package com.colabriq.model;

import static com.colabriq.shared.encode.Hash.sha512;

import com.colabriq.shared.encode.CBOR;
import com.colabriq.shared.encode.Hex;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Envelope {	
	@Expose
	@SerializedName("contents")
	private Contents contents;
	
	@Expose
	@SerializedName("hashkey")
	private String hashKey;
	
	@Expose
	@SerializedName("link_verifier")
	private LinkVerifier linkVerifier;
	
	@Expose
	@SerializedName("signature")
	private Signature signature;
	
	public Envelope() {
	}
	
	public Envelope(Contents contents, LinkVerifier linkVerifier, Signature signature) {
		this.hashKey = Hex.encode(sha512(CBOR.forObject(contents)));
		this.contents = contents;
		this.linkVerifier = linkVerifier;
		this.signature = signature;
	}
	
	public String getHashKey() {
		return hashKey;
	}
	
	public Contents getContents() {
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
		return this.hashKey.hashCode();
	}
	
	@Override
	public String toString() {
		return "Envelope(" + 
			"contents=" + contents + ", " +
			"hashKey=" + hashKey.substring(0, 5) + "..., " +
			"signature=" + signature + ", " +	
			"linkVerifier=" + linkVerifier + ", " + 
		")";
	}
}
