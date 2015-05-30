package nnetwork;

import java.io.File;

/**
 * Created by João on 29/05/2015.
 */
public class NetworkTest {

    public static void main(String[] args) {
        DataSet set = new DataSet(new File("data/train_data_new.txt"),1,1);

        //double learningRate, double momentum, double characteristicTime
        NNetwork network = new NNetwork(0.1, 0.7, 0);

        int[] nNeurons = {18};

        network.buildFromData(set,nNeurons);

        network.train(1000); //number of iterations

        DataSet test_set = new DataSet(new File("data/test_data.txt"), 1, 1);

        network.setDataSet(test_set);

        network.test();

        System.out.println(set);
    }
}
