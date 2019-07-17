package sortings;

//retArr is int[2]
//retArr[0] == number of comparisons.
//retArr[1] == number of exchanges with the sorting array.

public class InsertionSort{

    private int[] Arr;
    private int size;
    
    public InsertionSort(int[] Arr, int size){
	this.Arr = Arr.clone();	
	this.size = size;
    }

    public int[] sortedArr(){
	return this.Arr;
    }

    
    
    public int[] sort(){

	int[] retArr = new int[2];	
	int[] aux = new int[2];
	int count;
			
	for(int i = 0; i < this.size -1; i++){

	    retArr[0]++;
	    if(Arr[i] <= Arr[i+1])
		continue;
	    
	    
	    count = 0;
	    aux[1] = Arr[i+1];
	    aux[0] = Arr[i];
	    retArr[1] += 2;
	    
	    
	    do{
		Arr[i+1-count] = aux[0];		
		count++;
		retArr[0]++;
		if(count > i)
		    break;
				
		aux[0] = Arr[i-count];		       
		retArr[1] += 2;
		retArr[0]++;
	    }while(aux[0] > aux[1]);
	    
	    Arr[i+1-count] = aux[1];
	    retArr[1]++;
	}
	
	return retArr;
    }

    public long effsort(){
	
	long ret = System.nanoTime();	
	int[] aux = new int[2];
	int count;		
	
	for(int i = 0; i < this.size -1; i++){
	    
	    if(Arr[i] <= Arr[i+1])
		continue;
	    
	    count = 0;
	    aux[1] = Arr[i+1];
	    aux[0] = Arr[i];	    
	    
	    do{
		Arr[i+1-count] = aux[0];		
		count++;	
		if(count > i)	    		   
		    break;
			       				
		aux[0] = Arr[i-count];		       	
	    }while(aux[0] > aux[1]);
	    Arr[i+1-count] = aux[1];
	}

	ret = System.nanoTime() - ret;
	return ret;
    }

    
}
