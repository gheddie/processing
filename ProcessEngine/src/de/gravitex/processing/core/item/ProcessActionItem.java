package de.gravitex.processing.core.item;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.exception.ProcessExecutionException;
import de.gravitex.processing.core.logic.FlowAction;

public class ProcessActionItem extends ProcessItem {
	
	private static Logger			logger					= Logger.getLogger(ProcessActionItem.class);

	private Class<? extends FlowAction> actionClass;

	public ProcessActionItem(String identifier, String description) {
		super(identifier, description);
	}

	public ProcessItemType getItemType() {
		return ProcessItemType.ACTION;
	}

	public void setActionClass(Class<? extends FlowAction> actionClass) {
		this.actionClass = actionClass;
	}
	
	public void gainControl() {
		//execute action
		if (actionClass != null) {
			try {
				((Class<? extends FlowAction>) actionClass).newInstance().executeAction();
			} catch (ProcessExecutionException | InstantiationException | IllegalAccessException e) {
				logger.error(e);
			}	
		}
	}
}
