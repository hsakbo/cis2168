import java.awt.Desktop;
import java.io.*;
import java.util.*;
import java.lang.*;


public class assign5{
    //Knight one, board x axis starts from 0 and from top left. y axis starts from top left from 0.

    static int order;
    
    public static int[][] recurseKnight(int[][] visited, int x, int y){


       
	if(x + 1 > 7 || y - 2 < 0);
	//out of bounds check, 
	
	//checks if visited
	else if(visited[x+1][y-2] != 0);

	else{
	    visited[x+1][y-2] = ++order;
	    recurseKnight(visited, x+1, y-2);
	    //the recursion.
	}


	//same block as above except it is the other move. All possible 8 moves are coded like this.
	if((x+2 > 7 || y -1 < 0));		   
	else if(visited[x+2][y-1] != 0);
	else{
	    visited[x+2][y-1] = ++order;
	    recurseKnight(visited, x+2, y-1);
	}


	if((x+2 > 7 || y+1 > 7));
	else if(visited[x+2][y+1] != 0);		
	else{
	    visited[x+2][y+1] = ++order;
	    recurseKnight(visited, x+2, y+1);
	}

 
	if((x+1 > 7 || y+2 > 7));
	else if(visited[x+1][y+2] != 0);
	else{
	    visited[x+1][y+2] = ++order;
	    recurseKnight(visited, x+1, y+2);
	}

	
	
	if((x-1 < 0 || y+2 > 7));
	else if(visited[x-1][y+2] != 0);
	else{
	    visited[x-1][y+2] = ++order;
	    recurseKnight(visited, x-1, y+2);
	}

	    
	if((x-2 < 0 || y + 1 > 7));
	else if(visited[x-2][y+1] != 0);	       
	else{
	    visited[x-2][y+1] = ++order;
	    recurseKnight(visited, x-2, y+1);
	}
       

	if((x-2 < 0 || y -1 < 0));
	else if(visited[x-2][y-1] != 0);
	else{
	    visited[x-2][y-1] = ++order;
	    recurseKnight(visited, x-2, y-1);
	}
       

	if((x -1 < 0 || y -2 < 0));
	else if(visited[x-1][y-2] != 0);
	else{
	    visited[x-1][y-2] = ++order;
	    recurseKnight(visited, x-1, y-2);
	}

	//after all possibilities are exhausted, this if is run which will pop a function stack. However, if complete, no execution.
	
	if(order != 64){
	
	    visited[x][y] = 0;
	    order--;
	    return null;
	}

	//final return
	return visited;
	    
	
    }

    public static void wrapKnight(int xStart, int yStart){

	order = 1;
	// will work clockworks from north starting from NE and ending from NW.

	int[][] visited = new int[8][8];
	visited[xStart][yStart] = 1;
	//counter for locations, will return this.

	recurseKnight(visited, xStart, yStart);
	System.out.println("\n\t\tKnights of Camelot:\n");
	for(int i = 0; i < 8; i++){
	    for(int j = 0; j < 8; j++)
		System.out.printf("%d\t", visited[i][j]);

	    System.out.println("\n\n");

	}
    }


    public static void printPuzzle(int[][] puzzle){


	for(int i = 0; i < 9; i++){
	    System.out.printf("\t\t");
	    for(int j = 0; j < 9; j++){
		System.out.printf("%d ", puzzle[i][j]);
		if(j == 2 || j == 5)
		    System.out.printf("* ");
	    }
	    System.out.printf("\n");
	    if(i == 2 || i == 5){
		System.out.printf("\t\t");
		for(int k = 0; k < 21; k++)
		    System.out.printf("*");
		System.out.printf("\n");
	    }
	}

	System.out.printf("\n");
    }


    

    //finds the legal status of a digit for the sudoku puzzle.
    public static boolean checker(int[][] puzzle, int x, int y){

	//checks the row (x axis)
	for(int i = 0; i < 9; i++){
	    if(i == x)
		continue;
	    
	    else if(puzzle[y][i] == puzzle[y][x])
		return false;
		
	}


	//checks the column (y xis)
	for(int i = 0; i < 9; i++){
	    if(i == y)
		continue;
	    
	    else if(puzzle[i][x] == puzzle[y][x])
		return false;
		
	}


	//checks the box
	int xmult, ymult;
	xmult = x / 3;
	ymult = y / 3;
	
	for(int i = ymult*3; i < 3*(ymult+1); i++){	
	    for(int j = xmult*3; j < 3*(xmult+1); j++){
		if(i == y && j == x)
		    continue;

		else if(puzzle[i][j] == puzzle[y][x])
		    return false;
		    
	    }
	}

	return true;
    }



