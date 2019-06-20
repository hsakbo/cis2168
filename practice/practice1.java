
import java.util.*;

public class practice1{

    //q18
    public <E> void add(E item){
	Node<E> temp;

	int size = list.size();//O(1) amortized
	for(Node meee: list){
	    if(temp.compareTo(item) <= 0){
		temp = mee; 
		break;
	    }
	    temp = null;
	    	
	}

	//handle head
	if(temp != null){
	    Node<E> neew = new Node(item);
	    neew.next = temp;
	    neew.prev = temp.prev;
	    neew.prev.next = neew;
	    temp.prev = neew;
	    size++;
	    
	}

	//handle head and tail
	else{
	    temp.next = neew;
	    neew.prev = temp;
	    size++;
	}
	

	//for(Node a: list)
	//new Node(item)
       
	    


	
    }

    public static void main(String[] args){

	List<String> list = new LinkedList<String>();
	list.add("hello");
	list.add("world");
	list.add("nice");
	String temp;
	String rev;
	for(int i = 0; i < list.size(); i++){
	    rev = "";
	    temp = list.get(i);
	    for(int j = 0; j < temp.length(); j++)
		rev = temp.charAt(j) + rev;

	    list.set(i, rev);
	    System.out.printf("%s\n", list.get(i));
	}

	


       
	
    }

}
