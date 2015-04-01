package transfer;
import java.io.Serializable;

public class Sigmoid extends TransferFunction implements Serializable{

	private static final long serialVersionUID = 1L;

	private double slope = 1.0;
	
	public Sigmoid(double _slope){
		slope = _slope;
	}
	
	public double getSlope(){
		return slope;
	}
	
	public void setSlope(double _slope){
		slope = _slope;
	}
	
	@Override
	public double getOutput(double net) {
		double den = 1.0 + Math.exp(-slope * net);
        
		output = (1.0 / den);
        
        return output;
	}

	@Override
	public double getDerivative(double net) {
		 // +0.1 is fix for flat spot see http://www.heatonresearch.com/wiki/Flat_Spot
		double derivative = slope * output * (1.0 - output) + 0.1;
		return derivative;
	}

}
