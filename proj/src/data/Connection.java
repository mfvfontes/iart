package data;
import java.io.Serializable;

public class Connection implements Serializable{

	private static final long serialVersionUID = 1L;

	Neuron srcNeuron;
	
	Neuron destNeuron;
	
	Weight weight;
	
	public Connection(Neuron _srcNeuron, Neuron _destNeuron, Weight _weight){
		setSrcNeuron(_srcNeuron);
		setDestNeuron(_destNeuron);
		setWeight(_weight);
	}
	
	public Neuron getSrcNeuron(){
		return srcNeuron;
	}
	
	public void setSrcNeuron(Neuron _srcNeuron){
		if(_srcNeuron == null)
			throw new IllegalArgumentException("Source Neuron: null.");
		
		srcNeuron = _srcNeuron;
	}
	
	public Neuron getDestNeuron(){
		return destNeuron;
	}
	
	public void setDestNeuron(Neuron _destNeuron){
		if(_destNeuron == null)
			throw new IllegalArgumentException("Destination Neuron: null.");
		
		destNeuron = _destNeuron;
	}
	
	public Weight getWeight(){
		return weight;
	}
	
	public void setWeight(Weight _weight){
		if(_weight == null)
			throw new IllegalArgumentException("Weight: null");
		
		weight = _weight;
	}
	
	public double getWeightedInput(){
		return (srcNeuron.getOutput() * weight.getValue());
	}
}
