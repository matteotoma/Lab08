package it.polito.tdp.extflightdelays.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.creaGrafo(4900.0);
		
		Graph<Airport, DefaultWeightedEdge> g = model.getGrafo();
		for(DefaultWeightedEdge e : g.edgeSet()) {
			System.out.println(g.getEdgeSource(e)+ "\n" 
						+ g.getEdgeTarget(e)+"\n"
						+g.getEdgeWeight(e)+"\n");
		}

	}

}
