package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessForkItem extends ProcessItem {

	public ProcessForkItem(String identifier) {
		super(identifier);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.FORK;
	}
}
