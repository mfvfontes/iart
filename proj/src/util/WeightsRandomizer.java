package util;

import java.util.Random;

import net.Connection;
import net.Layer;
import net.NeuralNetwork;
import net.Neuron;

public class WeightsRandomizer {
	
	protected Random generator;
	
	public WeightsRandomizer(){
		generator = new Random();
	}
	
	public WeightsRandomizer(Random _generator){
		generator = _generator;
	}
	
	public void randomize(NeuralNetwork network){
		for(Layer layer : network.getLayers())
			randomize(layer);
	}
	
	public void randomize(Layer layer){
		for(Neuron neuron : layer.getNeurons())
			randomize(neuron);
	}
	
	public void randomize(Neuron neuron){
		for(Connection con : neuron.getInputConnections())
			con.getWeight().setValue(nextRandomDouble());
	}
	
	public double nextRandomDouble(){
		return generator.nextDouble();
	}
	
}
