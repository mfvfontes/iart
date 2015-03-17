/*
 * main.cpp
 *
 *  Created on: Mar 17, 2015
 *      Author: ei12183
 */
#include <iostream>
#include <vector>
#include <cassert>
#include "ANN.h"
#include "TrainingData.h"

using namespace std;

void show(string label, vector <double> &v){

	cout << label << " ";

	for(unsigned i = 0; i < v.size(); i++)
		cout << v[i] << " ";

	cout << endl;
}

int main(){
	TrainingData data("data.txt");

	vector <unsigned> topology;
	data.getTopology(topology);

	ANN network(topology);

	vector <double> inputs, targets, results;

	int step = 0;

	while(!data.isEof()){
		step++;

		cout << endl << "Step " << step;

		if(data.getNextInputs(inputs) != topology[0])
			break;

		show(": Inputs:", inputs);
		network.fforward(inputs);

		//Collect the net's actual output results

		network.getResults(results);
		show("Outputs:", results);

		//Train the network

		data.getTargetOutputs(targets);
		show("Targets:", targets);
		assert(targets.size() == topology.back());

		network.bprop(targets);

		cout << "Net recent average error: " << network.getRecentAverageError() << endl;
	}

	return 0;
}



