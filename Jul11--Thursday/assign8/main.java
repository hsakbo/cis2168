package sortings;
import java.io.*;
import java.util.*;



public class main{

    public static void csvCTL(int[] arr, int size){

	InsertionSort inSort = new InsertionSort(arr, size);
	TimSortTime timmy = new TimSortTime(arr, size);
	QuickSort qSort = new QuickSort(arr, size);	

	long t  = inSort.effsort();
	long t2 = timmy.sortTime();
	long t3 = qSort.sortTime();
	
	System.out.printf("%d,%d,%d\n", t, t2, t3);
	
    }

    public static void csvAll(int[] arr, int size){
	InsertionSort inSort = new InsertionSort(arr, size);
	TimSortCandE timmy = new TimSortCandE(arr, size);
	TimSortTime tim = new TimSortTime(arr, size);
	QuickSort qSort = new QuickSort(arr, size);

	int[] retI = inSort.sort();
	inSort = new InsertionSort(arr, size);
	long t = inSort.effsort();

	int[] retT = timmy.sortCE();
	long t1 = tim.sortTime();

	int[] retQ = qSort.sortCandE();
	qSort = new QuickSort(arr, size);
	long t2 = qSort.sortTime();

	System.out.printf("%d,%d,%d,%d,%d,%d,%d,%d,%d\n", t, t1, t2, retI[0], retI[1], retT[0], retT[1], retQ[0], retQ[1]);
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

	//InsertionSort inSort = new InsertionSort(unsorted, size);
	    

	
	
	//printArr(inSort.sortedArr(), size);
	//long[] ret = inSort.sort();	
	//System.out.printf("%d,%d\n", ret[0],ret[1]);
	//TimSortTime test = new TimSortTime(unsorted, size);
	//TimSortCandE tSort = new TimSortCandE(unsorted, size);
	//QuickSort qSort = new QuickSort(unsorted, size);

	//int[] ret = qSort.sortCandE();
	//System.out.printf("%d %d\n", ret[0], ret[1]);
	//printArr(qSort.retArr, size);
	    
	//csv(inSort, tSort);
		
	//tSort.sortCE();		
	//printArr(tSort.retArr, size);
	//controlled debug: line 276163 of debug
	/*
	int arr1[] = {344,425,453,459,568,1075,1304};
	int key = 969;
	System.out.printf("key: %d\n", key);
	int indRet = tSort.binIndex(arr1, key);
	System.out.printf("%d: %d\n", indRet, arr1[indRet]);
	System.out.printf("%s\n", Arrays.toString(arr1));
	
	int[] arr = {10, 80, 30, 90, 40, 50, 70};
	int[] arr2 = {10, 40};
	qSort.partition(arr, 0, arr.length-1);
	System.out.printf("%s\n", Arrays.toString(arr));
	*/

	csvCTL(unsorted, size);
    }
    
}
