package drivers;

import analyzers.DataPoint;
import analyzers.Graph;

public class Driver {

	public static void main(String[] args) {
		
		int numClusters = 0;
		int lowerGraphLimit = 0;
		double upperGraphLimit = 0;
		int numDimensions = 0;
		// construct a graph
		Graph graph = new Graph(numClusters, lowerGraphLimit, upperGraphLimit, numDimensions);
		
		// construct points
		double[] data = new double[1];
		
		// add points to the graph!
		graph.add(new DataPoint(data));

	}

}
