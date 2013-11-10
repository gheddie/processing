package de.gravitex.processing.core.dao;

import java.util.Date;

import de.gravitex.processing.core.ProcessState;

public class ProcessEntity extends AbstractEntity {

	private String name;
	
	private ProcessState state;
	
	private Date creationDate;
	
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
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
