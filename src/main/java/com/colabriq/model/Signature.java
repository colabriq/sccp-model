package com.goodforgoodbusiness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signature {
	@Expose
	@SerializedName("did")
	private String did;
	
	@Expose
	@SerializedName("alg")
	private String algorithm;
	
	@Expose
	@SerializedName("sig")
	private String signature;
	
	public Signature() {
		
	}
	
	public Signature(String did, String algorithm, String signature) {
		this.did = did;
		this.algorithm = algorithm;
		this.signature = signature;
	}
	
	public String getDID() {
		return did;
	}
	
	@Override
	public String toString() {
		return "Signature(" + did + ", " + algorithm + ", " + signature.substring(0, 5) + "...)";
	}
}
