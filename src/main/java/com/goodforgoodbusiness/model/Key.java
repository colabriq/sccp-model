package com.goodforgoodbusiness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Key {
	@Expose
	@SerializedName("alg")
	private String algorithm;
	
	@Expose
	@SerializedName("key")
	private String key;
	
	public Key() {
		
	}
	
	public Key(String algorithm, String key) {
		this.algorithm = algorithm;
		this.key = key;
	}
	
	public String getAlgorithm() {
		return algorithm;
	}

	public String getKey() {
		return key;
	}
}
