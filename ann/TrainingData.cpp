/*
 * TrainingData.cpp
 *
 *  Created on: Mar 17, 2015
 *      Author: ei12183
 */

#include "TrainingData.h"

TrainingData::TrainingData(const string &filename) {
	file.open(filename.c_str());
}

bool TrainingData::isEof(void){
	return file.eof();
}

void TrainingData::getTopology(vector <unsigned> &topology){
	string line;
	string label;

	getline(file, line);
	stringstream ss(line);
	ss >> label;

	if(this->isEof() || label.compare("topology:") != 0)
		return;

	while(!ss.eof()){
		unsigned n;
		ss >> n;
		topology.push_back(n);
	}
}

unsigned TrainingData::getNextInputs(vector <double> &inputs){
	inputs.clear();

	string line;
	string label;

	getline(file, line);
	stringstream ss(line);

	ss >> label;

	if(label.compare("in:") == 0){
		double value;
		while(ss >> value)
			inputs.push_back(value);
	}

	return inputs.size();
}

unsigned TrainingData::getTargetOutputs(vector <double> &targets){
	targets.clear();

	string line;
	string label;

	getline(file, line);
	stringstream ss(line);

	ss >> label;

	if(label.compare("out:") == 0){
		double value;
		while(ss >> value)
			targets.push_back(value);
	}

	return targets.size();
}
