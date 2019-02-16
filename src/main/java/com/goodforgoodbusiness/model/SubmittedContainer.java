package com.goodforgoodbusiness.model;

import java.util.Set;
import java.util.stream.Stream;

import org.apache.jena.graph.Triple;

public class SubmittedContainer implements AccessibleContainer {
	private final SubmittableContainer container;
	private final SubmitResult result;
	
	public SubmittedContainer(SubmittableContainer container, SubmitResult result) {
		this.container = container;
		this.result = result;
	}
	
	public String getId() {
		return result.getId();
	}

	@Override
	public Stream<Triple> getTriples() {
		return container.getTriples();
	}
	
	@Override
	public Stream<Triple> getRemoved() {
		return container.getRemoved().stream();
	}

	@Override
	public Stream<Triple> getAdded() {
		return container.getAdded().stream();
	}
	
	public Set<Link> getLinks() {
		return container.getLinks();
	}

	@Override
	public String getValue() {
		return getId();
	}

	@Override
	public Stream<String> getPredecessors() {
		return container.getLinks().stream().map(Link::getRef);
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
		
		if (o instanceof Container) {
			return getId().equals(((Container)o).getId());
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "SubmittedContainer(" + getId() + ")";
	}
}