    //q2: this algorithm is fast!
    //psuedocode: (PLAN: null means a backtrack condition)
    //find the nearest 0 and make a loop that iterates 1 to 9 (k loop)
    //*************************THE K LOOP******************************
    //assign that value to the current index, and check if it is legal.
    //if illegal, use continue;
    //if legal, create the recursion loop for the next 0 index.
    //if the recursive call returned null(fail), then break recursion loop and let k loop iterate again.
    //else return the successful 2d array (the domino return).

    //The final check of puzzle completion can be done with a simple trick.
    //the check (if condition) should be placed below the recursion loop.
    //when the call is with (8, 8) and will die due to natural conditions. We can use this here for our check by making a
    //distinction between a normal and an abnormal exit from the recursion loop. I do this by saving the loop variable (nRow,
    //nCol) outside of the for statement, thereby preserving their last values. The conditions are: 9 for natural (8+1 < 9==
    //false). Abnormal death is 100 (101 < 9 == false).

    //if recursion loop died naturally (win condition) return the puzzle.
    //**************************The end of k loop*****************************
    //return null, this is because the k loop has ended which only means that all k values are illegal for that index.
    
    
    public static int[][] expSudoku1(int[][] puzzle, int x, int y){
	
	int nRow, nCol;
	nCol = nRow = 0;
	int i, j;
	j = x;
	
	int[][] voidCheck = null;

	//2 nested for loops to search the nearest 0.
	for(i = y; i < 9; i++){	    
	    for(; j < 9; j++){		
		if(puzzle[i][j] == 0){
		    //found the closest 0. Now execute this code 9 times.
		    
		    
		    //IMPORTANT: this will be called the k loop. 
		    for(int k = 1; k <= 9; k++){

			//the assignment of k to the puzzle[i][j] which is 0. (from 1 to 9 recursively)
			puzzle[i][j] = k;

			//continue to next ++k if illegal
			if(!checker(puzzle, j, i))
			    continue;
			

			//IMPORTANT: this is the recursion loop.		
			//The same functionality as outermost 2 loops. find the next 0 and calls it under new x y.
			nCol = j+1;
			for(nRow = i; nRow < 9; nRow++){
			    for(; nCol < 9; nCol++){
				if(puzzle[nRow][nCol] == 0){

				    voidCheck = expSudoku1(puzzle, nCol, nRow);

				    if(voidCheck != null)
					return voidCheck;

				    //break the 2 loops for abnormal escape.
				    else{
					
					nRow = 100;
					nCol = 100;
				    }	
				}
			    }
			    nCol = 0;
			}
		        //exit conditions: nRow: 9 if natural, 101 if voidCheck is null.
			
			
			
			//execute below under a natural exit of recursion loop.
			//This is the return condition per say.
			if(nRow == 9)
			    return puzzle;

			
			//k loop ends here
		    }
		    
		    //After k loop is done, which it will only end if all 9 iterations are continued, then:
		    puzzle[i][j] = 0; //subtle pointer logic... I H4T3 J4V4:.]:@.:\,.:/"QUNHOh2e2rq29j
		    return null;

		} // <-- end of if(puzzle[i][j] == 0)...
	    } // <-- end of j loop. reset j.
	    j = 0;
	    
	} // <-- end of main outermost loop i.


	//never reaches. probably.
	return null;
    
    }



    //this is even more faster with the removal of unnecesary for loops
    //the initial conditions are refactored into a different method which is called by the wrapper exp2sudoWrap()

    //static int count;
    public static int[][] expSudoku2(int[][] puzzle, int x, int y){
	
	//System.out.printf("%d x:%d y%d\n", ++count, x, y);
	//printPuzzle(puzzle);
	int nRow, nCol;
	
	
	int[][] voidCheck = null;

       
	for(int k = 1; k <= 9; k++){

	    //System.out.printf("aa\n");
	    puzzle[y][x] = k;
	    if(!checker(puzzle, x, y))
		continue;

	    
	    nCol = x;
	    for(nRow = y; nRow < 9; nRow++){
		for(; nCol < 9; nCol++){		    
		    if(puzzle[nRow][nCol] == 0){

			voidCheck = expSudoku2(puzzle, nCol, nRow);
			if(voidCheck != null)
			    return voidCheck;

			else{
					
			    nRow = 100;
			    nCol = 100;
			}	
		    }
		}
		nCol = 0;
	    }
	    if(nRow == 9)
		return puzzle;					    
	}
		    		    
	puzzle[y][x] = 0;
	//System.out.printf("a\n");
	return null;
    
    }

