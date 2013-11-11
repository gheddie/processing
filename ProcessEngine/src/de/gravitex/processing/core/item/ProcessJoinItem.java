package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessJoinItem extends ProcessItem {

	public ProcessJoinItem(String identifier, String description) {
		super(identifier, description);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.JOIN;
	}
	
	public boolean isBlocking() {
		return false;
	}
}
