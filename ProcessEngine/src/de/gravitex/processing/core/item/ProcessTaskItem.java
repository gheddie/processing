package de.gravitex.processing.core.item;

import java.util.Date;

import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.logic.TaskResolver;

public class ProcessTaskItem extends BlockingItem {
	
	private Class<? extends TaskResolver> resolverClass;

	public ProcessTaskItem(String identifier, String description) {
		super(identifier, description);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.TASK;
	}
	
	public void setResolverClass(Class<? extends TaskResolver> resolverClass) {
		this.resolverClass = resolverClass;
	}
	
	public Class<? extends TaskResolver> getResolverClass() {
		return resolverClass;
	}
	
	public void gainControl() {
		// just do nothing...
	}

	public Date calculateExpiryDate() {
		return null;
	}
}
