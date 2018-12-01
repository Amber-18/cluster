package analyzers;

import java.util.ArrayList;

public class Center extends Point{
	
	private double[] center;
	
	/** Constructs a Center object for a cluster
	 * @param center A double[] vector of the center's position*/
	public Center(double[] center) {
		super(center);
	}
	
	/** Recalibrate the center point using all points in this cluster*/
	public void recalibrate(ArrayList<DataPoint> points) {
		
		// number of dimensions in a center point should be the same as normal data points passed in
		double[] sums = new double[this.center.length];
		
		// from every point, add to sum
		for(DataPoint point : points) {
			for(int i = 0; i < sums.length; ++i) {
				sums[i] += point.vector[i];
			}
		}
		
		// divide each sum by number of total points to find average
		for(int i = 0; i < sums.length; ++i) {
			sums[i] = sums[i] / points.size();
		}
		
		// new centerPoint!
		this.center = sums;
		
		
	}

}
