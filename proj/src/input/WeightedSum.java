package input;


public class WeightedSum extends InputFunction{

	private static final long serialVersionUID = 1L;

    public static double[] getOutput(double[] inputs, double[] weights) {
        double[] output = new double[inputs.length];

        for (int i = 0; i < inputs.length; i++) 
            output[i] += inputs[i] * weights[i];

        return output;
    }
}
