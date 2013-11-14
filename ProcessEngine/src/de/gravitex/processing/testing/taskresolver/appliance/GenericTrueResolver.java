package de.gravitex.processing.testing.taskresolver.appliance;

import de.gravitex.processing.core.logic.TaskResolver;

public class GenericTrueResolver implements TaskResolver {

	public boolean isTaskResolved() {
		return true;
	}
}
