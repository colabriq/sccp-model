package com.goodforgoodbusiness.model;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.jena.graph.Triple;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.vertx.core.Future;

//@JsonAdapter(SubmittableContainer.Serializer.class)
public abstract class SubmittableContainer {
	public enum SubmitMode {
		SYNCHRONOUS,
		ASYNCHRONOUS,
		NONE
		
		;
		
		public static SubmitMode getDefault() {
			return SYNCHRONOUS;
		}
	}
	
	@Expose
	@SerializedName("added")
	private final List<Triple> added;
	
	@Expose
	@SerializedName("removed")
	private final List<Triple> removed;
	
	@Expose
	@SerializedName("links")
	private final Set<Link> links;
	
	public SubmittableContainer() {
		this.added = new LinkedList<>();
		this.removed = new LinkedList<>();
		this.links = new HashSet<>();
	}
	
	public SubmittableContainer(List<Triple> added, List<Triple> removed, Set<Link> links) {
		this.added = added.stream().collect(Collectors.toList());
		this.removed = removed.stream().collect(Collectors.toList());
		this.links = links;
	}
	
	public boolean isEmpty() {
		return added.isEmpty() && removed.isEmpty();
	}
	
	public void removed(Triple trup) {
		removed.add(trup);
	}
	
	public List<Triple> getRemoved() {
		return unmodifiableList(removed);
	}
	
	
	public void added(Triple trup) {
		added.add(trup);
	}
	
	public List<Triple> getAdded() {
		return unmodifiableList(added);
	}
	
	
	public Stream<Triple> getTriples() {
		return Stream.concat(getAdded().parallelStream(), getRemoved().parallelStream()).parallel();
	}

	
	public void linked(Link link) {
		links.add(link);
	}

	public Set<Link> getLinks() {
		return unmodifiableSet(links);
	}
	
	/**
	 * To be implemented by DHT
	 */
	public abstract void submit(Future<StorableContainer> future, SubmitMode mode);
	
	@Override
	public String toString() {
		return "SubmittableContainer(added=" + added + ", removed=" + removed + ", links=" + links + ")";
	}
}
