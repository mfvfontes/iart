package nnetwork;

import java.io.File;

/**
 * Created by João on 29/05/2015.
 */
public class NetworkTest {

    public static void main(String[] args) {
        DataSet set = new DataSet(new File("data/train_data.txt"),2,1);

        //double learningRate, double momentum, double characteristicTime
        NNetwork network = new NNetwork(0.7, .5, 0);

        int[] nNeurons = {30,20};

        network.buildFromData(set,nNeurons);

        network.train(0.005);

        System.out.println(set);
    }
}
