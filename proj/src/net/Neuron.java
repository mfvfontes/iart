package net;

import java.util.ArrayList;
import java.io.Serializable;
import input.InputFunction;
import input.WeightedSum;
import transfer.TransferFunction;
import transfer.Sigmoid;

public class Neuron implements Serializable{

	private static final long serialVersionUID = 1L;

	private String label;
	
	protected Layer layer;
		
	protected InputFunction inputFunction;
	
	protected TransferFunction transferFunction;
	
	protected ArrayList<Connection> inputConnections;
	
	protected ArrayList<Connection> outputConnections;
	
	protected transient double input = 0;
	
	protected transient double output = 0;
	
	protected transient double error = 0;

	public Neuron(){
		inputFunction = new WeightedSum();
		transferFunction = new Sigmoid();
	}
	
	public double getInput(){
		return input;
	}
	
	public double getOutput(){
		return output;
	}
	
	public void setInput(double _input){
		input = _input;
	}
	
	public void setOutput(double _output){
		output = _output;
	}
	
	public double getError(){
		return error;
	}
	
	public void setError(double _error){
		error = _error;
	}
	
	public String getLabel(){
		return label;
	}
	
	public void setLabel(String _label){
		label = _label;
	}
	
	public void setLayer(Layer _layer){
		layer = _layer;
	}
	
	public InputFunction getInputFunction(){
		return inputFunction;
	}
	
	public void setInputFunction(InputFunction _inputFunction){
		inputFunction = _inputFunction;
	}
	
	public TransferFunction getTransferFunction(){
		return transferFunction;
	}
	
	public void setTransferFunction(TransferFunction _transferFunction){
		transferFunction = _transferFunction;
	}
	
	public ArrayList<Connection> getInputConnections(){
		return inputConnections;
	}
	
	public void setInputConnections(ArrayList <Connection> _inputConnections){
		inputConnections = _inputConnections;
	}
	
	public ArrayList<Connection> getOutputConnections(){
		return outputConnections;
	}

	public void setOutputConnections(ArrayList <Connection> _outputConnections){
		outputConnections = _outputConnections;
	}
	
	public void calcIO(){
		if(inputConnections.size() > 0) //if has any input connections (i.e does not belong to first layer)
			input = inputFunction.getOutput(inputConnections);
		
		output = transferFunction.getOutput(input);
	}
	
	public void initWeights(double value){
		for(Connection con : inputConnections)
			con.getWeight().setValue(value);
	}
	
	public void reset(){
		this.setOutput(0.0);
		this.setInput(0.0);
	}
}
