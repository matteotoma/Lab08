/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Collegamento;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	txtResult.clear();
    	String s = distanzaMinima.getText();
    	double peso;
    	try {
    		peso = Double.parseDouble(s);
    	}
    	catch(Exception e) {
    		txtResult.appendText("Devi inserire un numero!");
    		return;
    	}
    	model.creaGrafo(peso);
    	txtResult.appendText("Grafo creato con " + model.nVertici() + " vertici e " + model.nArchi() +" archi\n");
    	/*
    	for(Collegamento c: model.getCollegamenti()) {
    		txtResult.appendText(c.getOrigin().getAirportName() + " " + c.getDestination().getAirportName() + 
    				" " + c.getDistance()+"\n");
    	}
    	*/
    	Graph<Airport, DefaultWeightedEdge> g = model.getGrafo();
    	txtResult.appendText("Elenco archi del grafo:\n");
		for(DefaultWeightedEdge e : g.edgeSet()) {
			txtResult.appendText(g.getEdgeSource(e).getAirportName() + " --- " + 
								 g.getEdgeTarget(e).getAirportName() + " " +
								 g.getEdgeWeight(e)+"\n");
		}    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
