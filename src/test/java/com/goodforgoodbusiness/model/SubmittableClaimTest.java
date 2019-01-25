package com.goodforgoodbusiness.model;

import static org.apache.jena.sparql.util.NodeFactoryExtra.createLiteralNode;

import org.apache.jena.graph.Triple;

import com.goodforgoodbusiness.shared.encode.JSON;

public class SubmittableClaimTest {
	public static void main(String[] args) {
		var claim1 = new SubmittableClaim();
		claim1.added(
			new Triple(
				createLiteralNode("a", null, null),
				createLiteralNode("b", null, null),
				createLiteralNode("c", null, null)
			)
		);
		
		var json = JSON.encode(claim1).toString();
		System.out.println(json);
		
		var claim2 = JSON.decode(json, SubmittableClaim.class);
		System.out.println(claim2);
		
	}
}
