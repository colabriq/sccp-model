package com.goodforgoodbusiness.model;

import static java.util.Objects.hash;
import static java.util.Optional.empty;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.jena.graph.Node;
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

/** Like a triple, but just the values, not data types or other associated gubbins. */
@JsonAdapter(TriTuple.Serializer.class)
public class TriTuple {
	public static class Serializer implements JsonSerializer<TriTuple>, JsonDeserializer<TriTuple> {
		@Override
		public JsonElement serialize(TriTuple tt, Type type, JsonSerializationContext ctx) {
			var obj = new JsonObject();
			
			if (tt.getSubject().isPresent()) {
				obj.addProperty("sub", tt.getSubject().get());
			}
			
			if (tt.getPredicate().isPresent()) {
				obj.addProperty("pre", tt.getPredicate().get());
			}
			
			if (tt.getObject().isPresent()) {
				obj.addProperty("obj", tt.getObject().get());
			}
			
			return obj;
		}
		
		@Override
		public TriTuple deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) {
			JsonObject obj = json.getAsJsonObject();
			
			return new TriTuple(
				obj.has("sub") ? Optional.of(obj.get("sub").getAsString()) : empty(),
				obj.has("pre") ? Optional.of(obj.get("pre").getAsString()) : empty(),
				obj.has("obj") ? Optional.of(obj.get("obj").getAsString()) : empty()
			);
		}
	}
	
	private static Optional<String> valueOf(Node node) {
		if (node == null || node.equals(Node.ANY)) {
			return empty();
		}
		else if (node.isURI()) {
			return Optional.of(node.getURI());
		}
		else if (node.isLiteral()) {
			return Optional.of(node.getLiteralLexicalForm());
		}
		else {
			throw new IllegalArgumentException(node.toString());
		}
	}
	
	/** Create from Triple */
	public static TriTuple from(Triple triple) {
		return new TriTuple(
			valueOf(triple.getSubject()),
			valueOf(triple.getPredicate()),
			valueOf(triple.getObject())
		);
	}
	
	/** Create from three Node objects. Null or Node.ANY map to null (any) */
	public static TriTuple from(Node subject, Node predicate, Node object) {
		return new TriTuple(
			valueOf(subject),
			valueOf(predicate),
			valueOf(object)
		);
	}
	
	/**
	 * Strip off N3 quotes
	 */
	private static String stripN3(String val) {
		if ((val.charAt(0) == '"') && (val.charAt(val.length() - 1) == '"')) {
			return val.substring(1, val.length() - 1);
		}
		
		if ((val.charAt(0) == '<') && (val.charAt(val.length() - 1) == '>')) {
			return val.substring(1, val.length() - 1);
		}
		
		throw new IllegalArgumentException(val);
	}
	
	/** Create from three N3-style values, e.g. <url> and "foo" */
	public static TriTuple fromN3Quoted(Optional<String> sub, Optional<String> pre, Optional<String> obj) {
		return new TriTuple(
			sub.map(TriTuple::stripN3),
			sub.map(TriTuple::stripN3),
			sub.map(TriTuple::stripN3)
		);
	}

	@Expose
	@SerializedName("sub")
	private final Optional<String> subject;
	
	@Expose
	@SerializedName("pre")
	private final Optional<String> predicate;

	@Expose
	@SerializedName("obj")
	private final Optional<String> object;
	
	public TriTuple(Optional<String> subject, Optional<String> predicate, Optional<String> object) {
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
	}
	
	public Optional<String> getSubject() {
		return subject;
	}

	public Optional<String> getPredicate() {
		return predicate;
	}

	public Optional<String> getObject() {
		return object;
	}

	@Override
	public int hashCode() {
		return hash(subject.orElse(null), predicate.orElse(null), object.orElse(null));
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof TriTuple)) {
			return false;
		}
		
		TriTuple other = (TriTuple)o;
		
		return 
			Objects.equals(subject, other.subject) &&
			Objects.equals(predicate, other.predicate) &&
			Objects.equals(object, other.object)
		;
	}
	
	@Override
	public String toString() {
		return "TriTuple(" + subject + ", " + predicate + ", " + object + ")";
	}
	
	/**
	 * Create all possible combinations for this TriTuple
	 */
	public Stream<TriTuple> matchingCombinations() {
		var sub = getSubject();
		var pre = getPredicate();
		var obj = getObject();
		
		var any = Optional.<String>empty();
		
		var combinations = Stream.of(
			// pick 3
			new TriTuple(sub, pre, obj),
			
			// pick 2
			new TriTuple(sub, pre, any),
			new TriTuple(sub, any, obj),
			new TriTuple(any, pre, obj),
			
			// pick 1
			new TriTuple(sub, any, any),
//			new TriTuple(any, pre, any),
			new TriTuple(any, any, obj)
			
			// pick 0
//			new TriTuple(any, any, any)
		);
	
		
		return combinations;
	}
}
