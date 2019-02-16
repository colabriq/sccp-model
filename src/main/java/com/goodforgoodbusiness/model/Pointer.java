package com.goodforgoodbusiness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pointer {
	@Expose
	@SerializedName("container_id")
	private String containerId;
	
	@Expose
	@SerializedName("container_key")
	private String containerKey;
	
	@Expose
	@SerializedName("nonce")
	private long nonce;
	
	public Pointer() {
	}
	
	public Pointer(String containerId, String containerKey, long nonce) {
		this.containerId = containerId;
		this.containerKey = containerKey;
		this.nonce = nonce;
	}
	
	public String getContainerId() {
		return containerId;
	}

	public String getContainerKey() {
		return containerKey;
	}
	
	public long getNonce() {
		return nonce;
	}
	
	@Override
	public int hashCode() {
		return containerId.hashCode() ^ containerKey.hashCode() ^ Long.hashCode(nonce);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (o instanceof Pointer) {
			var p = (Pointer)o;
			return containerId.equals(p.containerId) && containerKey.equals(p.containerKey) && (nonce == p.nonce);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Pointer(" + containerId + ")";
	}
}
