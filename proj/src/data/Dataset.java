package data;

import java.util.ArrayList;

/*
 * The inputs and outputs are ArrayList <ArrayList <Double>> 
 * 
 */

public class Dataset {

	ArrayList <Double> inputs;
	ArrayList <Double> outputs;
	
	public Dataset(ArrayList <Double> _inputs, ArrayList <Double> _outputs){
		inputs = _inputs;
		outputs = _outputs;
	}
	
	public ArrayList <Double> getInputs(){
		return inputs;
	}
	
	public void setInputs(ArrayList <Double> _inputs){
		inputs = _inputs;
	}
	
	public ArrayList <Double> getOutputs(){
		return outputs;
	}
	
	public void setOutputs(ArrayList <Double> _outputs){
		outputs = _outputs;
	}
	
	public static Dataset newInstance(Dataset aDataset){
		return new Dataset(aDataset.getInputs(), aDataset.getOutputs());
	}
	
}
