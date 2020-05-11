package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMapAirports;
	private ExtFlightDelaysDAO dao;
	private List<Collegamento> collegamenti;
	
	public Model() {
		idMapAirports = new HashMap<>();
		collegamenti = new LinkedList<>();
		dao = new ExtFlightDelaysDAO();
		dao.loadAllAirports(idMapAirports);
	}
	
	public void creaGrafo(Double peso) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// creazione vertici
		Graphs.addAllVertices(this.grafo, idMapAirports.values());
		
		for(Collegamento c: dao.getCollegamenti(peso, idMapAirports)) {
			DefaultWeightedEdge edge = grafo.getEdge(c.getOrigin(), c.getDestination());
			if(edge == null) {
				Graphs.addEdge(grafo, c.getOrigin(), c.getDestination(), c.getDistance());
			} 
			else {
				double pesoVecchio = grafo.getEdgeWeight(edge);
				double newPeso = (pesoVecchio + c.getDistance())/2;
				grafo.setEdgeWeight(edge, newPeso);
			}
		}
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Collegamento> getCollegamenti() {
		return collegamenti;
	}

	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
}