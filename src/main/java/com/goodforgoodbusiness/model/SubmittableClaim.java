package com.goodforgoodbusiness.model;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.jena.graph.Triple;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

@JsonAdapter(SubmittableClaim.Serializer.class)
public class SubmittableClaim {
	public static class Serializer implements JsonSerializer<SubmittableClaim>, JsonDeserializer<SubmittableClaim> {
		@Override
		public JsonElement serialize(SubmittableClaim claim, Type type, JsonSerializationContext ctx) {
			var obj = new JsonObject();
			
			obj.add("added", ctx.serialize(claim.getAdded()));
			obj.add("removed", ctx.serialize(claim.getRemoved()));
			obj.add("links", ctx.serialize(claim.getLinks()));
			
			return obj;
		}
		
		@Override
		public SubmittableClaim deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) {
			JsonObject obj = json.getAsJsonObject();
			
			return new SubmittableClaim(
				ctx.deserialize(obj.get("added"), TypeToken.getParameterized(List.class, Triple.class).getType()),
				ctx.deserialize(obj.get("removed"), TypeToken.getParameterized(List.class, Triple.class).getType()),
				ctx.deserialize(obj.get("links"), TypeToken.getParameterized(Set.class, Link.class).getType())
			);
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
	
	public SubmittableClaim() {
		this.added = new LinkedList<>();
		this.removed = new LinkedList<>();
		this.links = new HashSet<>();
	}
	
	public SubmittableClaim(List<Triple> added, List<Triple> removed, Set<Link> links) {
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
		return Stream.concat(getAdded().stream(), getRemoved().stream());
	}

	
	public void linked(Link link) {
		links.add(link);
	}

	public Set<Link> getLinks() {
		return unmodifiableSet(links);
	}
	
	@Override
	public String toString() {
		return "SubmittableClaim(added=" + added + ", removed=" + removed + ", links=" + links + ")";
	}
}
