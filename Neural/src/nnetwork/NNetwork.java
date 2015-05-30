package nnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by João on 29/05/2015.
 */
public class NNetwork {

    private ArrayList<NLayer> layers;
    private NLayer input, output;

    private DataSet dataSet;

    static private Random random = new Random();

    private double learningRate, characteristicTime, currentEpoch, momentum;

    public NNetwork(double learningRate, double momentum, double characteristicTime) {
        layers = new ArrayList<NLayer>();
        currentEpoch = 0;
        this.learningRate = learningRate;
        this.characteristicTime = characteristicTime;
        this.momentum = momentum;
    }


    public void train(double errorThreshold) {
        double error;
        double sum = 0.0;
        int epoch = 1;
        double average = 25;
        int samples = 25;

        double[] errors = new double[samples];

        do {
            error = backPropagate();

            sum -= errors[epoch % samples];
            errors[epoch % samples]= error;
            sum += errors[epoch % samples];

            if (epoch > samples) {
                average = sum / samples;
            }

            epoch ++;
            currentEpoch = epoch;

            System.out.println("End of epoch with average error = " + average );

        } while (average > errorThreshold);
    }

    public double backPropagate() {
        double error = 0;
        HashMap<NSynapse, Double> synapseNeuronDeltaMap = new HashMap<NSynapse, Double>();
        
        
        // Para cada linha do input fazer o feed forward da rede

        for (int inputIndex = 0; inputIndex < dataSet.getnEntries(); inputIndex++) {
            ArrayList<Double> input = dataSet.inputData.get(inputIndex);
            ArrayList<Double> expectedOutput = dataSet.outputData.get(inputIndex);

            setInputs(inputIndex);
            networkFeedForward();

            // Back Propagate the error
            for (int layerIndex = layers.size() - 1; layerIndex > 0; layerIndex--) {
                NLayer layer = layers.get(layerIndex);
                for (int neuronIndex = 0; neuronIndex < layer.getNeurons().size(); neuronIndex++) {
                    NNeuron neuron = layer.getNeurons().get(neuronIndex);

                    double neuronError = 0;

                    if (layer.isOutput()) {
                        neuronError = neuron.getDerivative() * (neuron.getOutput() - dataSet.outputData.get(inputIndex).get(neuronIndex));
                    } else  {

                        neuronError = neuron.getDerivative();

                        double sum = 0;
                        for (NNeuron nextNeuron : layer.getNextLayer().getNeurons()) {

                            int synapseIndex = 0;
                            boolean found = false;

                            while (synapseIndex < nextNeuron.getInputs().size() && !found) {
                                NSynapse synapse = nextNeuron.getInputs().get(synapseIndex);
                                if (synapse.getSourceNeuron() == neuron) {
                                    sum += (synapse.getWeight() * nextNeuron.getError());
                                    found = true;
                                }
                                synapseIndex ++;
                            }

                        }
                        neuronError *= sum;
                    }
                    neuron.setError(neuronError);
                }
            }

            // Adjust errors

            for (int layerIndex = layers.size() - 1; layerIndex > 0; layerIndex--) {
                NLayer layer = layers.get(layerIndex);

                for (NNeuron neuron : layer.getNeurons()) {
                    for (NSynapse synapse : neuron.getInputs()) {
                        double newLearningRate = characteristicTime > 0 ? learningRate / (1 + (currentEpoch / characteristicTime)) : learningRate;
                        double delta = newLearningRate * neuron.getError() * synapse.getSourceNeuron().getOutput();

                        if (synapseNeuronDeltaMap.get(synapse) != null) {
                            double previousDelta = synapseNeuronDeltaMap.get(synapse);
                            delta += momentum * previousDelta;
                        }

                        synapseNeuronDeltaMap.put(synapse,delta);
                        synapse.setWeight(synapse.getWeight() - delta);

                    }
                }

            }

            // Calculate the error
            double sum = 0;

            for (int outputVarIndex = 0; outputVarIndex < dataSet.getnOutputVars(); outputVarIndex++) {
                double expected = expectedOutput.get(outputVarIndex);
                double actual = output.getNeurons().get(outputVarIndex).getOutput();
                sum += Math.pow(expected - actual, 2);
            }

            error += sum/2.0;
        }


        System.out.println("Error = " + error);
        return error;


    }

    private void setInputs(int entryIndex) {
        if (input != null) {
            ArrayList<NNeuron> neurons = input.getNeurons();
            for (int i = 0; i < neurons.size(); i++) {
                NNeuron neuron = neurons.get(i);
                neuron.setOutput(dataSet.inputData.get(entryIndex).get(i));
            }
        }
    }

    public void buildFromData (DataSet data, int[] nNeuronsLayer) {
        input = new NLayer(null);
        dataSet = data;

        for (int inputVarIndex = 0; inputVarIndex < data.getnInputVars(); inputVarIndex++) {
            input.addNeuron(new NNeuron());
        }

        addLayer(input);

        NLayer previous = input;

        for (int layerIndex = 0; layerIndex < nNeuronsLayer.length; layerIndex++) {
            NLayer layer = new NLayer(previous);
            for (int neuronIndex = 0; neuronIndex < nNeuronsLayer[layerIndex]; neuronIndex++) {
                layer.addNeuron(new NNeuron());
            }
            addLayer(layer);
            previous = layer;
        }

        output = new NLayer(previous);
        for (int outputVarIndex = 0; outputVarIndex < data.getnOutputVars(); outputVarIndex++) {
            output.addNeuron(new NNeuron());
        }

        addLayer(output);

    }

    public void networkFeedForward() {
        for (int layerIndex = 1; layerIndex < layers.size(); layerIndex++) {
            layers.get(layerIndex).feedForward();
        }

    }

    public void addLayer(NLayer layer) {
        layers.add(layer);

        if (layers.size() == 1)
            input = layer;

        else if (layers.size() > 1) {
            NLayer previousLayer = layers.get(layers.size() - 2);
            previousLayer.setNextLayer(layer);
        }

        output = layers.get(layers.size() - 1);
    }

    static public double getStartingWeight() {
        return random.nextDouble();
    }

}
