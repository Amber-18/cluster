package analyzers;

public abstract class Point {
	
	protected double[] vector;
	
	protected int id;
	
	/**@param vector The vector
	 * @param distance Distance from center point of group this belongs to*/
	public Point(double[] vector) {
		this.vector = vector;
	}
	
	public double[] getVector() {
		return this.vector;
	}
	
	public void set(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	/** Calculate distance from this point to another data point
	 * @param point2 the other data point*/
	protected double distanceFrom(Point point2) {
		double[] thatVector = point2.getVector();
		double distance = 0;
		double sum = 0;
		
		for(int i = 0; i < thatVector.length; ++i) {
			distance = (this.vector[i] - thatVector[i]);
			distance = distance * distance;
			sum += distance;
		}
		
		distance = Math.sqrt(sum);
		
		return distance;
		
	}

}
