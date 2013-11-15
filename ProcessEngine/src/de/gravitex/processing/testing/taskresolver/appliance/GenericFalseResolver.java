package de.gravitex.processing.testing.taskresolver.appliance;

import de.gravitex.processing.core.logic.TaskResolver;

public class GenericFalseResolver implements TaskResolver {

	public boolean isTaskResolved() {
		return false;
	}
}
