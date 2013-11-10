package de.gravitex.processing.core.dao;

public abstract class AbstractEntity {

	private Long id;
	
	public Long getId() {
		return id;
	}
	
	protected void setId(Long id) {
		this.id = id;
	}
}
