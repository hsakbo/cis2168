package index;

import java.util.*;


public class IndexNode  {

    // The word for this entry
    String word;
    // The number of occurrences for this word
    int occurrences;
    // A list of line numbers for this word.
    List<Integer> list; 
	
	
	
    IndexNode left;
    IndexNode right;

    public IndexNode(String word, int line){
	this.word = word;
	list = new ArrayList<Integer>();
	list.add(line);
	left = right = null;
	this.occurrences = 1;
    }

    public IndexNode clone(){
	IndexNode ret = new IndexNode(word, list.get(0));
	for(int i = 1; i < list.size(); i++){
	    ret.add(list.get(i));
	}
	ret.left = this.left;
	ret.right = this.right;
	return ret;
	
	
    }
    
	
    // Constructors
    // Constructor should take in a word and a line number
    // it should initialize the list and set occurrences to 1
	
	
    public void add(int line){

	if(!list.contains(line)){
	   list.add(line);
	   occurrences++;
	}
    }
	
    // Complete This
    // return the word, the number of occurrences, and the lines it appears on.
    // string must be one line
    public boolean isContains(int line){
	return list.contains(line);
    }
	
    public String toString(){
	String ret = this.word;
	ret += " :- occurrences: " + String.valueOf(this.occurrences);
	ret += "| line numbers:" + this.list.toString();	
	return ret;
	
    }

    
	
	
}
