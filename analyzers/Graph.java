package analyzers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Graph {
	
	private int calibrationMeter;
	
	private ArrayList<Cluster> clusters = new ArrayList<>();
	
	private ArrayList<DataPoint> allPoints = new ArrayList<>();
	
	/** Constructs a Graph object and randomizes the center for each cluster created by the graph
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
			clusters.add(new Cluster(new Center(centerPointVector), i));
		}
		
		
	}
	
	/** Add a point to this graph, add that point to a cluster, and increment the calibrationMeter*/
	public void add(DataPoint point) {
		allPoints.add(point); // add points to set of all points
		
		calibratePoint(point);
		
		this.calibrationMeter += 1;
		if(this.calibrationMeter % 5 == 0) {
			this.calibrationMeter = 0;
			calibration();
		}
		
	}
	
	/** Returns a list of the centers of the clusters*/
	public ArrayList<Center> getCenters() {
		ArrayList<Center> centers = new ArrayList<>();
		
		for(Cluster cluster : this.clusters) {
			centers.add(cluster.getCenter());
		}
		
		return centers;
	}
	
	/** Calibrates the entire graph, called by the calibration meter
	 * Calibrates the center of each cluster
	 * Calibrates each point in the graph
	 * And then calibrates the centers, again*/
	private void calibration() {
		
		for(Cluster cluster : this.clusters) {
			cluster.recalibrate();
			cluster.clear();
		}
		
		for(DataPoint point : allPoints) {
			calibratePoint(point);
		}
		
		for(Cluster cluster : this.clusters) {
			cluster.recalibrate();
		}
		
	}
	
	/** Takes a single point and add it the cluster whose center this point is closest to*/
	private void calibratePoint(DataPoint point) {
		double distance;
		double minimum = 1000000000;
		int indexOfMin = 0;
		
		// iterate through all clusters to find which center has least distance to this point
		for(int i = 0; i < this.clusters.size(); ++i) {
			Cluster cluster = this.clusters.get(i);
			distance = point.distanceFrom(cluster.getCenter());
			
			if(distance < minimum) {
				minimum = distance;
				indexOfMin = i;
			}
		}
		
		// add point to the cluster whose center was least distant
		this.clusters.get(indexOfMin).addPoint(point);
		
	}
	
	/** Returns a list of all points on this graph*/
	public ArrayList<DataPoint> getAllPoints() {
		return this.allPoints;
	}
	
	public void analysisToString() {
		for(Cluster cluster : this.clusters) {
			//System.out.println(Arrays.toString(cluster.getAnswers()));
		}
	}
	
	public void runAgain() {
		
		this.clusters = new ArrayList<>();
		this.allPoints = new ArrayList<>();
		this.calibrationMeter = 0;
		Random rand = new Random();
		ArrayList<Double> potentials = new ArrayList<>();
		
		int upperGraphLimit = 300;
		int lowerGraphLimit = 0;
		int numClusters = 3;
		int numDimensions = 2;
		
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
			clusters.add(new Cluster(new Center(centerPointVector), i));
		}
		
		ArrayList<DataPoint> points = new ArrayList<>();
		DataPoint point;
		double[] dataGroup1;
		double[] dataGroup2;
		double[] dataGroup3;
		
		for(int i = 0; i < (30); ++i) {
			
			dataGroup1 = new double[2];
			dataGroup1[0] = rand.nextInt(10) + 280.0; // 280-289
			dataGroup1[1] = rand.nextInt(30) + 270.0; // 270-300
			
			dataGroup2 = new double[2];
			dataGroup2[0] = rand.nextInt(40) + 100.0; // 100-139
			dataGroup2[1] = rand.nextInt(30) + 200.0; // 200-229
			
			dataGroup3 = new double[2];
			dataGroup3[0] = rand.nextInt(40) + 200.0; // 200-239
			dataGroup3[1] = rand.nextInt(10) + 10.0; // 10-19
			
			point = new DataPoint(dataGroup1);
			add(point);
			
			point= new DataPoint(dataGroup2);
			add(point);
			
			point= new DataPoint(dataGroup3);
			add(point);
		}
	}
}
