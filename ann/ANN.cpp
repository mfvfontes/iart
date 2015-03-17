
#include "ANN.h"

ANN::ANN(const vector <unsigned> &topology){
	unsigned nlayers = topology.size();

	for(unsigned l = 0; l < nlayers; l++){
		layers.push_back(Layer());
		unsigned n_outputs = (l == topology.size() - 1) ? 0 : topology[l+1];

		for(unsigned n = 0; n <= topology[l]; n++)
			layers.back().push_back(Neuron(n_outputs));

		//Force the bias node's output value 1.0. It's the last neuron created above
		layers.back().back().setOutput(1.0);
	}
}

void ANN::fforward(const vector<double> &input){

	assert(input.size() == layers[0].size() - 1);

	//Assign the input values into the input neurons

	for(unsigned i = 0; i < input.size(); i++){
		layers[0][i].setOutput(input[i]);
	}

	//Forward propagate

	for(unsigned l = 1; l < layers.size(); l++){
		Layer &prev_layer = layers[l - 1];
		for(unsigned n = 0; n < layers[l].size() - 1; n++){
			layers[l][n].fforward(prev_layer);
		}
	}
}

void ANN::bprop(const vector <double> &target){

	//Calculate overall net error (RMS of output neuron errors)

	Layer &output_layer = layers.back();

	rms = 0.0;

	for(unsigned n = 0; n < output_layer.size() - 1; n++){
		double delta = target[n] - output_layer[n].getOutput();
		rms += delta*delta;
	}

	rms /= output_layer.size() - 1; //get average error squared
	rms = sqrt(rms); //RMS

	//Implement a recent average measurement

	recent_average_error = (recent_average_error * recent_average_smooth_factor + rms) / (recent_average_smooth_factor + 1.0);

	//Calculate output layer gradients

	for(unsigned n = 0; n < output_layer.size(); n++)
		output_layer[n].calcOutputGradients(target[n]);

	//Calculate gradients on hidden layers

	for(unsigned l = layers.size() - 2; l > 0; l--){
		Layer &hidden_layer = layers[l];
		Layer &next_layer = layers[l + 1];

		for(unsigned n = 0; n < hidden_layer.size(); n++)
			hidden_layer[n].calcHiddenGradients(next_layer);
	}

	//For all layers from outputs to first hidden layer, update connection weights

	for(unsigned l = layers.size() - 1; l > 0; l--){
		Layer &layer = layers[l];
		Layer &prev_layer = layers[l - 1];

		for(unsigned n = 0; n < layer.size() - 1; n++)
			layer[n].updateInputWeights(prev_layer);
	}
}

void ANN::getResults(vector <double> &results) const{

	results.clear();

	for(unsigned n = 0; n < layers.back().size(); n++)
		results.push_back(layers.back()[n].getOutput());

}

double ANN::getRecentAverageError() const{
	return recent_average_error;
}
