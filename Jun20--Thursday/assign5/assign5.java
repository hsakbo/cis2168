public class assign5{
    //Knight one, board x axis starts from 0 and from top left. y axis starts from top left from 0.

    static int order;
    //static int solStatus;
    //static int[][] solArr;
    
    /* LOL try using this instead of if(order == 64)
       public static boolean checkVisit(int[][] visited){

       for(int i = 0; i < 8; i++)
       for(int j = 0; j < 8; j++)
       if(visited[i][j] == 0)
       return false;

       return true;
	
       }
    */
    
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

    /* Troublesome code.
      public static int[][] solveSudoku(int[][] puzzle, int x,int y){


	
      int xmult, ymult;
      xmult = x / 3;
      ymult = y / 3;



      //I know this is cheating, the if condition to bypass the very first check so that the recursion initializes, but this is experimental.
      //I will try to make a better loop which can scale well with no matter where you begin and no reliance on global variables...
      if(puzzle[0][0] != 0){

      //******start box check**********
      for(int i = ymult*3; i < 3*(ymult+1); i++){	
      for(int j = xmult*3; j < 3*(xmult+1); j++){
      if(i == y && j == x)
      continue;

      else if(puzzle[i][j] == puzzle[y][x])
      return null;
		    
      }
      }
      //*********box check clear********


      //*********start column(x axis) check*****
      for(int i = 0; i < 9; i++){
      if(i == x)
      continue;
	    
      else if(puzzle[y][i] == puzzle[y][x])
      return null;
		
      }	
      //**********end column check**************


	    
      //**********start row check (y axis)******
      for(int i = 0; i < 9; i++){
      if(i == y)
      continue;

      else if(puzzle[i][x] == puzzle[y][x])
      return null;
		
      }
      }
      //**********end row checks****************

	
      int i, j;
      if(x != 0 && y != 0){
      i = y;
      if(x+1 > 8){
      i = y+1;
      j = 0;
      }

      else
      j = x+1;
      }
      else{
      i = 0;
      j = 0;
      }
	

	
      for(; i < 9; i++){
      for(; j < 9; j++){
      if(puzzle[i][j] == 0){
      for(int k = 1; k <= 9; k++){
      if(solStatus == 0) {
      puzzle[i][j] = k;
      solveSudoku(puzzle, j, i);
      //printPuzzle(puzzle);
      }
      if(solStatus != 0)
      return solArr;
      }
      if(solStatus == 0)
      return null;
      }				
      }
      j = 0;
	    
	
      }

      if(solStatus != 0)
      return solArr;



      solStatus = 1;
      solArr = puzzle;
      return puzzle;
	
      }
    */

    
    public static boolean checkBox(int[][] puzzle, int x, int y){

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

    public static boolean checkRow(int[][] puzzle, int x, int y){
	for(int i = 0; i < 9; i++){
	    if(i == x)
		continue;
	    
	    else if(puzzle[y][i] == puzzle[y][x])
		return false;
		
	}

	return true;
    }

    public static boolean checkCol(int[][] puzzle, int x, int y){

	for(int i = 0; i < 9; i++){
	    if(i == y)
		continue;
	    
	    else if(puzzle[i][x] == puzzle[y][x])
		return false;
		
	}
	return true;
    }

    
    //psuedocode: (PLAN: null means a fail condition)
    //find the nearest 0 and make a loop that iterates 1 to 9 (k loop)
    //*************************THE K LOOP******************************
    //assign that value to the current index, and check if it is legal.
    //if illegal, use continue;
    //if legal, create the recursion loop for the next 0 index.
    //if the recursive call returned null(fail), then break recursion loop and let k loop iterate again.
    //else return the successful 2d array (the domino return).

    //The final check of puzzle completion can be done with a simple trick.
    //the check (if condition) should be placed below the recursion loop. This is because the recursion loop will not execute
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

			//the assignment of k to the puzzle[i][j] which is 0.
			puzzle[i][j] = k;

			//continue to next ++k if illegal
			if(!checkCol(puzzle, j, i))    {continue;}			
			if(!checkRow(puzzle, j, i))    {continue;}
			if(!checkBox(puzzle, j, i))    {continue;} //the order matters for (miniscule) speed.
			
			

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
		    puzzle[i][j] = 0; //I don't understand this line, need help in explaining this...
		    return null;

		} // <-- end of if(puzzle[i][j] == 0)...
	    } // <-- end of j loop. reset j.
	    j = 0;
	    
	} // <-- end of main outermost loop i.


	//never reaches. probably.
	return null;
    
    }
    
    
    public static void wrapSudoku(){

	int[][] puzzle = new int[9][9];



	//actual puzzle:
	//puzzle[0][0] = 6;
	puzzle[0][2] = 6;
	puzzle[0][3] = 2;
	puzzle[0][4] = 5;
	puzzle[0][8] = 7;
	puzzle[1][1] = 4;
	puzzle[1][5] = 9;
	puzzle[2][2] = 8;
	puzzle[2][8] = 9;
	puzzle[3][0] = 4;
	puzzle[3][1] = 8;
	puzzle[3][5] = 1;
	puzzle[3][7] = 5;
	puzzle[4][2] = 3;
	puzzle[4][6] = 9;
	puzzle[5][1] = 6;
	puzzle[5][3] = 5;
	puzzle[5][7] = 2;
	puzzle[5][8] = 1;
	puzzle[6][0] = 6;
	puzzle[6][6] = 1;
	puzzle[7][3] = 1;
	puzzle[7][7] = 6;
	puzzle[8][0] = 2;
	puzzle[8][4] = 3;
	puzzle[8][5] = 7;
	puzzle[8][6] = 5;

	
       	System.out.printf("\nSource: https://www.websudoku.com/?level=4\n\n\t\tUnsolved Sudoku Puzzle:\n\n");
	printPuzzle(puzzle);

       	System.out.printf("\n\t\tSolved Sudoku Puzzle:\n\n");
	int[][] solution = expSudoku1(puzzle, 0, 0);
	printPuzzle(solution);
	    
    }

	
    
    
    public static void main(String[] args){

	//suggest (0, 0) coordinates
	//also ignore my x and y. This goes like wrapKnight(y, x)
	//wrapKnight(0, 0);

	wrapSudoku();
	

    }

}
