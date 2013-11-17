package de.gravitex.processing.core.item;

import java.util.Calendar;
import java.util.Date;

import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.logic.TaskResolver;
import de.gravitex.processing.testing.taskresolver.appliance.GenericTrueResolver;

public class ProcessWaitItem extends BlockingItem {

	public ProcessWaitItem(String identifier, String description) {
		super(identifier, description);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.WAIT;
	}
	
	public boolean isBlocking() {
		return true;
	}

	public Class<? extends TaskResolver> getResolverClass() {
		return GenericTrueResolver.class;
	}

	public Date calculateExpiryDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 20);
		return cal.getTime();
	}
}
