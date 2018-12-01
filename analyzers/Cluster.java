package analyzers;

import java.util.ArrayList;
import java.util.Arrays;

public class Cluster {
	
	private ArrayList<DataPoint> points;
	
	private Center center;
	
	/** Construct a cluster
	 * @param center The center point of this cluster*/
	public Cluster(Center center) {
		this.points = new ArrayList<>();
		this.center = center;
	}
	
	/** Return the Center of this cluster*/
	public Center getCenter() {
		return this.center;
	}
	
	/** Add a point to this cluster*/
	public void addPoint(DataPoint point) {
		this.points.add(point);
		
	}
	
	/** Calibrate the center point using all points in this cluster*/
	public void recalibrate() {
		
		if(this.points.size() == 0) {
			return;
		} else if(this.points.size() == 1) {
			this.center = new Center(this.points.get(0).getVector());
		}
		
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
	}
	
	/** Clears all points that are in this cluster, 
	 * this cluster will now be empty except for having a center point*/
	public void clear() {
		this.points.clear();
		
	}

}
