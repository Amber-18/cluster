package analyzers;

public class DataPoint extends Point{
	
	private double answer;
	
	/**@param vector The vector
	 * @param distance Distance from center point of group this belongs to
	 * @param answer What this data point actually is, to be used to see if the analysis ran correctly*/
	public DataPoint(double[] vector, double answer) {
		super(vector);
		
		this.answer = answer;
	}
	
	public double getAnswer() {
		return this.answer;
	}
	
	public double[] getVector() {
		return this.vector;
	}

}
