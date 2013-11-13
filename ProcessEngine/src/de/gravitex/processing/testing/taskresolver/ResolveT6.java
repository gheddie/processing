package de.gravitex.processing.testing.taskresolver;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.logic.TaskResolver;

public class ResolveT6 implements TaskResolver {
	
	private static Logger			logger					= Logger.getLogger(ResolveT6.class);

	public boolean isTaskResolved() {
		logger.info("resolving task t6...");		
		return true;
	}
}
