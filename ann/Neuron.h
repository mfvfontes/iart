/*
 * Neuron.h
 *
 *  Created on: Mar 17, 2015
 *      Author: ei12183
 */

#ifndef _NEURON_H_
#define _NEURON_H_

#include <cmath>
#include <cstdlib>
#include <vector>

using namespace std;

struct Connection{
	double weight;
	double dweight;
};

class Neuron {

	typedef vector <Neuron> Layer;

public:
	Neuron(unsigned n_outputs, unsigned index);
	void setOutput(double _output);
	double getOutput() const;
	void fforward(Layer &prev_layer);
	void calcOutputGradients(double target);
	void calcHiddenGradients(const Layer &next_layer);
	void updateInputWeights(Layer &prev_layer);
private:
	static double eta; //[0.0..1.0] overall net training rate
	static double alpha; //[0.0..m] multiplier of last weight change (momentum)
	static double transferFunction(double x);
	static double transferFunctionDerivative(double x);
	static double randomWeight(void);

	double sumDOW(const Layer &next_layer);
	double output;
	double gradient;
	unsigned index;

	vector <Connection> output_ws;
};

#endif
