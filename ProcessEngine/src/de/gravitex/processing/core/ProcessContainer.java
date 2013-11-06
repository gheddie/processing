package de.gravitex.processing.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import de.gravitex.processing.core.exception.ProcessException;
import de.gravitex.processing.core.item.ProcessForkItem;
import de.gravitex.processing.core.item.ProcessItem;
import de.gravitex.processing.core.logic.FlowDecision;
import de.gravitex.processing.testing.decision.DecisionForA1;

public class ProcessContainer {

	private HashMap<String, ProcessItem> processElements;

	private Set<ProcessItem> itemsInControl;

	public ProcessContainer() {
		super();
		init();
	}

	private void init() {
		processElements = new HashMap<>();
		itemsInControl = new HashSet<>();
	}

	public void addElement(ProcessItem processElement) throws ProcessException {

		if (processElements.size() == 0) {
			if (processElement.getItemType().equals(ProcessItemType.START)) {
				// init items in control with start element
				itemsInControl.add(processElement);
			} else {
				throw new ProcessException("process must start with START element!");
			}
		}

		System.out.println("adding process element : " + processElement);
		processElements.put(processElement.getIdentifier(), processElement);
	}

	public void proceed() {
		System.out.println("proceeding...");
		Set<ProcessItem> newItemsInControl = new HashSet<>();
		for (ProcessItem item : itemsInControl) {
			newItemsInControl.addAll(item.getFollowingItems());
		}
		itemsInControl = newItemsInControl;
		debugControl();
	}

	private void debugControl() {
		if (itemsInControl.size() > 0) {
			System.out.println("---------------------------------");
			for (ProcessItem item : itemsInControl) {
				System.out.println("IN CONTROL : " + item);
			}
			System.out.println("---------------------------------");
		} else {
			System.out.println("---------------------------------");
			System.out.println("NO ITEMS IN CONTROL");
			System.out.println("---------------------------------");
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

	public void addCondition(String itemIdentifier, String outgoingFlowDescriptor, Class<? extends FlowDecision> flowDecisionClazz) {
		((ProcessForkItem) processElements.get(itemIdentifier)).addOutlineCondition(outgoingFlowDescriptor, flowDecisionClazz);
	}
}
