package com.goodforgoodbusiness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pointer {	
	@Expose
	@SerializedName("claim_id")
	private String claimId;
	
	@Expose
	@SerializedName("claim_key")
	private String claimKey;
	
	@Expose
	@SerializedName("nonce")
	private long nonce;
	
	public Pointer() {
	}
	
	public Pointer(String claimId, String claimKey, long nonce) {
		this.claimId = claimId;
		this.claimKey = claimKey;
		this.nonce = nonce;
	}

	public String getClaimId() {
		return claimId;
	}

	public String getClaimKey() {
		return claimKey;
	}
	
	public long getNonce() {
		return nonce;
	}
	
	@Override
	public int hashCode() {
		return claimId.hashCode() ^ claimKey.hashCode() ^ Long.hashCode(nonce);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (o instanceof Pointer) {
			var p = (Pointer)o;
			return claimId.equals(p.claimId) && claimKey.equals(p.claimKey) && (nonce == p.nonce);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Pointer(" + claimId + ")";
	}
}
