package sortings;

public class QuickSort{

    private int size;
    private int[] arr;
    public int[] retArr;
    private long time;
    public int[] CE;
 
    public QuickSort(int[] arr, int size){
	this.size = size;
	this.arr = arr;
	this.time = 0;
	this.CE = new int[2];
	
    }

    /*
    private int partition(int[] arr, int inB, int inE){

	int piv = arr[inB];
	int j = inE;
	int temp;

	for(int i = 1; i <= inE; i++){

	    if(arr[i] > pivot){
		temp = 
	    }
	    
	}

    }
    */

    //pivot is last index
    public int partitionT(int arr[], int inB, int inE) 
    { 
        int pivot = arr[inE];  
        int j = inB - 1;
	int temp;
        for (int i = inB; i < inE; i++){
	    	
		
	    if (arr[i] <= pivot){ 
		
		j++; 					
		temp = arr[j]; 
		arr[j] = arr[i]; 
		arr[i] = temp; 
	    } 
	} 	
        
        temp = arr[j+1]; 
        arr[j+1] = arr[inE]; 
        arr[inE] = temp; 
	
        return j+1; 
    } 
  
  

    
    public long sortTime(){
	if(this.time == 0){
	    this.time = System.nanoTime();
	    retArr = this.arr.clone();
	    sortT(retArr, 0, retArr.length-1);
	    this.time = System.nanoTime() - this.time;
	    return this.time;
	}
	return this.time;
    }
    
    private void sortT(int arr[], int inB, int inE) 
    { 
        if (inB < inE) 
        { 
                        
            int piv = partitionT(arr, inB, inE);         
            sortT(arr, inB, piv-1); 
            sortT(arr, piv+1, inE); 
        } 
    }

    public int[] sortCandE(){
	retArr = this.arr.clone();
	sortCE(retArr, 0, retArr.length-1);
	return this.CE;
    }

    private void sortCE(int arr[], int inB, int inE) 
    { 
        if (inB < inE) 
        { 
            CE[0]++;
            int piv = partitionCE(arr, inB, inE);         
            sortCE(arr, inB, piv-1); 
            sortCE(arr, piv+1, inE); 
        } 
    }

    public int partitionCE(int arr[], int inB, int inE) 
    { 
        int pivot = arr[inE];  
        int j = inB - 1;
	CE[1]++;
	int temp;
        for (int i = inB; i < inE; i++){
	    	
	    CE[0]++;
	    if (arr[i] <= pivot){ 

		CE[1] += 3;
		j++; 					
		temp = arr[j]; 
		arr[j] = arr[i]; 
		arr[i] = temp; 
	    } 
	} 	

	CE[1] += 3;
        temp = arr[j+1]; 
        arr[j+1] = arr[inE]; 
        arr[inE] = temp; 
	
        return j+1; 
    } 
}
