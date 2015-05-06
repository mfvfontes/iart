package input;

import java.util.ArrayList;
import net.Connection;

public class WeightedSum extends InputFunction{

	private static final long serialVersionUID = 1L;

	public double getOutput(ArrayList <Connection> inputs){
		
		double output = 0.0;
		
		for(Connection con : inputs)
			output += con.getWeightedInput();
		
		return output;
		
	}
	
    public static double[] getOutput(double[] inputs, double[] weights) {
        double[] output = new double[inputs.length];

        for (int i = 0; i < inputs.length; i++) 
            output[i] += inputs[i] * weights[i];

        return output;
    }
}
