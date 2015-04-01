package transfer;
import java.io.Serializable;

abstract public class TransferFunction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected double output;
	
	abstract public double getOutput(double net);
	
	abstract public double getDerivative(double net); 
	
}
