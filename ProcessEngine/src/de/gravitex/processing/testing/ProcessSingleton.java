package de.gravitex.processing.testing;

import de.gravitex.processing.testing.appliance.Interest;

public class ProcessSingleton {

	private static ProcessSingleton instance = null;
	
	private Interest interest;

	private ProcessSingleton() {
		init();
	}
	
	private void init() {
		interest = Interest.POTENTIALLY;
	}

	public static ProcessSingleton getInstance() {
		if (instance == null) {
			instance = new ProcessSingleton();
		}
		return instance;
	}

	public void setInterest(Interest interest) {
		this.interest = interest;
	}
	
	public Interest getInterest() {
		return interest;
	}
}
