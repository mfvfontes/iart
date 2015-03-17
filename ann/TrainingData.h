/*
 * TrainingData.h
 *
 *  Created on: Mar 17, 2015
 *      Author: ei12183
 */

#ifndef _TRAININGDATA_H_
#define _TRAININGDATA_H_

#include <vector>
#include <string>
#include <sstream>
#include <fstream>

using namespace std;

class TrainingData {
public:
	TrainingData(const string &filename);
	bool isEof(void);
	void getTopology(vector <unsigned> &topology);

	//Returns the number of input values read from file
	unsigned getNextInputs(vector <double> &inputs);
	unsigned getTargetOutputs(vector <double> &targets);
private:
	ifstream file;
};

#endif /* TRAININGDATA_H_ */
