package de.gravitex.processing.core.dao;

import java.util.Date;

import de.gravitex.processing.core.ProcessItemType;
import de.gravitex.processing.core.TaskState;

public class ProcessItemEntity extends AbstractEntity {

	private String name;
	
	private int processId;
	
	private TaskState state;

	private Date expiryDate;

	private ProcessItemType itemType;
	
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
	
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate; 
	}
	
	public ProcessItemType getItemType() {
		return itemType;
	}

	public void setItemType(ProcessItemType itemType) {
		this.itemType = itemType; 
	}
}