    //finds initial 0.
    public static int[] initxy(int[][] puzzle){

	int[] retArr = {0, 0};
	for(int i = 0; i < 9; i++)
	    for(int j = 0; j < 9; j++)
		if(puzzle[i][j] == 0){
		    retArr[0] = j;
		    retArr[1] = i;
		    return retArr;
		}

	return retArr;
		    
	
    }

    //wrapper for much faster solution.
    public static void exp2sudoWrap(int[][] usrPuzzle){
	
	if(usrPuzzle == null){
	    int[][] puzzle = new int[9][9];
	    int[][] puzzle2 = new int[9][9];

	    //puzzle[0][0] = 3;
	    puzzle[0][2] = 6; puzzle2[0][3] = 1;
	    puzzle[0][3] = 2; puzzle2[0][4] = 9;
	    puzzle[0][4] = 5; puzzle2[0][7] = 6;
	    puzzle[0][8] = 7; puzzle2[1][0] = 5;
	    puzzle[1][1] = 4; puzzle2[1][6] = 9;
	    puzzle[1][5] = 9; puzzle2[2][0] = 2;
	    puzzle[2][2] = 8; puzzle2[2][3] = 6;
	    puzzle[2][8] = 9; puzzle2[2][4] = 4;
	    puzzle[3][0] = 4; puzzle2[3][1] = 8;
	    puzzle[3][1] = 8; puzzle2[3][2] = 7;
	    puzzle[3][5] = 1; puzzle2[3][7] = 5;
	    puzzle[3][7] = 5; puzzle2[4][0] = 4;
	    puzzle[4][2] = 3; puzzle2[4][4] = 8;
	    puzzle[4][6] = 9; puzzle2[4][8] = 9;
	    puzzle[5][1] = 6; puzzle2[5][1] = 6;
	    puzzle[5][3] = 5; puzzle2[5][6] = 8;
	    puzzle[5][7] = 2; puzzle2[5][7] = 2;
	    puzzle[5][8] = 1; puzzle2[6][4] = 7;
	    puzzle[6][0] = 6; puzzle2[6][5] = 3;
	    puzzle[6][6] = 1; puzzle2[6][8] = 6;
	    puzzle[7][3] = 1; puzzle2[7][2] = 9;
	    puzzle[7][7] = 6; puzzle2[7][8] = 8;
	    puzzle[8][0] = 2; puzzle2[8][1] = 4;
	    puzzle[8][4] = 3; puzzle2[8][4] = 2;
	    puzzle[8][5] = 7; puzzle2[8][5] = 8;
	    puzzle[8][6] = 5;

	    int[] ini = initxy(puzzle2);

	    System.out.printf("\nSource: https://www.websudoku.com/?level=4\n\n\t\tUnsolved Sudoku Puzzle:\n\n");
	    printPuzzle(puzzle2);

	    System.out.printf("\n\t\tSolved Sudoku Puzzle:\n\n");
	
	    printPuzzle(expSudoku2(puzzle2, ini[0], ini[1]));
	}

	else{
	    int[] ini = initxy(usrPuzzle);
	    System.out.printf("\n\t\tUnsolved Sudoku Puzzle:\n\n");
	    printPuzzle(usrPuzzle);
	    System.out.printf("\n\t\tSolved Sudoku Puzzle:\n\n");
	
	    printPuzzle(expSudoku2(usrPuzzle, ini[0], ini[1]));
	}
	    
	
	
    }
    


    public static int eulerTour(){

	File file = new File("/home/sflash/Documents/Uni-stuff/summer-2019/cis2168/Jun20--Thursday/assign5/euler.txt");
	//make sure to have euler.txt inside the CLASS path.

	
	String line;

	int[][][] bigArray = new int[50][9][9];
	int count = -1;

	try{

	    Scanner eu = new Scanner(file);

	    while(eu.hasNextLine()){

		count++;
		eu.nextLine();
		
		for(int i = 0; i < 9; i++){
		    line = eu.nextLine();
		    for(int j = 0; j < 9; j++){
			bigArray[count][i][j] = Character.getNumericValue(line.charAt(j));
		    }
		}	
	    }
	}
	
	
	catch(FileNotFoundException abc){
	    System.out.printf("try catch block failed\n");
	    
	}
	
	int[] start;
	
	for(int i = 0; i < 50; i++){

	    

	}
	    
	
	return -1;
    }
    
    
    public static void main(String[] args){

	//suggest (0, 0) coordinates
	//also ignore my x and y. This goes like wrapKnight(y, x)
	//wrapKnight(0, 0);
	


	//provide NULL for default puzzles (2 are coded). You can also feed it a puzzle.
	//default puzzle: ~13k recursive cycles, puzzle2: 11068 cycles.
	//exp2sudoWrap(null);

	eulerTour();

    }

}
