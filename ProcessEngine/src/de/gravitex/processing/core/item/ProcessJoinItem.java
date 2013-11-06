package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessJoinItem extends ProcessItem {

	public ProcessJoinItem(String identifier) {
		super(identifier);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.JOIN;
	}
}
