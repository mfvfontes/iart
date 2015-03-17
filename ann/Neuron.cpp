/*
 * Neuron.cpp
 *
 *  Created on: Mar 17, 2015
 *      Author: ei12183
 */

#include "Neuron.h"

double Neuron::eta = 0.15;
double Neuron::alpha = 0.5;

Neuron::Neuron(unsigned n_outputs, unsigned _index) {
	for (unsigned i = 0; i < n_outputs; i++){
		output_ws.push_back(Connection());
		output_ws.back().weight = randomWeight();
	}

	index = _index;
}

void Neuron::fforward(Layer &prev_layer){
	double sum = 0.0;

	for(unsigned n = 0; n < prev_layer.size(); n++){
		sum += prev_layer[n].output * prev_layer[n].output_ws[index].weight;
	}

	output = transferFunction(sum);
}

void Neuron::updateInputWeights(Layer &prev_layer){

	//The weights to be updated are in the Connection container in the neurons in the preceeding layer

	for(unsigned n = 0; n < prev_layer.size(); n++){
		Neuron &neuron = prev_layer[n];
		double old_dweight = neuron.output_ws[index].dweight;

		double new_dweight = eta*neuron.getOutput()*gradient + alpha*old_dweight;

		neuron.output_ws[index].dweight = new_dweight;
		neuron.output_ws[index].weight += new_dweight;
	}
}

double Neuron::sumDOW(const Layer &next_layer){
	double sum = 0.0;

	//Sum the contributions of the errors at the nodes we feed

	for(unsigned n = 0; n < next_layer.size() - 1; n++)
		sum += output_ws[n].weight * next_layer[n].gradient;

	return sum;
}

void Neuron::calcOutputGradients(double target){
	double delta = target - output;
	gradient = delta * transferFunctionDerivative(output);
}

void Neuron::calcHiddenGradients(const Layer &next_layer){
	double dow = sumDOW(next_layer);
	gradient = dow * transferFunctionDerivative(output);
}

void Neuron::setOutput(double _output){
	output = _output;
}

double Neuron::getOutput(void) const{
	return output;
}

double Neuron::transferFunction(double x){
	return (1 + (1 + exp(-x)));
}

double Neuron::transferFunctionDerivative(double x){
	double tfunction = transferFunction(x);

	return (tfunction*(1 - tfunction));
}

double Neuron::randomWeight(){
	return rand() / double(RAND_MAX);
}
