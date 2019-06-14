package circularlinkedlist;
import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E> {

	
	
    // Your variables
    
    Node<E> head;
    Node<E> tail;
    static int size = 0;  // BE SURE TO KEEP TRACK OF THE SIZE


        
	
    // implement this constructor
	
    public CircularLinkedList() {
    }


    // I highly recommend using this helper method
    // Return Node<E> found at the specified index
    // be sure to handle out of bounds cases
    private Node<E> getNode(int index) {
	if(size == 0){
	    return null;
	}

	else{
	    if (index > size)
		throw new IndexOutOfBoundsException(index);
		
	    Node<E> tmp = head;
	    for(int i = 0; i < index; i++){
		tmp = tmp.next;
	    }
	    return tmp;
	}
    }

    // attach a node to the end of the list
    public void add(E item) {
	if(size >= 1){
	    Node<E> tmp = getNode(size-1);
	    Node<E> newNode = new Node<E>(item);
	    size++;
	    newNode.next = head;
	    tmp.next = newNode;
	    tail = newNode;
	}

	else{
	    Node<E> newNode = new Node<E>(item);
	    size = 1;
	    newNode.next = head = tail = newNode;
	}
	

    }

	
    // Cases to handle
    // out of bounds : getNode will throw exception.
    //will ignore the index if not initialized.
    
    // adding to empty list
    // adding to front
    // adding to "end"
    // adding anywhere else
    // REMEMBER TO INCREMENT THE SIZE
    public void add(int index, E item){

	if(size >= 1){
	    
	    Node<E> tmp = getNode(index -1);
	    Node<E> newNode = new Node<E>(item);
	    newNode.next = tmp.next;
	    tmp.next = newNode;
	    size++;
	    if(index == size-1){
		tail = newNode;
	    }
	}

	else{
	    Node<E> newNode = new Node<E>(item);
	    newNode.next = newNode;
	    size = 1;
	    head = tail = newNode;
	}
	
    }

	

	
	
    // remove must handle the following cases
    // out of bounds
    // removing the only thing in the list
    // removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
    // removing the last thing 
    // removing any other node
    // REMEMBER TO DECREMENT THE SIZE
    public E remove(int index) {

	
	if(size != 0)
	    switch(index){

	    case 0:
		Node<E> tmp1 = head;
		head = head.next;
		tail.next = head;
		size--;
		return tmp1.item;
	
	
	    default:
		if(index == size -1){

		    getNode(size-2).next = head;
		    Node<E> tmp2 = tail;
		    tail = getNode(size-2);
		    size--;
		    return tmp2.item;
		}
		
		Node<E> tmp3 = getNode(index-1);
		Node<E> retVal = tmp3.next;
		tmp3.next = tmp3.next.next;
		size--;
		return retVal.item;
	    
	    }
	
	return null;
    }
	
	
	
	
    // Turns your list into a string
    // Useful for debugging
    public String toString(){
	Node<E> current =  head;
	StringBuilder result = new StringBuilder();
	if(size == 0){
	    return "";
	}
	if(size == 1) {
	    return head.item.toString();
			
	}
	else{
	    do{
		result.append(current.item);
		result.append(" ==> ");
		current = current.next;
	    } while(current != head);
	}
	return result.toString();
    }
	
	
    public Iterator<E> iterator() {
	return new ListIterator<E>();
    }
	
    // provided code for different assignment
    // you should not have to change this
    // change at your own risk!
    // this class is not static because it needs the class it's inside of to survive!
    private class ListIterator<E> implements Iterator<E>{
		
	Node<E> nextItem;
	Node<E> prev;
	int index;
		
	@SuppressWarnings("unchecked")
	//Creates a new iterator that starts at the head of the list
	public ListIterator(){
	    nextItem = (Node<E>) head;
	    index = 0;
	}

	// returns true if there is a next node
	// this is always should return true if the list has something in it
	public boolean hasNext() {
	    // TODO Auto-generated method stub
	    return size != 0;
	}
		
	// advances the iterator to the next item
	// handles wrapping around back to the head automatically for you
	public E next() {
	    // TODO Auto-generated method stub
	    prev =  nextItem;
	    nextItem = nextItem.next;
	    index =  (index + 1) % size;
	    return prev.item;
	
	}
		
	// removed the last node was visted by the .next() call 
	// for example if we had just created a iterator
	// the following calls would remove the item at index 1 (the second person in the ring)
	// next() next() remove()
	public void remove() {
	    int target;
	    if(nextItem == head) {
		target = size - 1;
	    } else{ 
		target = index - 1;
		index--;
	    }
	    CircularLinkedList.this.remove(target); //calls the above class
	}
		
    }
	
    // It's easiest if you keep it a singly linked list
    // SO DON'T CHANGE IT UNLESS YOU WANT TO MAKE IT HARDER
    private static class Node<E>{
	E item;
	Node<E> next;
		
	public Node(E item) {
	    this.item = item;
	}
		
    }
	

   

    public static void main(String[] args){
	CircularLinkedList<Integer> nel = new CircularLinkedList<Integer>();

	int k = 200;
	System.out.printf("k val: %d\n", k);
	for(int i = 0; i < 10; i++)
	    nel.add(i + 1);
	
	System.out.printf("%s\n", nel.toString());

	
	Iterator<Integer> mainLoop;
	
	    
	for(int i = 0; i < 10; i++){

	    mainLoop = nel.iterator();
	    for(int j = 0; j < k; j++){
		mainLoop.next();
	    }
	    mainLoop.remove();
	    System.out.printf("%s\n", nel.toString());
	}
       



    }
	
}
