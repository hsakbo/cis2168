

public class hello{

    public static int search(int[] x, int target){
	for(int i = 0; i < x.length; i++){
	    if(x[i] == target)
		return i;
	}
	return -1;

	//worst case scenario is looping six times if x.length is 6. The average is 
    }

    public static boolean areDifferent(int []x, int[] y){
	for(int i = 0; i<x.length; i++){
	    if(search(y, x[i]) != -1){
		return false;
	    }
	
	}
	    return true;

	    //o(n) = x.length * y.length

    }

    public static boolean areUnique(int[] x){
	for(int i = 0; i < x.length; i++){
	    for(int j = 0; x.length; j++){
		if(i != j && x[i] == x[j])
		    return false;
	    }
	}
	return true;
	//o(n^2) = x.length * x.length
	
	
	
	/* optimized */
	for(int i = 0; i < x.length; i++){
	    for(int j = i+1; i < x.length-1; j++){
		if(x[i] == x[j])
		    return false;
	    }
	}
	return true;
	

	//for comparisons if x.length is 6. Then comparisons are upto  5. Thus its 5*5.
	//if 3 statements are there inside the loop.
	//3n(n-1) /2
	//inner loop
	//T(n^2) = 3n^2/2
	//O(n^2)x

	
    }

    /*for(int i = 1; i < x.length; i*=2){
//do something with x[i]
2^k-1 = x.length < 2^k. log2(x.length) < k
if x.length = 10 then k = 9. O(n) = k-1 (because i < and not <=.

the growth rate is determined by the largest power in a polynomial. Lower order terms can be dropped**. ie n^2 + 5n + 25. Proof of irrelevance:
cn0^2 > n^2 + 5n + 25 must be true.
    */
    
    public static void main(String args[]){
	
    }

}
