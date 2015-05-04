package util;

public class RangedRandomizer extends WeightsRandomizer {

	private double min;
	private double max;
	
	public RangedRandomizer(double _min, double _max){
		min = _min;
		max = _max;
	}
	
	@Override
	public double nextRandomDouble(){
		return (max - min)*generator.nextDouble() + min;
	}
	
}
