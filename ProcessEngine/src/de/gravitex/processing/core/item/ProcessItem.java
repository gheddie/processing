package de.gravitex.processing.core.item;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.gravitex.processing.core.ProcessItemType;

public abstract class ProcessItem {
	
	private static Logger			logger					= Logger.getLogger(ProcessItem.class);

	private String identifier;
	
	private String description;
	
	private Set<ProcessItem> followingItems;
	
	private Set<ProcessItem> parentItems;

	public ProcessItem(String identifier, String description) {
		super();
		init();
		this.identifier = identifier;
		this.description = description;
	}

	private void init() {
		this.followingItems = new HashSet<>();
		this.parentItems = new HashSet<>();
	}

	public abstract ProcessItemType getItemType();

	public String toString() {
		return "<<"+description+">> " + getClass().getSimpleName() + " [item type:"+getItemType()+"|identifier:"+identifier+"] ("+parentItems.size()+" parents)";
	}

	public String getIdentifier() {
		return identifier;
	}

	public void addFollower(ProcessItem followingElement) {
		followingItems.add(followingElement);
	}
	
	public Set<ProcessItem> getFollowingItems() {
		return followingItems;
	}

	public void addParentItem(ProcessItem parentItem) {
		parentItems.add(parentItem);
	}

	public void gainControl() {
		logger.info(" ------------------> gaining control : " + this);
	}
	
	public abstract boolean isBlocking();
}
