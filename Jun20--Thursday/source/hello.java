public class hello{

    public static void reversePrint(String str){
	if(str == null || str.equals("")){
	    return;

	}

	else{
	    reversePrint(str.substring(1));
	    System.out.printf("%s", str.charAt(0));
	    return;
	}
    }
    public static double gcd(int m, int n) {
	if (m % n == 0)
	    return n;
	else
	    return gcd(n, m % n);
    }

    public static int fibRecursion(int current, int prev, int n){

	if(n == 1 || n == 0)
	    return current;

	else
	    return fibRecursion(current + prev, current, n-1);
    }


    //IMPORTANT
   
    public static int binarySearch(Object[] items, Comparable target, int first, int last){
	if(first > last)
	    return -1;

	else{
	    int middle = (first + last) / 2;
	    int compResult = target.compareTo(items[middle]);
	    if(compResult == 0){
		return middle;
	    }
	    else if (compResult < 0)
		return binarySearch(items, target, first, middle -1);
	    else
		return binarySearch(items, target, first + 1, middle);
	}
    }


    //towers of hanoi
    public static String showMoves(int n, char startPeg, char destPeg, char tempPeg){
	if (n == 1){
	    return "Move disk 1 from peg " + startPeg + " to peg " + destPeg + "\n";
	}

	else{
	    return showMoves(n - 1, startPeg, tempPeg, destPeg) + "Move disk" + n + "from peg " + startPeg + " to peg " + destPeg + "\n"+ showMoves(n -1, tempPeg, destPeg, startPeg);
	}
    }



    
    //blob count, match of 2d array
    //prob start x and y need to be abnormal
    public int countCells(int x, int y){
	int result;

	if(x < 0 || x >= grid.getNCols() || y < 0 || y >= grid.getNROWS()){
	    return 0;
	}

	else if(!grid.getColors(x, y).equals(ABNORMAL))
	    return 0;

	else{
	    grid.recolor(x, y, TEMPORARY);
	    return 1
		+ countCells(x-1, y-1) + countCells(x, y-1) + count(x+1, y-1)
		+ countCells(x-1, y) + countCells(x+1, y)
		+countCells(x-1, y +1) + countCells(x, y+1) + count(x+1, y+1);
	}
    }
    
    public static void main(String[] args){

	System.out.printf("%f\n", gcd(319, 22));
	System.out.printf("%d\n", fibRecursion(1, 0, 10));
	
    }

}
