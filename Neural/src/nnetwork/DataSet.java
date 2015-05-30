package nnetwork;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by João on 28/05/2015.
 */
public class DataSet {
    public ArrayList< ArrayList<Double>> inputData, outputData;

    // Needed for data standardization
    private ArrayList<Double> min, max;
    private boolean initialized = false;
    private boolean train;

    private int nEntries, outputVars, nHeaderVars;

    public DataSet(File inputFile, int nOutputVars, int nHeaderVars) {

        // Initializations
        inputData = new ArrayList<ArrayList<Double>>();
        outputData = new ArrayList<ArrayList<Double>>();
        min = new ArrayList<Double>();
        max = new ArrayList<Double>();
        outputVars = nOutputVars;
        nEntries = 0;
        this.nHeaderVars = nHeaderVars;

        readFile(inputFile);
        standardization();

        System.out.println("DataSet initialized");
    }

    private void readFile(File inputFile) {
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = fileReader.readLine()) != null) {
                parseLine(line.split(","));
                nEntries++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String[] lineVars) {
        if (! initialized) {
            for (int i = 0, lineVarsLength = lineVars.length - nHeaderVars; i < lineVarsLength; i++) {
                min.add(Double.MAX_VALUE);
                max.add(Double.MIN_VALUE);
            }

            initialized = true;
        }

        ArrayList<Double> inputVarArray = new ArrayList<Double>();
        for (int inputVarIndex = nHeaderVars; inputVarIndex < lineVars.length - outputVars; inputVarIndex++) {
            double value = Double.parseDouble(lineVars[inputVarIndex]);
            inputVarArray.add(value);

            if (value > max.get(inputVarIndex-nHeaderVars))
                max.set(inputVarIndex-nHeaderVars,value);
            else if (value < min.get(inputVarIndex-nHeaderVars))
                min.set(inputVarIndex-nHeaderVars,value);
        }
        inputData.add(inputVarArray);

        ArrayList<Double> outputVarArray = new ArrayList<Double>();
        for (int outputVarIndex = inputVarArray.size(); outputVarIndex < outputVars + inputVarArray.size(); outputVarIndex++) {
            double value = Double.parseDouble(lineVars[outputVarIndex + nHeaderVars]);
            outputVarArray.add(value);

            if (value > max.get(outputVarIndex))
                max.set(outputVarIndex,value);
            else if (value < min.get(outputVarIndex))
                min.set(outputVarIndex,value);
        }
        outputData.add(outputVarArray);
    }

    public int getnInputVars () { return inputData.get(0).size(); }
    public int getnOutputVars () { return outputData.get(0).size(); }

    public int getnEntries() {
        return nEntries;
    }

    private void standardization() {
        for (ArrayList<Double> line : inputData) {
            for (int i = 0; i < line.size(); i++) {
                Double oldValue = line.get(i);
                Double newValue = (oldValue - min.get(i)) / (max.get(i) - min.get(i));

                if ((max.get(i) - min.get(i)) != 0)
                    line.set(i,newValue);
                else
                    line.set(i,1.0);

//                System.out.println("Replaced var number " + i + " - " + oldValue + " with " + newValue + "\t\t(" + oldValue + " - " + min.get(i) + ") / (" + max.get(i) + " - " + min.get(i) + ")" );

            }
        }

        for (ArrayList<Double> line : outputData) {
            for (int i = 0; i < line.size(); i++) {
                Double oldValue = line.get(i);
                Double newValue = (oldValue - min.get(i+inputData.get(0).size())) / (max.get(i+inputData.get(0).size()) - min.get(i+inputData.get(0).size()));

                if ((max.get(i+inputData.get(0).size()) - min.get(i+inputData.get(0).size())) != 0)
                    line.set(i,newValue);
                else
                    line.set(i,1.0);


//                System.out.println("Replaced " + oldValue + " with " + newValue);
            }
        }
    }
    /*


    private ArrayList< ArrayList<Float>> data;
    private ArrayList<Float> min, max;
    private boolean initialized = false;

    private int inputFields;
    // Number of fields that are expected to be result
    private int resultFields;

    // Number of fields in the data set
    private int dataLength = 0;

    public DataSet(File input, int nVars, int nOutput) {
        this.resultFields = resultFields;
        min = new ArrayList<Float>();
        max = new ArrayList<Float>();

        data = new ArrayList<ArrayList<Float>>();

        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(input));
            String line;
            while ((line = fileReader.readLine()) != null) {
                parseLine(line.split(","));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        standardization();
    }

    private void parseLine(String[] dataStringArray) {
        ArrayList<Float> dataArray = new ArrayList<Float>();

        if (dataLength == 0) {
            dataLength = dataStringArray.length;
            inputFields = dataLength - resultFields;
        }
        else if (dataLength != dataStringArray.length)
            System.err.println("Data Set has inconsistent number of parameters");

        if (! initialized) {
            for (int varIndex = 0; varIndex < dataLength; varIndex++) {
                min.add(Float.MAX_VALUE);
                max.add(Float.MIN_VALUE);
            }

            initialized = true;
        }

        for (int varIndex = 0; varIndex < dataLength; varIndex++) {
            Float value = Float.parseFloat(dataStringArray[varIndex]);
            dataArray.add(value);

            if (value > max.get(varIndex))
                max.set(varIndex,value);
            else if (value < min.get(varIndex))
                min.set(varIndex,value);

        }

        data.add(dataArray);
    }


    */
}
