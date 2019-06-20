import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
    private int rows;
    private int cols;
    private Cell[][] maze;



    // extra credit
    public void genDFSMaze() {
	//boolean[][] visited;
	Stack<Cell> stack  = new Stack<Cell>();
	Cell start = maze[0][0];
	stack.push(start);
    }

    //homework
    public void solveMaze() {
	//Stack<Cell> stack  = new Stack<Cell>();
	Cell start = maze[0][0];
	start.setBackground(Color.GREEN);
	Cell finish = maze[rows-1][cols-1];
	finish.setBackground(Color.RED);
	//stack.push(start);
	int visited[][] = new int[rows][cols]; //boolean array for visit check.

	//Must... protect... C!!
        //for(int i = 0; i < rows; i++) 
	//    for(int j = 0; j < cols; j++)
	//	visited[i][j] = 0;

	//I am going to use x and y coordinates in an array instead of storing cell objects.
	visited[0][0] = 1;
	int x, y;
	x = y = 0;
	Stack<int[]> pos = new Stack<int[]>();
	int[] arr = {x, y};
	int[] cloning;

	
	while(visited[rows-1][cols-1] != 1){

	    cloning = arr.clone();
	    pos.push(cloning);

	    
	    try{
		Thread.sleep(5);
	    }
	    catch(InterruptedException ex){
		Thread.currentThread().interrupt();
	    }
	    
	    
	    
	    //east
	    if((x+1 < cols) && !maze[y][x].eastWall && visited[y][x+1] != 1){//relying on optimization, else this will throw out of bound exception. first boolean false should resolve the if stack as false without checking the rest.
		
		maze[y][x].setBackground(Color.BLUE);
		maze[y][++x].setBackground(Color.GREEN);
		arr[0] = x;
		arr[1] = y;
		cloning = arr.clone();
		pos.push(cloning);
		visited[y][x] = 1;
		
	    }

	    //south
	    else if((y+1 < rows) && !maze[y][x].southWall && visited[y+1][x] != 1){

		    
	        maze[y][x].setBackground(Color.BLUE);
		maze[++y][x].setBackground(Color.GREEN);
		arr[0] = x;
		arr[1] = y;
		cloning = arr.clone();
		pos.push(cloning);
		visited[y][x] = 1;
	    }

	    //north
	    else if((y-1 >= 0) && !maze[y][x].northWall && visited[y-1][x] != 1){

	        maze[y][x].setBackground(Color.BLUE);
		maze[--y][x].setBackground(Color.GREEN);
		arr[0] = x;
		arr[1] = y;
		cloning = arr.clone();
		pos.push(cloning);
		visited[y][x] = 1;
		    
	    }

	    //west
	    else if((x-1 >= 0) && !maze[y][x].westWall && visited[y][x-1] != 1){

	        maze[y][x].setBackground(Color.BLUE);
		maze[y][--x].setBackground(Color.GREEN);
		arr[0] = x;
		arr[1] = y;
		cloning = arr.clone();
		pos.push(cloning);
		visited[y][x] = 1;
	    }
		
	    else{

		pos.pop();
		maze[y][x].setBackground(Color.WHITE);
		x = pos.peek()[0];
		y = pos.peek()[1];

		maze[y][x].setBackground(Color.GREEN);
		pos.pop();

		//logically these 2 steps are needed but due to the nature of the if statements they are irrelevant.
		//arr[0] = x;
		//arr[1] = y;

	    } 
	}

	maze[0][0].setBackground(Color.YELLOW); 

    }


    	


    public void genNWMaze() {
		
	for(int row = 0; row  < rows; row++) {
	    for(int col = 0; col < cols; col++) {

		if(row == 0 && col ==0) {
		    continue;
		}

		else if(row ==0) {
		    maze[row][col].westWall = false;
		    maze[row][col-1].eastWall = false;
		} else if(col == 0) {
		    maze[row][col].northWall = false;
		    maze[row-1][col].southWall = false;
		}else {
		    boolean north = Math.random()  < 0.5;
		    if(north ) {
			maze[row][col].northWall = false;
			maze[row-1][col].southWall = false;
		    } else {  // remove west
			maze[row][col].westWall = false;
			maze[row][col-1].eastWall = false;
		    }
		    maze[row][col].repaint();
		}
	    }
	}
	this.repaint();
		
    }

    public MazeGridPanel(int rows, int cols) {
	this.setPreferredSize(new Dimension(800,800));
	this.rows = rows;
	this.cols = cols;
	this.setLayout(new GridLayout(rows,cols));
	this.maze =  new Cell[rows][cols];
	for(int row = 0 ; row  < rows ; row++) {
	    for(int col = 0; col < cols; col++) {

		maze[row][col] = new Cell(row,col);

		this.add(maze[row][col]);
	    }

	}

	this.genNWMaze();


    }




}
