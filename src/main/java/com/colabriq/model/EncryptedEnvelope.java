package com.colabriq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EncryptedEnvelope {	
	@Expose
	@SerializedName("contents")
	private String contents;
	
	@Expose
	@SerializedName("hashkey")
	private String hashkey;
	
	@Expose
	@SerializedName("link_verifier")
	private LinkVerifier linkVerifier;
	
	@Expose
	@SerializedName("signature")
	private Signature signature;
	
	public EncryptedEnvelope() {
	}
	
	public EncryptedEnvelope(String hashkey, String contents, LinkVerifier linkVerifier, Signature signature) {
		this.hashkey = hashkey;
		this.contents = contents;
		this.linkVerifier = linkVerifier;
		this.signature = signature;
	}
	
	public String getHashKey() {
		return hashkey;
	}
	
	public String getContents() {
		return contents;
	}
	
	public LinkVerifier getLinkVerifier() {
		return linkVerifier;
	}
	
	public Signature getSignature() {
		return signature;
	}
	
	@Override
	public int hashCode() {
		return this.hashkey.hashCode();
	}
	
	@Override
	public String toString() {
		return "EncryptedEnvelope(" + 
			"contents=" + contents + ", " +
			"hashkey=" + hashkey.substring(0, 5) + "..., " +
			"signature=" + signature + ", " +	
			"linkVerifier=" + linkVerifier + ", " + 
		")";
	}
}
