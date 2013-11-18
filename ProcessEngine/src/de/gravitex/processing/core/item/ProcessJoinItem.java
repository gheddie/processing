package de.gravitex.processing.core.item;

import java.util.Date;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessJoinItem extends BlockingItem {

	public ProcessJoinItem(String identifier, String description) {
		super(identifier, description);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.JOIN;
	}
	
	public Date calculateExpiryDate() {
		return null;
	}
}
