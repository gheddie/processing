package de.gravitex.processing.testing.taskresolver;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.logic.TaskResolver;

public class ResolveT1 implements TaskResolver {
	
	private static Logger			logger					= Logger.getLogger(ResolveT1.class);

	public boolean isTaskResolved() {
		logger.info("resolving task t1...");		
		return true;
	}
}
