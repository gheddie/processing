package de.gravitex.processing.core.item;

import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.logic.TaskResolver;

public class ProcessTaskItem extends ProcessItem {
	
	private Class<? extends TaskResolver> resolverClass;

	public ProcessTaskItem(String identifier, String description) {
		super(identifier, description);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.TASK;
	}
	
	public boolean isBlocking() {
		return true;
	}

	public void setResolverClass(Class<? extends TaskResolver> resolverClass) {
		this.resolverClass = resolverClass;
	}
	
	public Class<? extends TaskResolver> getResolverClass() {
		return resolverClass;
	}
}
