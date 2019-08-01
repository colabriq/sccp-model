package com.colabriq.model;

import java.util.stream.Stream;

import org.apache.jena.graph.Triple;

import com.colabriq.shared.treesort.TreeNode;

public interface AccessibleContainer extends Container, TreeNode<String> {
	public Stream<Triple> getRemoved();
	public Stream<Triple> getAdded();
	public Stream<Triple> getTriples();
}
