package de.gravitex.processing.core.dao;

import de.gravitex.processing.core.TaskState;

public class ProcessTask extends AbstractEntity {

	private String name;
	
	private TaskState state;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TaskState getState() {
		return state;
	}
	
	public void setState(TaskState state) {
		this.state = state;
	}
}
