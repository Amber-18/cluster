package graphics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import analyzers.DataPoint;
import analyzers.Graph;
import drivers.Driver;

public class GraphFrame extends JFrame{
	
	/** Use default UID */
	private static final long serialVersionUID = 1L;
	
	/** panel for slider */
	GraphicPoint gp;
	
	JPanel panel1 = new JPanel();
	
	public GraphFrame(Graph graph) {
		super("Graph Frame!");
		
		this.gp = new GraphicPoint(graph);
		
		add(gp);
		
		
        setSize(1000, 1000);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFrame buttonFrame = new JFrame("Run Again?");
        
        JButton button = new JButton("Run Again?");
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	buttonFrame.setVisible(false);
            	graph.runAgain();
            	new GraphFrame(graph);
            }
        });
        
        buttonFrame.add(button);
        buttonFrame.setLocation(500, 500);
        buttonFrame.setSize(500, 200);
        buttonFrame.setVisible(true);
        buttonFrame.setResizable(false);
        buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
