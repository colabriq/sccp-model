package com.goodforgoodbusiness.model;

import java.util.Set;
import java.util.stream.Stream;

import org.apache.jena.graph.Triple;

public class SubmittedClaim implements AccessibleClaim {
	private final SubmittableClaim claim;
	private final SubmitResult result;
	
	public SubmittedClaim(SubmittableClaim claim, SubmitResult result) {
		this.claim = claim;
		this.result = result;
	}
	
	public String getId() {
		return result.getId();
	}

	@Override
	public Stream<Triple> getTriples() {
		return claim.getTriples();
	}
	
	@Override
	public Stream<Triple> getRemoved() {
		return claim.getRemoved().stream();
	}

	@Override
	public Stream<Triple> getAdded() {
		return claim.getAdded().stream();
	}
	
	public Set<Link> getLinks() {
		return claim.getLinks();
	}

	@Override
	public String getValue() {
		return getId();
	}

	@Override
	public Stream<String> getPredecessors() {
		return claim.getLinks().stream().map(Link::getRef);
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (o instanceof Claim) {
			return getId().equals(((Claim)o).getId());
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "SubmittedClaim(" + getId() + ")";
	}
}

