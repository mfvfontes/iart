import java.io.Serializable;
import java.util.ArrayList;

public class NeuralNetwork implements Serializable{

	private static final long serialVersionUID = 1L;

	ArrayList<Layer> layers;
	
	ArrayList<Neuron> inputNeurons;
	
	ArrayList<Neuron> outputNeurons;
	
	public NeuralNetwork(){
		layers = new ArrayList<Layer>();
		inputNeurons = new ArrayList<Neuron>();
		outputNeurons = new ArrayList<Neuron>();
	}
	
	public ArrayList<Layer> getLayers(){
		return layers;
	}
	
	public ArrayList<Neuron> getInputNeurons(){
		return inputNeurons;
	}
	
	public ArrayList<Neuron> getOutputNeurons(){
		return outputNeurons;
	}
	
	public boolean addLayer(Layer layer){
		if (layer == null)
			throw new IllegalArgumentException("Layer: null");
		
		layers.add(layer);
		layer.setNetwork(this);
		
		return true;
	}
	
	public boolean removeLayer(Layer layer){
		if(!layers.remove(layer))
			throw new RuntimeException("Layer doesn't exist.");
		
		return true;
	}
	
	public boolean removeLayerAt(int index){
		Layer layer = layers.get(index);
		
		return removeLayer(layer);
	}
	
	public void reset(){
		for(Layer layer: layers)
			layer.reset();
	}
}
