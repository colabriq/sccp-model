package com.goodforgoodbusiness.model;

import java.util.Collection;

public interface Claim {
	public String getId();
	public Collection<? extends Link> getLinks();
}
