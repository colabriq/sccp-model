package com.goodforgoodbusiness.model;

import java.util.stream.Stream;

import org.apache.jena.graph.Triple;

import com.goodforgoodbusiness.shared.treesort.TreeNode;

public interface AccessibleClaim extends Claim, TreeNode<String> {
	public Stream<Triple> getRemoved();
	public Stream<Triple> getAdded();
	public Stream<Triple> getTriples();
}
