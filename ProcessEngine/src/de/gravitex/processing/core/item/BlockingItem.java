package de.gravitex.processing.core.item;

import java.util.Date;

public abstract class BlockingItem extends ProcessItem {

	public BlockingItem(String identifier, String description) {
		super(identifier, description);
	}

//	public abstract Class<? extends TaskResolver> getResolverClass();

	public abstract Date calculateExpiryDate();
}
