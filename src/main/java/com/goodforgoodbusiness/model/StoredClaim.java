package com.goodforgoodbusiness.model;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Stream.concat;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.jena.graph.Triple;

import com.goodforgoodbusiness.shared.encode.JSON;
import com.goodforgoodbusiness.shared.treesort.TreeNode;
import com.google.common.reflect.TypeToken;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoredClaim implements AccessibleClaim, TreeNode<String> {
	public static List<StoredClaim> fromJson(String json) {
		return JSON.decode(json, new TypeToken<List<StoredClaim>>() {}.getType() );
	}
	
	@Expose
	@SerializedName("inner_envelope")
	private Envelope innerEnvelope;
	
	@Expose
	@SerializedName("links")
	private Set<? extends ProvenLink> links;
	
	@Expose
	@SerializedName("signature")
	private Signature signature;
	
	public StoredClaim(Envelope env, Set<? extends ProvenLink> links, Signature signature) {
		this.innerEnvelope = env;
		this.links = links;
		this.signature = signature;
	}
	
	public StoredClaim() {
	}
	
	public Envelope getInnerEnvelope() {
		return innerEnvelope;
	}
	
	public Stream<Triple> getAdded() {
		return innerEnvelope.getContents().getAdded().stream()
		;
	}
	
	public Stream<Triple> getRemoved(){
		return innerEnvelope.getContents().getRemoved().stream()
		;
	}
	
	public Stream<Triple> getTriples() {
		return concat(getRemoved(), getAdded());
	}
	
	public Set<? extends ProvenLink> getLinks() {
		return unmodifiableSet(links);
	}

	public Signature getSignature() {
		return signature;
	}
	
	@Override
	public String getId() {
		return innerEnvelope.getHashKey();
	}
	
	@Override
	public String getValue() {
		return innerEnvelope.getHashKey();
	}

	@Override
	public Stream<String> getPredecessors() {
		return links.stream().map(Link::getRef);
	}
	
	@Override
	public int hashCode() {
		return this.innerEnvelope.hashCode();
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
		return "StoredClaim(" + innerEnvelope + ", " + links + ", " + signature + ")";
	}
}
