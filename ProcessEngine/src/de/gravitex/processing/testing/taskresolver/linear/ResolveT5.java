package de.gravitex.processing.testing.taskresolver.linear;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.logic.TaskResolver;

public class ResolveT5 implements TaskResolver {
	
	private static Logger			logger					= Logger.getLogger(ResolveT5.class);

	public boolean isTaskResolved() {
		logger.info("resolving task t5...");		
		return true;
	}
}
