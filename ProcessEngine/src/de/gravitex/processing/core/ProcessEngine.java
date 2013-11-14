package de.gravitex.processing.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.dao.ProcessDAO;
import de.gravitex.processing.core.dao.ProcessTask;
import de.gravitex.processing.core.exception.ProcessException;
import de.gravitex.processing.core.exception.TaskUnresolvedException;
import de.gravitex.processing.core.item.ProcessActionItem;
import de.gravitex.processing.core.item.ProcessForkItem;
import de.gravitex.processing.core.item.ProcessItem;
import de.gravitex.processing.core.item.ProcessTaskItem;
import de.gravitex.processing.core.logic.FlowAction;
import de.gravitex.processing.core.logic.FlowDecision;
import de.gravitex.processing.core.logic.TaskResolver;

public class ProcessEngine {
	
	private static Logger			logger					= Logger.getLogger(ProcessEngine.class);

	private HashMap<String, ProcessItem> processElements;

	private Set<ProcessItem> itemsInControl;

	private List<ProcessTask> openTasks;

	private Set<ProcessItem> visitedItems;

	public ProcessEngine() {
		super();
		init();
	}

	private void init() {
		processElements = new HashMap<>();
		itemsInControl = new HashSet<>();
		visitedItems = new HashSet<>();
	}

	public void addElement(ProcessItem processElement) throws ProcessException {
		
		if (processElement == null) {
			throw new ProcessException("process element must not be NULL!");
		}
		
		if ((processElement.getIdentifier() == null) || (processElement.getIdentifier().length() == 0)) {
			throw new ProcessException("process element must have an identifier!");
		}

		if (processElements.size() == 0) {
			//...
		} else {
			if (processElements.get(processElement.getIdentifier()) != null) {
				throw new ProcessException("duplicate identifier '"+processElement.getIdentifier()+"' detected.");
			}
		}

//		logger.info("adding process element : " + processElement);
		processElements.put(processElement.getIdentifier(), processElement);
	}

	public void singleStep() {
//		logger.info("stepping...");
		Set<ProcessItem> newItemsInControl = new HashSet<>();
		for (ProcessItem item : itemsInControl) {
			//blocking item will not put following items but itself into the set
			if (item.isBlocking()) {
				newItemsInControl.add(item);
			} else {
				newItemsInControl.addAll(item.getFollowingItems());	
			}			
		}
		itemsInControl = newItemsInControl;
		for (ProcessItem item : itemsInControl) {
			gainControl(item);
		}
	}

	/*
	private void debugControl() {
		if (itemsInControl.size() > 0) {
			logger.info("---------------------------------");
			for (ProcessItem item : itemsInControl) {
				logger.info("IN CONTROL : " + item);
			}
			logger.info("---------------------------------");
		} else {
			logger.info("---------------------------------");
			logger.info("NO ITEMS IN CONTROL");
			logger.info("---------------------------------");
		}
	}
	*/

	public void relateParentFromTo(String parentIdentifier, String itemIdentifier) throws ProcessException {

		ProcessItem item = processElements.get(itemIdentifier);
		if (item == null) {
			throw new ProcessException("item not found while relating : '" + itemIdentifier + "'.");
		}

		ProcessItem parent = processElements.get(parentIdentifier);
		if (parent == null) {
			throw new ProcessException("parent not found while relating : '" + parentIdentifier + "'.");
		}

		parent.addFollower(item);
		item.addParentItem(parent);
	}

	public void addCondition(String itemIdentifier, String outgoingFlowDescriptor, Class<? extends FlowDecision> flowDecisionClass) {
		((ProcessForkItem) processElements.get(itemIdentifier)).addOutlineCondition(outgoingFlowDescriptor, flowDecisionClass);
	}

	public void addAction(String itemIdentifier, Class<? extends FlowAction> actionClass) {
		((ProcessActionItem) processElements.get(itemIdentifier)).setActionClass(actionClass);		
	}
	
