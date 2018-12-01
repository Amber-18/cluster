package analyzers;

import java.util.ArrayList;

public class Cluster {
	
	private ArrayList<DataPoint> points;
	
	private Center center;

	public Cluster(Center center) {
		this.points = new ArrayList<>();
		this.center = center;
	}
	
	public Center getCenter() {
		return this.center;
	}
	
	public void addPoint(DataPoint point) {
		this.points.add(point);
		
	}
	
	/** Calibrate the center point using all points in this cluster*/
	public void recalibrate() {
		
		// number of dimensions in a center point should be the same as normal data points passed in
		double[] sums = new double[this.center.getVector().length];
		
		// from every point, add to sum
		for(DataPoint point : this.points) {
			for(int i = 0; i < sums.length; ++i) {
				sums[i] += point.vector[i];
			}
		}
		
		// divide each sum by number of total points to find average
		for(int i = 0; i < sums.length; ++i) {
			sums[i] = sums[i] / this.points.size();
		}
		
		// new centerPoint!
		this.center = new Center(sums);
		
		// also empty list of all points, we need to re-calibrate all the other points also!
		this.points.clear();
	}
	
	public boolean contains(DataPoint point) {
		return this.points.contains(point);
	}
	
	public boolean remove(DataPoint point) {
		return this.points.remove(point);
	}

}
