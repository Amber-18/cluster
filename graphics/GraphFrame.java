package graphics;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import analyzers.Graph;

public class GraphFrame extends JFrame{
	
	/** Use default UID */
	private static final long serialVersionUID = 1L;
	
	/** panel for slider */
	GraphicPoint gp;
	
	JPanel panel1 = new JPanel();
	
	public GraphFrame(Graph graph) {
		super("Graph Frame!");
		
		this.gp = new GraphicPoint(graph);
		
		panel1.add(new JTextField("Clustering!"));
		
		add(panel1, BorderLayout.NORTH);
		add(gp, BorderLayout.SOUTH);
		
		
        setSize(1000, 1000);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
