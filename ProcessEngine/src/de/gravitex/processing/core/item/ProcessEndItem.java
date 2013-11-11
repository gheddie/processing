package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessEndItem extends ProcessItem {

	public ProcessEndItem(String identifier, String description) {
		super(identifier, description);
	}
	
	public ProcessItemType getItemType() {
		return ProcessItemType.END;
	}
	
	public boolean isBlocking() {
		return false;
	}
}
