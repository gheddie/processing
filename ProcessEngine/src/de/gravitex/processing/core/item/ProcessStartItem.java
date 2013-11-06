package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessStartItem extends ProcessItem {

	public ProcessStartItem(String identifier) {
		super(identifier);
	}
	
	public ProcessItemType getItemType() {
		return ProcessItemType.START;
	}
}
