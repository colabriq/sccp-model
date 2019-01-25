package com.goodforgoodbusiness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitResult {
	@Expose
	@SerializedName("id")
	private String id;
	
	public SubmitResult() {
	}
	
	public String getId() {
		return id;
	}
}
