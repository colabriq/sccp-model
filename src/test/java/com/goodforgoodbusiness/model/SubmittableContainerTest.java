package com.goodforgoodbusiness.model;

import static org.apache.jena.sparql.util.NodeFactoryExtra.createLiteralNode;

import org.apache.jena.graph.Triple;

import com.goodforgoodbusiness.shared.encode.JSON;

public class SubmittableContainerTest {
	public static void main(String[] args) {
		var container1 = new SubmittableContainer();
		container1.added(
			new Triple(
				createLiteralNode("a", null, null),
				createLiteralNode("b", null, null),
				createLiteralNode("c", null, null)
			)
		);
		
		var json = JSON.encode(container1).toString();
		System.out.println(json);
		
		var container2 = JSON.decode(json, SubmittableContainer.class);
		System.out.println(container2);
		
	}
}
