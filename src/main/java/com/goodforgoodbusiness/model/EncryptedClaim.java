package com.goodforgoodbusiness.model;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import com.goodforgoodbusiness.shared.treesort.TreeNode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EncryptedClaim implements Claim, TreeNode<String> {
	@Expose
	@SerializedName("inner_envelope")
	private EncryptedEnvelope innerEnvelope;
	
	@Expose
	@SerializedName("links")
	private Set<? extends ProvenLink> links;
	
	@Expose
	@SerializedName("signature")
	private Signature signature;
	
	public EncryptedClaim(EncryptedEnvelope env, Set<? extends ProvenLink> links, Signature signature) {
		this.innerEnvelope = env;
		this.links = links;
		this.signature = signature;
	}
	
	public EncryptedClaim() {
	}
	
	@Override
	public String getId() {
		return innerEnvelope.getHashKey();
	}
	
	public EncryptedEnvelope getInnerEnvelope() {
		return innerEnvelope;
	}
	
	public Set<? extends ProvenLink> getLinks() {
		return Collections.unmodifiableSet(links);
	}
	
	public Signature getSignature() {
		return signature;
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
	public String toString() {
		return "Claim(" + innerEnvelope + ", " + links + ", " + signature + ")";
	}
}
