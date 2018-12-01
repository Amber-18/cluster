package drivers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import analyzers.DataPoint;
import analyzers.Graph;
import graphics.GraphFrame;

public class Driver {

	public static void main(String[] args) {
		
		int numClusters = 3;
		int lowerGraphLimit = 0;
		double upperGraphLimit = 300;
		int numDimensions = 2;
		int numPoints = 50;
		
		// construct a graph
		Graph graph = new Graph(numClusters, lowerGraphLimit, upperGraphLimit, numDimensions);
				
		ArrayList<DataPoint> points = new ArrayList<>();
		DataPoint point;
		Random rand = new Random();
		double[] dataGroup1;
		double[] dataGroup2;
		double[] dataGroup3;
		
		for(int i = 0; i < (numPoints/2); ++i) {
			
			dataGroup1 = new double[2];
			dataGroup1[0] = rand.nextInt(30) + 270.0; // 280-309
			dataGroup1[1] = rand.nextInt(30) + 220.0; // 260-279
			
			dataGroup2 = new double[2];
			dataGroup2[0] = rand.nextInt(40) + 30.0; // 30-49
			dataGroup2[1] = rand.nextInt(40) + 10.0; // 30-49
			
			dataGroup3 = new double[2];
			dataGroup3[0] = rand.nextInt(40) + 200.0; // 30-49
			dataGroup3[1] = rand.nextInt(40) + 10.0; // 30-49
			
			point = new DataPoint(dataGroup1);
			graph.add(point);
			
			point= new DataPoint(dataGroup2);
			graph.add(point);
			
			point= new DataPoint(dataGroup3);
			graph.add(point);
		}
		
		// new GraphFrame(graph);
		
	}

}
