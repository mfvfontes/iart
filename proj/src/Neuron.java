import input.InputFunction;

import java.io.Serializable;

import transfer.TransferFunction;

public class Neuron implements Serializable{

	private static final long serialVersionUID = 1L;

	private String label;
	
	protected Layer layer;
		
	protected InputFunction inputFunction;
	
	protected TransferFunction transferFunction;
	
	protected transient double input = 0;
	
	protected transient double output = 0;
	
	protected transient double error = 0;

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
	
	public void reset(){
		this.setOutput(0.0);
		this.setInput(0.0);
	}
}
