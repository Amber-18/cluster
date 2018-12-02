package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import analyzers.*;

public class GraphicPoint extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DataPoint> points;
	
	private Graph graph;
	
	public GraphicPoint(Graph graph) {
		super();
		
		this.graph = graph;
		this.points = graph.getAllPoints();
	}
	
	public void paintComponent(Graphics g) {
		
		double[] vector = new double[this.points.get(0).getVector().length];
		int x;
		int y;
		
		for(DataPoint p : this.points) {
			
			vector = p.getVector();
			
			x = (int)vector[0];
			y = (int)vector[1];
			
			g.setColor(Color.BLACK);
			g.fillRect(x, y, 5, 5);
			
		}
		
		g.setColor(Color.MAGENTA);
		
		for(Center center : graph.getCenters()) {
			
			vector = center.getVector();
			
			x = (int)vector[0];
			y = (int)vector[1];
			g.fillRect(x, y, 5, 5);
		
		}
		
		
		
		
	}

}
