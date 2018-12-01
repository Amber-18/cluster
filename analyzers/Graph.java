package analyzers;

import java.util.ArrayList;
import java.util.Random;

public class Graph {
	
	private int calibrationMeter;
	
	private ArrayList<Cluster> clusters = new ArrayList<>();
	
	private ArrayList<DataPoint> allPoints = new ArrayList<>();
	
	/** Constructs a Graph object
	 * GraphLimits are the same for every graphical dimensions
	 * GraphLimits are limits on every value in a data point
	 * @param numClusters The number of clusters to be expected
	 * @param lowerGraphLimit The lower limit of this graph, used for correct spacing of cluster centers
	 * @param upperGraphLimit The upper limit of this graph, used for correct spacing of the cluster centers
	 * @param numDimensions The number of dimensions this graph, and the dataPoints, will have*/
	public Graph(int numClusters, double lowerGraphLimit, double upperGraphLimit, int numDimensions) {
		this.clusters = new ArrayList<>();
		this.allPoints = new ArrayList<>();
		this.calibrationMeter = 0;
		Random rand = new Random();
		ArrayList<Double> potentials = new ArrayList<>();
		
		// calculate the lower potential value for a center evenly spaced within the graph
		double range = upperGraphLimit - lowerGraphLimit;
		range = range / ((double)numClusters + 1.0);
		
		// calculate all potential values and add them to a list
		while(range < upperGraphLimit) {
			potentials.add(new Double(range));
			range += range;
		}
		
		// for every center point
		for(int i = 0; i < numClusters; ++i) {
			// for every dimension that center point has
			double[] centerPointVector = new double[numDimensions];
			for(int j = 0; j < numDimensions; ++j) {
				centerPointVector[j] = potentials.get(rand.nextInt(potentials.size()));
			}
			
			// hope that the centers are far enough apart!!
			clusters.add(new Cluster(new Center(centerPointVector)));
		}
		
		
	}
	
	public void add(DataPoint point) {
		allPoints.add(point); // add points to set of all points

		calibratePoint(point);
		
		this.calibrationMeter += 1;
		if(this.calibrationMeter == 5) {
			this.calibrationMeter = 0;
			calibration();
		}
		
	}

	private void calibration() {
		
		for(Cluster cluster : this.clusters) {
			cluster.recalibrate();
		}
		
		for(DataPoint point : allPoints) {
			calibratePoint(point);
		}
		
	}

	private void calibratePoint(DataPoint point) {
		double distance;
		double minimum = 1000000000;
		int j = 0;
		
		// iterate through all clusters to find which center has least distance to this point
		for(int i = 0; i < this.clusters.size(); ++i) {
			Cluster cluster = this.clusters.get(i);
			distance = point.distanceFrom(cluster.getCenter());
			
			if(distance < minimum) {
				j = i;
			}
		}
		
		// add point to the cluster whose center was least distant
		this.clusters.get(j).addPoint(point);
		
	}

}
