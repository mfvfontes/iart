package error;

import java.io.Serializable;

public class MeanSquaredError implements ErrorFunction, Serializable{

	private static final long serialVersionUID = 1L;
	
	private transient double n;
    private transient double errorSum;
    
	public MeanSquaredError(double n){
		this.n = n;
	}
	
	@Override
	public void reset(){
		errorSum = 0.0;
	}
	
	@Override
	public double getTotalError(){
		return errorSum / n;
	}
	
	
}
