package de.gravitex.processing.core;

import de.gravitex.processing.core.item.ProcessActionItem;
import de.gravitex.processing.core.item.ProcessEndItem;
import de.gravitex.processing.core.item.ProcessForkItem;
import de.gravitex.processing.core.item.ProcessItem;
import de.gravitex.processing.core.item.ProcessJoinItem;
import de.gravitex.processing.core.item.ProcessStartItem;

public class ProcessItemFactory {

	public static ProcessItem getProcessElement(ProcessItemType processItemType, String identifier) {
		switch (processItemType) {
		case START:
			//------------------
			return new ProcessStartItem(identifier);
			//------------------
		case ACTION:
			//------------------
			return new ProcessActionItem(identifier);
			//------------------
		case FORK:
			//------------------
			return new ProcessForkItem(identifier);
			//------------------
		case JOIN:
			//------------------
			return new ProcessJoinItem(identifier);
			//------------------
		case END:
			//------------------
			return new ProcessEndItem(identifier);
			//------------------
		}
		return null;
	}
}
