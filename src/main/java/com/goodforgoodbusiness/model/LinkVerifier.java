package com.goodforgoodbusiness.model;

public class LinkVerifier extends Key {	
	public LinkVerifier() {
	}
	
	public LinkVerifier(String algorithm, String publicKey) {
		super(algorithm, publicKey);
	}
	
	@Override
	public String toString() {
		return "LinkVerifier(" + getAlgorithm() + ", " + getKey().substring(0, 5) + "...)";
	}
}
