package de.gravitex.processing.core.dao;

import de.gravitex.processing.core.TaskState;

public class ProcessTask extends AbstractEntity {

	private String name;
	
	private int processId;
	
	private TaskState state;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getProcessId() {
		return processId;
	}
	
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	
	public TaskState getState() {
		return state;
	}
	
	public void setState(TaskState state) {
		this.state = state;
	}
}
