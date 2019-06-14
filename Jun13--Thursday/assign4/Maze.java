import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame {

    public static MazeGridPanel sol;
    public Maze() {

	sol = new MazeGridPanel(100, 100);
	this.add(sol);
	this.setSize(800, 800);
		
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.pack();
	this.setVisible(true);
        
    }
	
    public static void main(String[] args) {
	new Maze();
	sol.solveMaze();
    }
}
