package com.goodforgoodbusiness.model;

public class EncryptedPointer {
	private final String data;

	public EncryptedPointer(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	@Override
	public int hashCode() {
		return data.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (o instanceof EncryptedPointer) {
			return data.equals(((EncryptedPointer)o).data);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "EncryptedPointer(" + data.substring(0, 10) + "...)";
	}
}
