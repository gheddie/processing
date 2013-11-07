package de.gravitex.processing.core.logic;

import de.gravitex.processing.core.exception.ProcessExecutionException;

public interface FlowAction {

	public void executeAction() throws ProcessExecutionException;
}
