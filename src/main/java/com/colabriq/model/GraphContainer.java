package com.colabriq.model;

import org.apache.jena.graph.Graph;

/**
 * A container that contains a Graph
 */
public interface GraphContainer extends AccessibleContainer {
	public Graph asGraph();
}
