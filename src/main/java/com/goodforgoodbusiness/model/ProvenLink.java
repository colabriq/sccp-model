package com.goodforgoodbusiness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProvenLink extends Link {
	@Expose
	@SerializedName("proof")
	private String proof;
	
	public ProvenLink() {
	}
	
	public ProvenLink(Link link, String proof) {
		super(link.getRef(), link.getRel());
		this.proof = proof;
	}

	public String getProof() {
		return proof;
	}
}
