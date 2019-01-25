package com.goodforgoodbusiness.model;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

@JsonAdapter(Link.Serializer.class)
public class Link {
	public static class Serializer implements JsonSerializer<Link> {
		@Override
		public JsonElement serialize(Link link, Type type, JsonSerializationContext ctx) {
			var obj = new JsonObject();
			
			obj.add("ref", ctx.serialize(link.getRef()));
			obj.add("rel", ctx.serialize(link.getRel().toString()));
			
			if (link instanceof ProvenLink) {
				obj.add("proof", ctx.serialize(((ProvenLink)link).getProof()));
			}
			
			return obj;
		}
	}
	
	@JsonAdapter(RelTypeSerializer.class)
	public static enum RelType {
		CAUSED_BY("https://schemas.goodforgoodbusiness.org/weft/1.0#causedBy")
		
		;
		
		private final String uri;
		
		private RelType(String uri) {
			this.uri = uri;
		}
		
		public static RelType fromUri(String uri) {
			for (RelType r : values()) {
				if (r.uri.equals(uri)) {
					return r;
				}
			}
			
			return null;
		}
		
		@Override
		public String toString() {
			return uri;
		}
	}
	
	private static class RelTypeSerializer implements JsonSerializer<RelType>, JsonDeserializer<RelType> {
		@Override
		public JsonElement serialize(RelType rel, Type type, JsonSerializationContext ctx) {
			return ctx.serialize(rel.toString());
		}	
		
		@Override
		public RelType deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) {
			return RelType.fromUri(json.getAsString());
		}
	}
	
	@Expose
	@SerializedName("ref")
	private String ref;
	
	@Expose
	@SerializedName("rel")
	private RelType rel;
	
	public Link() {
	}
	
	public Link(String ref, RelType rel) {
		this.ref = ref;
		this.rel = rel;
	}
	
	public String getRef() {
		return ref;
	}
	
	public RelType getRel() {
		return rel;
	}
	
	@Override
	public int hashCode() {
		return ref.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Link) {
			Link l = (Link)o;
			return this.ref.equals(l.ref);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Link(" + ref.substring(0, 5) + ", " + rel + ")";
	}
}
