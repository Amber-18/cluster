package analyzers;

public class DataPoint extends Point{
	
	/**@param vector The vector
	 * @param distance Distance from center point of group this belongs to*/
	public DataPoint(double[] vector) {
		super(vector);
	}
	
	public double[] getVector() {
		return this.vector;
	}

}
