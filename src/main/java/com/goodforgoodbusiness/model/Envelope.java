package com.goodforgoodbusiness.model;

import static com.goodforgoodbusiness.shared.encode.Hash.sha256;

import com.goodforgoodbusiness.shared.encode.CBOR;
import com.goodforgoodbusiness.shared.encode.Hex;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Envelope {	
	@Expose
	@SerializedName("contents")
	private Contents contents;
	
	@Expose
	@SerializedName("hashkey")
	private String hashkey;
	
	@Expose
	@SerializedName("link_verifier")
	private LinkVerifier linkVerifier;
	
	@Expose
	@SerializedName("signature")
	private Signature signature;
	
	public Envelope() {
	}
	
	public Envelope(Contents contents, LinkVerifier linkVerifier, Signature signature) {
		this.hashkey = Hex.encode(sha256(CBOR.forObject(contents)));
		this.contents = contents;
		this.linkVerifier = linkVerifier;
		this.signature = signature;
	}
	
	public String getHashKey() {
		return hashkey;
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
		return this.hashkey.hashCode();
	}
	
	@Override
	public String toString() {
		return "Envelope(" + 
			"contents=" + contents + ", " +
			"hashkey=" + hashkey.substring(0, 5) + "..., " +
			"signature=" + signature + ", " +	
			"linkVerifier=" + linkVerifier + ", " + 
		")";
	}
}
