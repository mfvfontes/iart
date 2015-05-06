package net;
import java.io.Serializable;
import java.util.ArrayList;

public class Layer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private NeuralNetwork network;
	
	private String label;
	
	ArrayList<Neuron> neurons;
	
	public Layer(int num_neurons){
		neurons = new ArrayList<Neuron>(num_neurons);
	}
	
	public NeuralNetwork getNetwork(){
		return network;
	}
	
	public void setNetwork(NeuralNetwork _network){
		network = _network;
	}
	
	public final ArrayList<Neuron> getNeurons(){
		return neurons;
	}
	
	public String getLabel(){
		return label;
	}
	
	public void setLabel(String _label){
		label = _label;
	}
	
	public Neuron getNeuronAt(int index){
		return neurons.get(index);
	}
	
	public boolean addNeuron(Neuron neuron){
		if(neuron == null)
			throw new IllegalArgumentException("Neuron: null.");
		
		neuron.setLayer(this);
		neurons.add(neuron);
		
		return true;
	}
	
	/*Java doesn't allow default parameter values...*/
	
	public boolean addNeuron(Neuron neuron, int index){
		if(neuron == null)
			throw new IllegalArgumentException("Neuron: null.");
		
		neuron.setLayer(this);
		neurons.add(index, neuron);
		
		return true;
	}
	
	public void removeNeuronAt(int index){
		Neuron neuron = getNeuronAt(index);
		neuron.setLayer(null);
		neurons.remove(index);
	}
	
	public boolean removeNeuron(Neuron neuron){
		if(!neurons.remove(neuron))
			throw new RuntimeException("Neuron doesn't exist.");
		
		return true;
	}
	
	public int indexOf(Neuron neuron){
		return neurons.indexOf(neuron);
	}
	
	public void initWeights(double value){
		for(Neuron neuron : neurons)
			neuron.initWeights(value);
	}
	
	public void reset(){
		for(Neuron neuron : neurons)
			neuron.reset();
	}
}
