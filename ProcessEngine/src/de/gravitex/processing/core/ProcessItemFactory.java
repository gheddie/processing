package de.gravitex.processing.core;

import de.gravitex.processing.core.item.ProcessActionItem;
import de.gravitex.processing.core.item.ProcessEndItem;
import de.gravitex.processing.core.item.ProcessForkItem;
import de.gravitex.processing.core.item.ProcessItem;
import de.gravitex.processing.core.item.ProcessJoinItem;
import de.gravitex.processing.core.item.ProcessStartItem;
import de.gravitex.processing.core.item.ProcessTaskItem;
import de.gravitex.processing.core.item.ProcessWaitItem;

public class ProcessItemFactory {

	public static ProcessItem getProcessElement(ProcessItemType processItemType, String identifier, String description) {
		switch (processItemType) {
		case START:
			//------------------
			return new ProcessStartItem(identifier, description);
			//------------------
		case ACTION:
			//------------------
			return new ProcessActionItem(identifier, description);
			//------------------
		case TASK:
			//------------------
			return new ProcessTaskItem(identifier, description);
			//------------------			
		case FORK:
			//------------------
			return new ProcessForkItem(identifier, description);
			//------------------
		case JOIN:
			//------------------
			return new ProcessJoinItem(identifier, description);
			//------------------
		case WAIT:
			//------------------
			return new ProcessWaitItem(identifier, description);
			//------------------			
		case END:
			//------------------
			return new ProcessEndItem(identifier, description);
			//------------------
		}
		return null;
	}
}
