package nnetwork;

/**
 * Created by João on 29/05/2015.
 */
public class NSynapse {

    private NNeuron sourceNeuron;
    private double weight;

    public NSynapse(NNeuron sourceNeuron) {
        this.sourceNeuron = sourceNeuron;
        weight = NNetwork.getStartingWeight();
    }

    public NNeuron getSourceNeuron() {
        return sourceNeuron;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
