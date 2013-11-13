package de.gravitex.processing.testing.taskresolver.linear;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.logic.TaskResolver;

public class ResolveT4 implements TaskResolver {
	
	private static Logger			logger					= Logger.getLogger(ResolveT4.class);

	public boolean isTaskResolved() {
		logger.info("resolving task t4...");		
		return true;
	}
}
