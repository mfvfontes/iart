package transfer;
import java.io.Serializable;

public class Tanh extends TransferFunction implements Serializable {

	private static final long serialVersionUID = 1L;

	private double slope = 2.0;
	
	public Tanh(double _slope){
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
		double e_x = Math.exp(slope * net);                
        output = (e_x - 1d) / (e_x + 1d);
        
        return output;
	}

	@Override
	public double getDerivative(double net) {
		return (1.0 - output * output);
	}
	
}
