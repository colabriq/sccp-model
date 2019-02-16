package com.goodforgoodbusiness.model;

import java.util.Collection;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.goodforgoodbusiness.shared.encode.Hex;

public interface Claim {
	public String getId();
	public Collection<? extends Link> getLinks();
	
	/** Returns the SecretKey you can use for AES encryption of contents */
	default public SecretKey getConvergentKey() {
		var hashBytes = Hex.decode(getId());
		
		if (hashBytes.length < 32) {
			throw new UnsupportedOperationException();
		}
		
		// truncate the hash
		// this is the normal way to shorten hashes that are too long
		// e.g. SHA-384 truncates SHA-512
		var key = new byte[32];
		System.arraycopy(hashBytes, 0, key, 0, 32);
		
		return new SecretKeySpec(key, "AES");
	}
}
