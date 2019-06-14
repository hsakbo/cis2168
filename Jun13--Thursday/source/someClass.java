import java.util.*;


//stacks and queus.
//stack most operations O(1), last in first out ( Lifo)
//queue is First in First out (fifo)

    
public class someClass{


    public static boolean stacking(String ex){

	Stack obj = new Stack();
	for(int i = 0; i < ex.length(); i++){
	    if(ex.charAt(i) == '('){
		obj.push('(');
	    }
	    else if(ex.charAt(i) == '[' ){
		obj.push('[');
	    }
	    else if(ex.charAt(i) == ')'){
		if(obj.empty())
		    return false;
		if(obj.peek().equals('('))
		    obj.pop();
		else
		    return false;
	    }
	    else if(ex.charAt(i) == ']'){
		if(obj.empty())
		    return false;
		if(obj.peek().equals('['))
		    obj.pop();
		else
		    return true;
	    }
	}
	if(obj.empty())
	return true;
	else
	    return false;
	
    }
    
    public static int postFix(String[] obj){

	Stack something = new Stack();
	
	return 0;
    }

    public static int arrQ(){
	ArrayQueue q = new ArrayQueue(5);
	q.offer('*'); //adds
	q.poll(); //removes
	//this is efficient. Reallocation uses O(n)
	//spcae complexity comparisson:
	//double link queue: 1.5 times of single linked
	//circular array queue: 0.5 times of a single l.
	
	    
	
    }
    
    
    public static void main(String[] args){
	String ex = ")(a+[b-c+d])";
	System.out.printf("%s\n", stacking(ex));
	
		    
	    
	
    }

}
