package de.gravitex.processing.testing.appliance.decision;

import de.gravitex.processing.core.logic.FlowDecision;
import de.gravitex.processing.testing.ProcessSingleton;
import de.gravitex.processing.testing.appliance.Interest;

public class DecisionInterestNone implements FlowDecision {

	public boolean conditionValid() {
		return ProcessSingleton.getInstance().getInterest().equals(Interest.NONE);
	}
}
