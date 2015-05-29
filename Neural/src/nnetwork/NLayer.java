package nnetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João on 29/05/2015.
 */
public class NLayer {

    private ArrayList<NNeuron> neurons;
    private NLayer previousLayer, nextLayer;

    public NLayer() {
        neurons = new ArrayList<NNeuron>();
        previousLayer = null;
    }

    public NLayer(NLayer previousLayer) {
        neurons = new ArrayList<NNeuron>();
        this.previousLayer = previousLayer;
    }

    public void addNeuron(NNeuron neuron) {
        neurons.add(neuron);

        if (previousLayer != null) {
            for(NNeuron previousNeuron : previousLayer.getNeurons()) {
                neuron.addInput(new NSynapse(previousNeuron));
            }
        }

    }

    public void feedForward() {
        for (NNeuron neuron : neurons) {
            neuron.activate();
        }
    }


    public void setPreviousLayer(NLayer previousLayer) {
        this.previousLayer = previousLayer;
    }

    public void setNextLayer(NLayer nextLayer) {
        this.nextLayer = nextLayer;
    }

    public ArrayList<NNeuron> getNeurons() {
        return neurons;
    }

    public boolean isOutput () {
        return (nextLayer == null);
    }

    public NLayer getNextLayer() {
        return nextLayer;
    }
}
