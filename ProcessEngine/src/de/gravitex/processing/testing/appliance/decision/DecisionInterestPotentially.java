package de.gravitex.processing.testing.appliance.decision;

import de.gravitex.processing.core.logic.FlowDecision;
import de.gravitex.processing.testing.ProcessSingleton;
import de.gravitex.processing.testing.appliance.Interest;

public class DecisionInterestPotentially implements FlowDecision {

	public boolean conditionValid() {
		return ProcessSingleton.getInstance().getInterest().equals(Interest.POTENTIALLY);
	}
}
