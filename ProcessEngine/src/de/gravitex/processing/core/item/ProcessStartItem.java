package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessStartItem extends ProcessItem {

	public ProcessStartItem(String identifier, String description) {
		super(identifier, description);
	}
	
	public ProcessItemType getItemType() {
		return ProcessItemType.START;
	}
}
