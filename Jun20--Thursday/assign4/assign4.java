public class assign5{
    //Knight one, board x axis starts from 0 and from top left. y axis starts from top left from 0.

    static int[][][] board;
    static int order;

    public boolean checkVisit(int[][] visited){

	for(int i = 0; i < 8; i++)
	    for(int j = 0; j < 8; j++)
		if(visited[i][j] == 0)
		    return false;

	return true;
	
    }
    
    public int[][] recurse(int[][] visited, int x, int y){

	
	//NO C, no more initializing triple array hell!

	if (checkVisit(visited)){
	    return visited;
	}	

	switch (board[x][y][0]){

	case 0:
	    board[x][y][0]++;
	    if((x + 1 > 7 || y - 2 < 0) && visited[x+1][y-2] == 0){
		//handle visited state ON
		
		recurse(visited, x, y);
	    }
	    else{
		visited[x+1][y-2] = ++order;
		recurse(visited, x+1, y-2);
	    }

	case 1:
	    board[x][y][0]++;
	    if((x+2 > 7 || y -1 < 0) && visited[x+2][y-1] == 0){
		recurse(visited, x, y);
	    }
	    else{
		visited[x+2][y-1] = ++order;
		recurse(visited, x+2, y-1);
	    }

	case 2:
	    board[x][y][0]++;
	    if((x+2 > 7 || y+1 > 7) && visited[x+2][y+1] == 0){
		recurse(visited, x, y);
	    }

	    else{
		visited[x+2][y+1] = ++order;
		recurse(visited, x+2, y+1);
	    }

	case 3:
	    board[x][y][0]++;
	    if((x+1 > 7 || y+2 > 7) && visited[x+1][y+2] == 0){
		recurse(visited, x, y);
	    }
	    else{
		visited[x+1][y+2] = ++order;
		recurse(visited, x+1, y+2);
	    }

	case 4:
	    board[x][y][0]++;
	    if((x-1 < 0 || y+2 > 7) && visited[x-1][y+2] == 0){
		recurse(visited, x, y);
	    }

	    else{
		visited[x-1][y+2] = ++order;
		recurse(visited, x-1, y+2);
	    }

	case 5:
	    board[x][y][0]++;
	    if((x-2 < 0 || y + 1 > 7) && visited[x-2][y+1] == 0){
		recurse(visited, x, y);
	    }

	    else{
		visited[x-2][y+1] = ++order;
		recurse(visited, x-2, y+1);
	    }

	case 6:
	    board[x][y][0]++;
	    if((x-2 < 0 || y -1 < 0) && visited[x-2][y-1] == 0){
		recurse(visited, x-2, y-1);
	    }

	    else{
		visited[x-2][y-1] = ++order;
		recurse(visited, x-2, y-1);
	    }

	case 7:
	    board[x][y][0]++;
	    if((x -1 < 0 || y -2 < 0) && visited[x-1][y-2] == 0){
		recurse(visited, x, y);
	    }

	    else{
		visited[x-1][y-2] = ++order;
		recurse(visited, x-1, y-2);
	    }
		
	default:
	    visited[x][y] = 0;
	    board[x][y][0] = 0;
	    return visited[x][y];
	}
	    
	
    }

    public int[][] wrapKnight(){

	this.order = 1;
	this.board = new int[8][8][1];
	//the board 1st demension: x axis
	//the board 2nd dimension: y axis
	//board 3rd dimension: legal status of each move. will work clockworks from north starting from NE and ending from NW.

	int[][] visited = new int[8][8];
	//counter for locations, will return this.


	return recurse(visited, 0, 0);
	
	
    }
    
    public static void main(String[] args){


	int knighty[][] = wrapKnight();
	
	
    }

}
