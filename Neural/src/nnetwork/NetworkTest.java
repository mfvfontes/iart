package nnetwork;

import java.io.File;

/**
 * Created by João on 29/05/2015.
 */
public class NetworkTest {

    public static void main(String[] args) {
        DataSet set = new DataSet(new File("data/test_data.txt"),1,1);

        NNetwork network = new NNetwork(0.1, 0.9, 0);

        int[] nNeurons = {10};

        network.buildFromData(set,nNeurons);

        network.train(0.1);

        System.out.println(set);
    }
}
