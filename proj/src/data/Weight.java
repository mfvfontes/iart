package data;
import java.io.Serializable;

public class Weight implements Serializable{

	private static final long serialVersionUID = 1L;

	protected double value;
	
	public Weight(double _value){
		setValue(_value);
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double _value){
		value = _value;
	}
}
