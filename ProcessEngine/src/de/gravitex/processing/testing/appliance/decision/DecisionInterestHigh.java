package de.gravitex.processing.testing.appliance.decision;

import de.gravitex.processing.core.logic.FlowDecision;
import de.gravitex.processing.testing.ProcessSingleton;
import de.gravitex.processing.testing.appliance.Interest;

public class DecisionInterestHigh implements FlowDecision {

	public boolean conditionValid() {
		return ProcessSingleton.getInstance().getInterest().equals(Interest.HIGH);
	}
}
