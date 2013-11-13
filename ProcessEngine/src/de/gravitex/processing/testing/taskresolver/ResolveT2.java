package de.gravitex.processing.testing.taskresolver;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.logic.TaskResolver;

public class ResolveT2 implements TaskResolver {

	private static Logger			logger					= Logger.getLogger(ResolveT2.class);

	public boolean isTaskResolved() {
		logger.info("resolving task t2...");		
		return true;
	}
}
