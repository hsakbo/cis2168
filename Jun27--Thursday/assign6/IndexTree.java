package index;

import java.io.File;
import java.util.*;

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {

    // This is your root 
    // again, your root does not use generics because you know your nodes
    // hold strings, an int, and a list of integers
    public IndexNode root;
    
	
    // Make your constructor
    // It doesn't need to do anything
    public IndexTree(){
	root = null;	
    }

  
	
    // complete the methods below
	
    // this is your wrapper method
    // it takes in two pieces of data rather than one
    // call your recursive add method
    public void add(String word, int lineNumber){

	//for debug
	if(myAdd(root, word, lineNumber) == null);
	    // System.out.printf("%s\n", "myAdd in line 36 returned null");

	return;
	
    }

    
    private IndexNode myAdd(IndexNode locRoot, String wrd, int line){

	
	IndexNode retNode;
	
	if(root == null)
	    root = new IndexNode(wrd, line);
	    
		

	else if(locRoot == null){
	    IndexNode newNode = new IndexNode(wrd, line);
	    return newNode;
	}

	else if(locRoot.word.equals(wrd)){
	    locRoot.add(line);
	    return locRoot;
	}

	else if(locRoot.word.compareTo(wrd) > 0){
	    	    
	    retNode = myAdd(locRoot.left, wrd, line);
	    locRoot.left = retNode;
	    return locRoot;
	}

	else if(locRoot.word.compareTo(wrd) < 0){
	    retNode = myAdd(locRoot.right, wrd, line);
	    locRoot.right = retNode;
	    return locRoot;
	}

	return null;
	    
	
    }
	
	
    // your recursive method for add
    // Think about how this is slightly different the the regular add method
    // When you add the word to the index, if it already exists, 
    // you want to  add it to the IndexNode that already exists
    // otherwise make a new indexNode
    // private IndexNode add(IndexNode root, String word, int lineNumber){
    //	return null;
    //}
	
	
	
	
    // returns true if the word is in the index
    public boolean contains(String word){
	return binTsearch(root, word);

    }

    public boolean binTsearch(IndexNode locRoot, String word){
	if(locRoot == null)
	    return false;

	else if(locRoot.word.equals(word))
	    return true;

	else if(locRoot.word.compareTo(word) > 0)
	    return binTsearch(locRoot.left, word);

	else if(locRoot.word.compareTo(word) < 0)
	    return binTsearch(locRoot.right, word);

	return false;
	
    }
	
    // call your recursive method
    // use book as guide

    //It may differ from the book, because I wanted to get the answer myself first.
    public void delete(String word){
	
	//using a dummy node to make it delete the root node. Otherwise the cascading-reassigning  delete() will not replace the root and just return new root.
	
	if(contains(word)){
	    IndexNode dummy = new IndexNode("" + 0, 0);
	    dummy.right = this.root;
	    root = delete(dummy, word).right;
	    dummy = null;
	    
	}
    }
	
    // your recursive case
    // remove the word and all the entries for the word
    // This should be no different than the regular technique.
    
    private IndexNode delete(IndexNode locRoot, String word){

	IndexNode retNode;
	if(locRoot == null)
	    return null;

	//I could have made the wrapper simpler by making a case for root here,
	//but it's time costing with an extra if.

	//matched with a local root case
	else if(locRoot.word.equals(word)){
   
	    if(locRoot.left == null){
		//System.out.printf("a");
		return locRoot.right;
	    }

	    //go left once
	    retNode = locRoot.left;
	    if(retNode.right == null){
		//System.out.printf("aaaah..");
		retNode.right = locRoot.right;
		return retNode;		
	    }

	    

	    //assign retNode to one before the right most node
	    while(retNode.right.right != null)
		retNode = retNode.right;
	    
	    
	    
	    IndexNode temp = retNode.right; //this will return
	    
	    retNode.right = retNode.right.left; //the subtree of temp.left is attached to the retNode.right
	    
	    temp.left = locRoot.left; //fixing the left
	    temp.right = locRoot.right;	//fixing the right

	    return temp;	    
		
	}


	//preorder traversal	
	else if(locRoot.word.compareTo(word) > 0){    
	    retNode = delete(locRoot.left, word);
	    locRoot.left = retNode;

	}

	else if(locRoot.word.compareTo(word) < 0){    
	    retNode = delete(locRoot.right, word);    
	    locRoot.right = retNode;
	    	    
	}
	    
	return locRoot;
    }

    
	
	
    // prints all the words in the index in inorder order
    // To successfully print it out
    // this should print out each word followed by the number of occurrences and the list of all occurrences
    // each word and its data gets its own line
    public void printIndex(){
	myPrint(root);
    }	
    

    private void myPrint(IndexNode locRoot){
	if(locRoot == null)
	    return;

	myPrint(locRoot.left);
	System.out.printf("%s\n", locRoot.toString());
	myPrint(locRoot.right);	
	return;
	
    }

    //debug methods I wrote (for deletion)
    public void printSub(String word){
	IndexNode root = subNode(this.root, word);
	myPrint(root);
    }
    
    private IndexNode subNode(IndexNode locRoot, String word){
	if(locRoot == null)
	    return null;

	else if(locRoot.word.equals(word))
	    return locRoot;
	else if(locRoot.word.compareTo(word) > 0)
	    return subNode(locRoot.left, word);
	else if(locRoot.word.compareTo(word) < 0)
	    return subNode(locRoot.right, word);

	return null;
    }
			   
    public int size(){
	return elegantSize(root);
    }
    
    private int size(IndexNode locRoot, int a){

	if(locRoot == null)
	    return a;

	a++;
	a = size(locRoot.left, a);
	a = size(locRoot.right, a);
	return a;
	
    }

    private int elegantSize(IndexNode locRoot){
	if(locRoot == null)
	    return 0;
	
	int a = 1;
	a += elegantSize(locRoot.left);
	a += elegantSize(locRoot.right);
	return a;
    }

    public boolean isFull(){
	return isFull(root);
    }

    private boolean isFull(IndexNode locRoot){

	if(locRoot == null)
	    return true;
	
	if(locRoot.right == null && locRoot.left != null)
	    return false;

	if(locRoot.left == null && locRoot.right != null)
	    return false;

	boolean l = isFull(locRoot.left);
	boolean r = isFull(locRoot.right);

	return l && r;
    }

    public boolean equals(IndexTree tree){
	return equals(root, tree.root);	
    }

    private boolean equals(IndexNode locRoot, IndexNode tree){

	if(locRoot == null && tree == null)
	    return true;

	if(locRoot == null && tree != null)
	    return false;

	if(locRoot != null && tree == null)
	    return false;

	if(!locRoot.word.equals(tree.word))
	    return false;
	    
	boolean l = equals(locRoot.left, tree.left);
	boolean r = equals(locRoot.right, tree.right);

	return l && r;
    }
    
    public static void main(String[] args){
	IndexTree index = new IndexTree();
	IndexTree index2 = new IndexTree();

	String line;
	String[] words;
	int linum = 0;
	
	File file = new File("pg100.txt");
	try{
	    Scanner sc = new Scanner(file);
	    while(sc.hasNextLine()){
		linum++;
		line = sc.nextLine();
		words = line.split("\\s+");
		//System.out.printf("%s\n", Arrays.toString(words));
		
		for(String word : words){
		    if(word.equals(""))
			continue;

		    word = word.replaceAll("[^a-zA-Z]", "");
		    //System.out.printf("%s\n", word);
		    index.add(word, linum);
		    index2.add(word, linum);
		}
		
	    }
	    sc.close();	    
	}

	catch(Exception e){
	    //e.printStactTrace();
	    System.out.printf("Some exception in try catch\n");
	}
	
	
	// add all the words to the tree
		
	// print out the index

	System.out.printf("\n\n\n");
	//index.printIndex();
	
	//index.delete("zodiacs");
	//index.delete("zwaggerd");
	System.out.printf("%d\n", index.size());
	index.delete("the");
	index2.delete("the");
	
	//System.out.printf("\n\n\n\tAFTER DELETE\n\n\n");
	//index.printIndex();	
	//System.out.printf("%d\n", index.size());

	//there was some complex debugging, the tenderbodied was not chosen out of random.
	//System.out.printf("%s\n", index.contains("tenderbodied"));
	//index.printSub("the");
	//System.out.printf("%s\n", index.isFull());

	System.out.printf("%s\n", index.equals(index2));
	


	
	// test removing a word from the index

		
    }
}
