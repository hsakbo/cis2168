package sortings;
import java.io.*;
import java.util.*;



public class main{

    public static void csv(InsertionSort inSort, TimSortTime timmy){
	long t  = inSort.effsort();
	long t2 = timmy.sortTime();
	System.out.printf("in: %d\ntim: %d\nin-tim=%d\n", t, t2, t-t2);
	
    }


    public static void printArr(int[] arr, int size){
	for(int i = 0; i < size; i++){
	    System.out.printf("%d\n", arr[i]);
	}

    }

    public static void main(String[] args){

	//these are constants I got from the second sorting algorithm, for purposes of accuracy I will use a different sorting algorithm to accurately record time without extra lines to record comps, and exhanges.
	
	//insertion sort number of comparisons for this file is 1033741884.
	//insertion sort number of exchanges: 1033832617
	int[] unsorted = new int[45403];
	int size = 0;


	
	try{
	    Random r = new Random();
	    File f = new File("gen.txt");
	    Scanner sc = new Scanner(f);
	    while(sc.hasNext()){
		unsorted[size++] = sc.nextInt();
	    }
	    
	}

	catch (Exception e){	    
	    System.out.printf("try catch block failed\n");
	}

	InsertionSort inSort = new InsertionSort(unsorted, size);
	    

	
	
	//printArr(inSort.sortedArr(), size);
	//long[] ret = inSort.sort();	
	//System.out.printf("%d,%d\n", ret[0],ret[1]);
	TimSortCandE tSort = new TimSortCandE(unsorted, size);
	//csv(inSort, tSort);
	int[] r = tSort.sortCE();
	System.out.printf("%d, %d\n", r[0], r[1]);
	printArr(tSort.retArr(), size);
	//controlled debug: line 276163 of debug
	/*
	int arr1[] = {344,425,453,459,568,1075,1304};
	int key = 969;
	System.out.printf("key: %d\n", key);
	int indRet = tSort.binIndex(arr1, key);
	System.out.printf("%d: %d\n", indRet, arr1[indRet]);
	System.out.printf("%s\n", Arrays.toString(arr1));
	*/
	
    }

}
