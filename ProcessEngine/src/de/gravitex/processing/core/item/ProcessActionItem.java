package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessActionItem extends ProcessItem {

	public ProcessActionItem(String identifier) {
		super(identifier);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.ACTION;
	}
}
