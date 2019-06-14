public class test{

    public static String reverse(String word){
	String reversed = "";
	for(int i = 0; i < word.length(); i++){
	    reversed = reversed + word.charAt(word.length() -i -1);
	    
	}

	return reversed;
	    
    }

    public static void main(String[] args){
	String ret = "hello world";
	System.out.printf("%s\n", reverse(ret));

	//slide >110
	//iterator has boolean hasNext(), E next(), void remove().
	//iterator is an object and E next returns an iterator object.

	//linkedlist remove is O(n^2) but iterator.remove is O(n).
	//first iterator call will go to the first element of the list.

	//	Iterator<Integer> iter = aList.Iterator();
	//while(iter.hasNext()){
	//    int nextInt = iter.next();
	//    ...
	//	}
	//O(n) when traversing the list with iterator.
	

	// *****enhanced for loop is introduced after Java 5.0

	//for (String nextStr: myList) {
	// ... nextStr

	//sum = 0; example of unboxing done by Java of converting Integer to int primitive.
	//for (int nextInt: myList){
	//sum += nextInt;
	//}

	//comparable interface.

	//public interface Iterable<E> {
	// returns an iterator over the elements in this collection. */
	//  Iterator<E> iterator();
	
	//}ListIterator<E> iter = theList.listIterator();
	//listIterator() will implement compareTo.
	//while(iter.hasNext()){
	//if(e.compareTo(iter.next()) < 0){
	//found element > new one the compareTo is already implemented by the constructor of 
	// iter.previous();
	//iter.add(e);
	//return;}}

	
	
	
	
    }

}
