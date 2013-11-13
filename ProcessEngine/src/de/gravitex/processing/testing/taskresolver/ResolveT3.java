package de.gravitex.processing.testing.taskresolver;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.logic.TaskResolver;

public class ResolveT3 implements TaskResolver {
	
	private static Logger			logger					= Logger.getLogger(ResolveT3.class);

	public boolean isTaskResolved() {
		logger.info("resolving task t3...");		
		return true;
	}
}
