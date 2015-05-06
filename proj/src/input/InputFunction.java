package input;

import java.io.Serializable;
import java.util.ArrayList;
import net.Connection;

abstract public class InputFunction implements Serializable {

	private static final long serialVersionUID = 1L;

	abstract public double getOutput(ArrayList <Connection> inputs);
	
}
