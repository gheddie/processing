package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessEndItem extends ProcessItem {

	public ProcessEndItem(String identifier) {
		super(identifier);
	}
	
	public ProcessItemType getItemType() {
		return ProcessItemType.END;
	}
}
