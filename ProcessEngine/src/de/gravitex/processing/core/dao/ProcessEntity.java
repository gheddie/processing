package de.gravitex.processing.core.dao;

import de.gravitex.processing.core.ProcessState;

public class ProcessEntity extends AbstractEntity {

	private String name;
	
	private ProcessState state;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ProcessState getState() {
		return state;
	}
	
	public void setState(ProcessState state) {
		this.state = state;
	}
}
