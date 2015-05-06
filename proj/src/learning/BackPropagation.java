package learning;

import java.io.Serializable;
import java.util.ArrayList;
import data.Dataset;
import net.Layer;
import net.NeuralNetwork;
import net.Neuron;

public class BackPropagation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Dataset dataset;
	
	NeuralNetwork network;
	
	public BackPropagation(Dataset _dataset, NeuralNetwork _network){
		dataset = _dataset;
		network = _network;
	}
	
	public void learn(){
		
		ArrayList <Double> obtained_outputs = Dataset.newInstance(dataset).getInputs();
		ArrayList <Double> expected_outputs = dataset.getOutputs();
		ArrayList <Double> errors = new ArrayList <Double> ();
		ArrayList <Double> net_inputs = new ArrayList <Double> ();
		
		/*Randomize every weight of the network*/
		
		network.randomizeWeights();
		
		/* Propagate the inputs forward to compute the outputs */
		
		ArrayList <Layer> layers = network.getLayers();
	
		for(int l = 2; l < layers.size(); l++){
			Layer layer = layers.get(l);
			
			ArrayList <Neuron> neurons = layer.getNeurons();
			
			for(Neuron neuron : neurons){
				neuron.calcIO();
				net_inputs.add(neuron.getInput());
				obtained_outputs.add(neuron.getOutput());
			}
		}
			
		/* Propagate deltas backward from output layer to input layer */
		
		Layer output_layer = layers.get(layers.size() - 1);
		ArrayList <Neuron> output_neurons = output_layer.getNeurons(); 
		
		for(int i = 0; i < output_neurons.size(); i++){
			Neuron neuron = output_neurons.get(i);
			Double derivative = neuron.getTransferFunction().getDerivative(net_inputs.get(i));
			Double error = derivative * (expected_outputs.get(i) - obtained_outputs.get(i));
			
			errors.add(error);
		}
		
		for(int l = layers.size() - 1; l > 1; l--){
			Layer layer = layers.get(l);
			
			ArrayList <Neuron> neurons = layer.getNeurons();
			
			for(int i = 0; i < neurons.size(); i++){
				Neuron neuron = neurons.get(i);
				Double derivative = neuron.getTransferFunction().getDerivative(net_inputs.get(i));
				
				//Still a lot to do...some errors above...
			}
		}
		
		/* Update every weight in network using deltas */
		
	}
}
