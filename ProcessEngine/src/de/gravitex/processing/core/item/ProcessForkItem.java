package de.gravitex.processing.core.item;

import java.util.HashMap;
import java.util.Set;

import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.logic.FlowDecision;

public class ProcessForkItem extends ProcessItem {
	
	private HashMap<String, Class<? extends FlowDecision>> outlineConditions;

	public ProcessForkItem(String identifier) {
		super(identifier);
		outlineConditions = new HashMap<>();
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.FORK;
	}

	public void addOutlineCondition(String outgoingFlowDescriptor, Class<? extends FlowDecision> flowDecisionClazz) {
		outlineConditions.put(outgoingFlowDescriptor, flowDecisionClazz);
	}
	
	public Set<ProcessItem> getFollowingItems() {
		return super.getFollowingItems();
	}
}
