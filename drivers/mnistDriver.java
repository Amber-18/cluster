package drivers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import analyzers.DataPoint;
import analyzers.Graph;

public class mnistDriver {

	public static void main(String[] args) throws IOException {
		
		int numClusters = 9;
		int lowerGraphLimit = 0;
		double upperGraphLimit = 256;
		int numDimensions = 784;
		int numPoints = 5000;
		
		// construct a graph
		Graph graph = new Graph(numClusters, lowerGraphLimit, upperGraphLimit, numDimensions);
		
		String path = "src/drivers/train.csv";
		String line;
		String[] lineArray;
		double[] point;
		double answer;
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		// read in first line and throw it away
		br.readLine();
		
		for(int i = 0; i < numPoints; ++i) {
			line = br.readLine();
			lineArray = line.split(",");
			answer = Double.parseDouble(lineArray[0]);
			lineArray = Arrays.copyOfRange(lineArray, 1, lineArray.length);
			
			point = new double[784];
			for(int index = 0; index < 784; ++index) {
				point[index] = Double.parseDouble(lineArray[index]);
			}
			
			// add every data point to the graph
			graph.add(new DataPoint(point, answer));
		}
		
		graph.analysisToString();
		
		
		
	}

}