	public int startProcess() throws ProcessException {
		//put start item in control
		ProcessItem startItem = findStartItem();
		gainControl(startItem);
		itemsInControl.add(startItem);
		Connection connection = null;
		try {
			connection = ProcessDAO.getConnection();
			int processId = ProcessDAO.writeProcessInstance("klaus", ProcessState.RUNNING, new Date(), connection );
			loop(processId);
			ProcessDAO.returnConnection(connection);
			return processId;
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e);
			return -1;
		}
	}

	private void gainControl(ProcessItem item) {
		if (!(visitedItems.contains(item))) {
			item.gainControl();
			visitedItems.add(item);
		} else {
			logger.info("item '"+item.getIdentifier()+"' already visited ---> returning.");
		}	
	}

	private ProcessItem findStartItem() throws ProcessException {
		for (ProcessItem item : processElements.values()) {
			if (item.getItemType().equals(ProcessItemType.START)) {
				return item;
			}
		}
		throw new ProcessException("start item could not be found!");
	}

	private void loop(int processId) {
		while (!(allItemsInControlBlocking())) {
			singleStep();				
		}
		persistActualProcessState(processId);
	}

	private void persistActualProcessState(int processId) {
		ProcessTask task = null;
		for (ProcessItem item : itemsInControl) {
			task = new ProcessTask();
			task.setName(item.getIdentifier());
			task.setState(TaskState.OPEN);
			if (!(taskActuallyOpen(item.getIdentifier()))) {
				Connection connection;
				try {
					connection = ProcessDAO.getConnection();
					ProcessDAO.writeProcessTask(processId, task, connection);
					ProcessDAO.returnConnection(connection);
				} catch (ClassNotFoundException | SQLException e) {
					logger.error(e);
				}				
			}			
		}
	}

	private boolean taskActuallyOpen(String identifier) {
		if (openTasks == null) {
//			logger.info("no open tasks present --> skipping '"+identifier+"'.");
			return false;
		}
		for (ProcessTask openTask : openTasks) {
			if (openTask.getName().equals(identifier)) {
				logger.info("task '"+identifier+"' is already marked as open --> skipping.");	
				return true;
			}
		}
		return false;
	}

	private boolean allItemsInControlBlocking() {
		for (ProcessItem item : itemsInControl) {
			if (!(item.isBlocking())) {
				return false;
			}
		}
		return true;
	}

	public void clearItemsInControl() {
		itemsInControl.clear();
	}
	
	public void adaptItemsInControl(String... itemIdentifiers) throws ProcessException {
		ProcessItem element = null;
		for (String itemIdentifier : itemIdentifiers) {
			element = processElements.get(itemIdentifier);
			if (element == null) {
				throw new ProcessException("unable to find process item by identifier '"+itemIdentifier+"'!");
			}
			itemsInControl.add(element);
		}
	}

	public void finishTask(String taskName, int processId) throws ProcessException {
		logger.info("attempting to finish task '"+taskName+"'...");
		ProcessTaskItem taskItem = (ProcessTaskItem) processElements.get(taskName);
		Connection connection = null;
		try {			
			Class<? extends TaskResolver> resolverClass = (Class<? extends TaskResolver>) taskItem.getResolverClass();
			if (resolverClass == null) {
				throw new ProcessException("resolver class for task '"+taskName+"' must not be NULL!");
			}
			if (resolverClass.newInstance().isTaskResolved()) {
				connection = ProcessDAO.getConnection();
				ProcessDAO.setTaskResolved(processId, taskName, connection);	
				//put following item of task in control (task has only one following item)
				Iterator<ProcessItem> iterator = taskItem.getFollowingItems().iterator();
				ProcessItem item = iterator.next();
				itemsInControl.add(item);
				gainControl(item);
				//place other open tasks as itemsin control
				openTasks = ProcessDAO.loadOpenTasks(processId, connection);
				for (ProcessTask task : openTasks) {
					itemsInControl.add(processElements.get(task.getName()));
				}
				loop(processId);
				ProcessDAO.returnConnection(connection);
			} else {
				throw new TaskUnresolvedException("task '"+taskName+"' was not resolved!");
			}			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			logger.error(e);
		}
	}

	public void addTaskResolver(String itemIdentifier, Class<? extends TaskResolver> resolverClass) {
		((ProcessTaskItem) processElements.get(itemIdentifier)).setResolverClass(resolverClass);
	}
}
