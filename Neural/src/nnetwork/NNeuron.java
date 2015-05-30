package nnetwork;

import java.util.ArrayList;

/**
 * Created by João on 29/05/2015.
 */
public class NNeuron {

    private ArrayList<NSynapse> inputs;
    private double output, derivative, sum, error;

    public NNeuron() {
        inputs = new ArrayList<NSynapse>();
        error = 0;

    }

    public void activate() {
        calculateSum();
        output = activation();
        calculateDerivative();

    }

    private double activation() {
        //return 1.0 / (1 + Math.exp(-1.0 * sum));
        return (Math.tanh(sum) + 1)/2.0;
    }

    private void calculateSum() {
        sum = 0;
        for (NSynapse synapse : inputs) {
            sum += synapse.getWeight() * synapse.getSourceNeuron().getOutput();
        }
    }

    private void calculateDerivative() {
        //derivative = output * (1.0 - output);
        derivative = (1 - Math.pow(Math.tanh(sum), 2))/2.0;
    }


    public void addInput (NSynapse input) {
        inputs.add(input);
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getDerivative() {
        return derivative;
    }

    public ArrayList<NSynapse> getInputs() {
        return inputs;
    }

    public void setError(double neuronError) {
        this.error = neuronError;
    }

    public double getError() {
        return error;
    }
}
