package de.gravitex.processing.core;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.dao.ProcessDAO;
import de.gravitex.processing.core.dao.ProcessTask;
import de.gravitex.processing.core.exception.ProcessException;
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

	private int processId;

	public ProcessEngine() {
		super();
		init();
	}

	private void init() {
		processElements = new HashMap<>();
		itemsInControl = new HashSet<>();
	}

	public void addElement(ProcessItem processElement) throws ProcessException {
		
		if (processElement == null) {
			throw new ProcessException("process element must not be NULL!");
		}
		
		if ((processElement.getIdentifier() == null) || (processElement.getIdentifier().length() == 0)) {
			throw new ProcessException("process element must have an identifier!");
		}

		if (processElements.size() == 0) {
			if (processElement.getItemType().equals(ProcessItemType.START)) {
				// init items in control with start element
				itemsInControl.add(processElement);
			} else {
				throw new ProcessException("process must start with START element!");
			}
		} else {
			if (processElements.get(processElement.getIdentifier()) != null) {
				throw new ProcessException("duplicate identifier '"+processElement.getIdentifier()+"' detected.");
			}
		}

		logger.info("adding process element : " + processElement);
		processElements.put(processElement.getIdentifier(), processElement);
	}

	public void singleStep() {
		logger.trace("stepping...");
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
			item.gainControl();
		}
		debugControl();
	}

	private void debugControl() {
		if (itemsInControl.size() > 0) {
			
			//all blocking?
//			if (allItemsInControlBlocking()) {
//				logger.info(" ------------------------------ ALL BLOCKING ------------------------------ ");
//			}
			
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

	public void relateParent(String itemIdentifier, String parentIdentifier) throws ProcessException {

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
	
	public void resumeProcess(int processIdToResume, String... itemIdentifiersToResume) {
		processId = processIdToResume;
		logger.info("resuming process...");
		//restore items in in control from database
		itemsInControl.clear();
		for (String itemIdentifierToResume : itemIdentifiersToResume) {
			itemsInControl.add(processElements.get(itemIdentifierToResume));			
		}		
		//set all items in control to following item
		Set<ProcessItem> newItemsInControl = new HashSet<>();
		for (ProcessItem item : itemsInControl) {
			newItemsInControl.addAll(item.getFollowingItems());
		}
		itemsInControl = newItemsInControl;
		try {
			while (!(allItemsInControlBlocking())) {
				singleStep();
				Thread.sleep(2500);
			}
			persistActualProcessState();
		} catch (InterruptedException e) {
			logger.error(e);
		}		
	}

	public void startProcess() {
		processId = ProcessDAO.writeProcessInstance("klaus", ProcessState.RUNNING, new Date());
		try {
			while (!(allItemsInControlBlocking())) {
				singleStep();
				Thread.sleep(2500);
			}
			persistActualProcessState();
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}

	private void persistActualProcessState() {
		ProcessTask task = null;
		for (ProcessItem item : itemsInControl) {
			task = new ProcessTask();
			task.setName(item.getIdentifier());
			task.setState(TaskState.OPEN);
			ProcessDAO.writeProcessTask(processId, task);
		}
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

	public void finishTask(String taskName) {
		logger.info("attempting to finish task '"+taskName+"'...");
		ProcessTaskItem taskItem = (ProcessTaskItem) processElements.get(taskName);
		try {
			if (((Class<? extends TaskResolver>) taskItem.getResolverClass()).newInstance().isTaskResolved()) {
				ProcessDAO.setTaskResolved(processId, taskName);	
			}			
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(e);
		}
	}

	public void addTaskResolver(String itemIdentifier, Class<? extends TaskResolver> resolverClass) {
		((ProcessTaskItem) processElements.get(itemIdentifier)).setResolverClass(resolverClass);
	}
}
