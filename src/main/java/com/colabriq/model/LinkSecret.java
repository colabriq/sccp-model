package com.goodforgoodbusiness.model;

public class LinkSecret extends Key {
	public LinkSecret() {
	}
	
	public LinkSecret(String algorithm, String privateKey) {
		super(algorithm, privateKey);
	}
	
	@Override
	public String toString() {
		return "LinkSecret(" + getAlgorithm() + ", " + getKey().substring(0, 5) + "...)";
	}
}
