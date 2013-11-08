package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;

public class ProcessTaskItem extends ProcessItem {

	public ProcessTaskItem(String identifier, String description) {
		super(identifier, description);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.TASK;
	}
}
