package com.goodforgoodbusiness.model;

import static org.apache.jena.graph.NodeFactory.createURI;
import static org.apache.jena.sparql.util.NodeFactoryExtra.createLiteralNode;

import java.util.HashMap;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;

public class TriTupleTest {
	public static void main(String[] args) {
		var map = new HashMap<TriTuple, Boolean>();
		
		TriTuple tt1 = TriTuple.from(
			new Triple(
				createURI("https://twitter.com/ijmad8x"),
				createURI("http://xmlns.com/foaf/0.1/name"),
				createLiteralNode("Ian Maddison", null, "http://www.w3.org/2001/XMLSchema/string")
			)
		);
		
		map.put(tt1, true);
		
		System.out.println(map.containsKey(tt1));
		
		TriTuple tt2 = TriTuple.from(
			new Triple(
				createURI("https://twitter.com/ijmad8x"),
				Node.ANY,
				Node.ANY
			)
		);
		
		map.put(tt2, true);
		
		System.out.println(map.containsKey(tt2));
	}
}
