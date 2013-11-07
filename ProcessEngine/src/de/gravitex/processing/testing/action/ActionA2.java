package de.gravitex.processing.testing.action;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.exception.ProcessExecutionException;
import de.gravitex.processing.core.logic.FlowAction;

public class ActionA2 implements FlowAction {
	
	private static Logger			logger					= Logger.getLogger(ActionA2.class);

	public void executeAction() throws ProcessExecutionException {
		logger.info("executing action for a2...");
	}
}
