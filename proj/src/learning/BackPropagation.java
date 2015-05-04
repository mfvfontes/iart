package learning;

import java.io.Serializable;
import java.util.ArrayList;
import data.NeuralNetwork;

public class BackPropagation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	ArrayList <Double> inputs;
	ArrayList <Double> outputs;
	
	NeuralNetwork network;
	
	public BackPropagation(ArrayList <Double> _inputs, ArrayList <Double> _outputs, NeuralNetwork _network){
		inputs = _inputs;
		outputs = _outputs;
		network = _network;
	}
	
	public void learn(){
		
		/*Randomize every weight of the network*/
		
		network.randomizeWeights();
		
		/* Propagate the inputs forward to compute the outputs */
		
		/* Propagate deltas backward from output layer to input layer */
		
		/* Update every weight in network using deltas */
	}
}
