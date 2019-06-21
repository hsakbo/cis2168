public class assign5{
    //Knight one, board x axis starts from 0 and from top left. y axis starts from top left from 0.

    static int order;
    static int solStatus;
    static int[][] solArr;
    
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


    public static int[][] solveSudoku(int[][] puzzle, int x,int y){

	//System.out.printf("%d %d\n", x, y);
	//******start box check**********
	int xmult, ymult;
	xmult = x / 3;
	ymult = y / 3;

	if(x != 0 && y != 0){
	    
	    for(int i = ymult; i < 3*(ymult+1); i++){	
		for(int j = xmult; j < 3*(xmult+1); j++){
		    if(i == y && j == x)
			continue;

		    else if(puzzle[i][j] == puzzle[y][x])
			return null;
		}
	    }
	}
	//*********box check clear********


	//*********start column(x axis) check*****
	if(puzzle[y][x] != 0){

	    for(int i = 0; i < 9; i++){
		if(i == x)
		    continue;
	    
		else if(puzzle[y][i] == puzzle[y][x])
		    return null;
	
	    }
	}
	//**********end column check**************


	//**********start row check (y axis)******
	if(puzzle[y][x] != 0){
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
			    puzzle[i][j] = 0;
			}
			if(solStatus != 0)
			    return solArr;
			    
		    }
		}		
	    }
	    j = 0;
	}
	
	if(solStatus == 0){
	solStatus++;
	solArr = puzzle;
	}
	
	return solArr;
	
    }
    
    public static void wrapSudoku(){

	int[][] puzzle = new int[9][9];



	//actual puzzle:
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
	int[][] solution = solveSudoku(puzzle, 0, 0);
	printPuzzle(solution);
	    
    }

	
    
    
    public static void main(String[] args){

	//suggest (0, 0) coordinates
	//also ignore my x and y. This goes like wrapKnight(y, x)
	//wrapKnight(0, 0);

	wrapSudoku();
	

    }

}
