/*
 * ANN.h
 *
 *  Created on: Mar 17, 2015
 *      Author: ei12183
 */

#ifndef _ANN_H_
#define _ANN_H_

#include <vector>
#include <cassert>
#include "Neuron.h"

using namespace std;

typedef vector <Neuron> Layer;

class ANN{

public:
	ANN(const vector <unsigned> &topology);

	void fforward(const vector<double> &input);
	void bprop(const vector<double> &target);
	void getResults(vector<double> &result) const;

	double getRecentAverageError() const;
private:
	vector <Layer> layers;
	double rms;
	double recent_average_error;
	double recent_average_smooth_factor;
};

#endif
