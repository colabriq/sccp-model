package com.goodforgoodbusiness.model;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Stream;

import org.apache.jena.graph.Triple;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

public class Contents {
	@JsonAdapter(ThreadTypeSerializer.class)
	public static enum ThreadType {
		CLAIM("claim");

		private final String value;
		
		private ThreadType(String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value;
		}
		
		public static ThreadType fromString(String string) {
			return Stream
				.of(ThreadType.values())
				.filter(tt -> tt.value.equals(string))
				.findFirst()
				.orElse(null)
			;
		}
	}
	
	public static class ThreadTypeSerializer implements JsonSerializer<ThreadType>, JsonDeserializer<ThreadType> {
		@Override
		public JsonElement serialize(ThreadType tt, Type type, JsonSerializationContext ctx) {
			return new JsonPrimitive(tt.toString());
		}
		
		@Override
		public ThreadType deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) {
			return ThreadType.fromString(json.getAsString());
		}
	}
	
	@Expose
	@SerializedName("type")
	private ThreadType type;
	
	@Expose
	@SerializedName("antecedents")
	private List<String> antecedents;
	
	@Expose
	@SerializedName("added")
	private List<? extends Triple> added;
	
	@Expose
	@SerializedName("removed")
	private List<? extends Triple> removed;
	
	@Expose
	@SerializedName("link_secret")
	private LinkSecret linkSecret;
	
	public Contents() {
	}
	
	public Contents(ThreadType type, List<String> ante, List<? extends Triple> added, List<? extends Triple> removed, LinkSecret linkSec) {
		this.type = type;
		this.antecedents = ante;
		this.added = added;
		this.removed = removed;
		this.linkSecret = linkSec;
	}

	public List<String> getAntecedents() {
		return unmodifiableList(antecedents);
	}
	
	public List<Triple> getAdded() {
		return unmodifiableList(added);
	}
	
	public List<Triple> getRemoved(){
		return unmodifiableList(removed);
	}
	
	public LinkSecret getLinkSecret() {
		return linkSecret;
	}
	
	@Override
	public String toString() {
		return "Contents(" + 
			"type=" + type + ", " + 
			"added=" + asList(added) + ", " + 
			"removed=" + asList(removed) + ", " + 
			"antecedents=" + antecedents.stream().map(a -> a.substring(0, 5) + "...").collect(toList()) + ", " + 
			"linkSecret=" + linkSecret + 
		")";
	}
}
