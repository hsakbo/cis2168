import java.util.*;
public class assign2{

    
    public static <E> boolean unique(List <E> list){
        
	for (int i = 0; i < list.size(); i++){
	    for (int j = 0; j < list.size(); j++){
		if (j != i){
		    if (list.get(i).equals(list.get(j))){
			return false;
		    }
		}
	    }
	}

	return true;
	//time complexity n^2
    }
    
    public static List<Integer> allMultiples(List <Integer> list, int m){

	List<Integer> retList = new LinkedList<Integer>();
	int temp;
	for(int i = 0; i < list.size(); i++)
	    {
		temp = (int) list.get(i);
		if(temp == 0){
		    continue;
		}
		
		if(temp % m == 0){
		    retList.add(list.get(i));
		}
	    }

	return retList;
	//time complexity O(n)
    }
    
    
    public static List<String> allStringsOfSize(List<String> list, int length){

	List<String> retList = new LinkedList<String>();
	String temp;
	for(int i = 0; i < list.size(); i++){
	    temp = list.get(i);
	    if(temp.length() == length){
		retList.add(temp);
	    }
	}

	return retList;
	//time complexity O(n)
    }

    public static <E> boolean isPermutation(List<E> a, List<E> b){

	if(a.size() != b.size()){
	    return false;
	}

	int size = a.size();
	
	int countA, countB;
	for(int i = 0; i < size; i++){
	    countA = countB = 0;
	    for(int j = 0; j < size; j++){	       
		    if(a.get(i).equals(a.get(j))){
			countA++;
		    }
		
	    }
	    for(int k = 0; k < size; k++){
		if(a.get(i).equals(b.get(k))){
		    countB++;
		}
	    }

	    if(countA != countB){
		return false;
	    }
	}

	return true;
	//time complexity O(n^2)
    }
			
    public static List<String> stringToListOfWords(String str){

	ArrayList<String> retList = new ArrayList<String>();
	String[] sanitized = str.split("[^a-zA-Z0-9-]");
	for (int i = 0; i < sanitized.length; i++){
	    if(sanitized[i].equals("")){
		continue;
	    }
		
	    retList.add(sanitized[i]);
	}
	//time complexity O(n)
	return retList;
    }


    public static <E> void removeAllInstances(List<E> list, E item){
	for(int i = 0; i < list.size(); i++){
	    if(list.get(i).equals(item)){
		list.remove(i);
		i--;
	    }
	}
	//time complexity O(n)
	return;
    }


    

    public static void main(String[] args){

	/*

	LinkedList <String> myList = new LinkedList<String>();

	myList.add("Hello");
	myList.add(" ");
	myList.add("World");
	myList.add(" ");

	LinkedList <Integer> intList = new LinkedList<Integer>();
	
	for(int i = 0; i < 10; i++){
	    intList.add(i);
	}


	
	//	for(int i = 0; i < retInt.size(); i++)
	//  System.out.println(retInt.get(i));
	
	System.out.println(unique(myList));

	List<String> retStr = allStringsOfSize(myList, 5);
	//	for(int i = 0; i < retStr.size(); i++)
	//  System.out.println(retStr.get(i));

	
	System.out.println(isPermutation(myList, retStr));
	*/
	List <String> retStr = new ArrayList();
	String go = "Hello, \"my\" name is Jeff1. Who are you?Compound-word pass 2.5"; //will split decimals from integer
	retStr = stringToListOfWords(go);
		for(int i = 0; i < retStr.size(); i++)
	  System.out.println(retStr.get(i));
	
    }
    
}
