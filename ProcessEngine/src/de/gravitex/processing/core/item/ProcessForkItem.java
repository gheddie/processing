package de.gravitex.processing.core.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.logic.FlowDecision;

public class ProcessForkItem extends ProcessItem {
	
	private static Logger			logger					= Logger.getLogger(ProcessForkItem.class);
	
	private HashMap<String, Class<? extends FlowDecision>> outlineConditions;

	public ProcessForkItem(String identifier, String description) {
		super(identifier, description);
		outlineConditions = new HashMap<>();
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.FORK;
	}

	public void addOutlineCondition(String outgoingFlowDescriptor, Class<? extends FlowDecision> flowDecisionClazz) {
		outlineConditions.put(outgoingFlowDescriptor, flowDecisionClazz);
	}
	
	public Set<ProcessItem> getFollowingItems() {
		Set<ProcessItem> effectiveFollowingItems = new HashSet<>();
		for (ProcessItem processItem : super.getFollowingItems()) {
			if (checkOutlineCondition(processItem)) {
				effectiveFollowingItems.add(processItem);
			}
		}		
		return effectiveFollowingItems;
	}

	private boolean checkOutlineCondition(ProcessItem processItem) {
		logger.info("checking outline condition for process item : "+processItem+".");
		try {
			boolean conditionValid = ((Class<? extends FlowDecision>) outlineConditions.get(processItem.getIdentifier())).newInstance().conditionValid();
			if (conditionValid) {
				logger.trace("condition valid for item identifier '"+processItem.getIdentifier()+"'...");
			} else {
				logger.trace("condition invalid for item identifier '"+processItem.getIdentifier()+"'...");
			}
			return conditionValid;
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(e);
			return false;
		}
	}
}
