package sortings;
import java.util.Stack;

public class TimSortTime{

    public int[] arr;
    public int size;
    public int[] retArr; //the solution.
    public int[] inv;
    
    
    public TimSortTime(int[] unsorted, int size){
	arr = unsorted.clone();
	this.size = size;
	//retArr = new int[2];
	inv = null;
    }

    public int[] retArr(){
	return this.retArr;
    }
    
    //reverse the array    
    private void reverse(int[] src){
	int temp;
	int len = src.length/2;
	int pos = src.length - 1;

	for(int i = 0; i < len; i++){
	    temp = src[i];
	    src[i] = src[pos-i];
	    src[pos-i] = temp;	    
	}	
    }
    
    //binary search index location suitable to return.
    //genuinly struggled trying to conceive this search.
    public int binIndex(int[] src, int target){

	//description of method:
	//squeeze down the interval
	//find which boundary: return the upperbound.
	
	int range = src.length/2;
	int left = 0;
	int right = src.length-1;
	int conv = range;
	
	while(range > 0){
	    	    
	    if(target == src[conv])
		return conv+1;

	    else if(target < src[conv]){
		right = conv;
		range /= 2;
		conv = (right+left)/2;
		
	    }

	    else{
		left = conv;
		range /= 2;
		conv = (right+left)/2;
	    }
	    
	    //System.out.printf("Left: %d: %d, Right: %d: %d\n", left, src[left], right, src[right]);

	}

	if(target == src[conv])
	    return conv+1;

	else if(target < src[conv]){
	    right = conv;
	    range /= 2;
	    conv = (right+left)/2;	    
	}
	
	else{
	    left = conv;
	    range /= 2;
	    conv = (right+left)/2;
	}
	
	return right; //the upperbound

    }

    
    //no galloping, or at least, it will constantly do binary searches to find the appropriate: starting search at index .length/2 of whichever is being copied first (if statements--inefficient).
    //Maybe I will consider adding it later, but later as in after this semester ends as I don't have time to balance the others if I went through implementing galloping.
    private int[] merge(int[] s1, int[] s2){

	int[] ret = new int[s1.length + s2.length];
	int offset;
	int count1 = 0;
	int count2 = 0;
	
	/*
	  System.out.printf("\n\n\n");
	  //debug//
	  for(int i = 0; i < s1.length; i++){
	  System.out.printf("%d\n", s1[i]);
	  }
	  System.out.printf("\n\n\n");
	  //debug//
	  for(int i = 0; i < s2.length; i++){
	  System.out.printf("%d\n", s2[i]);
	  }
	  System.out.printf("\n\n\n");
	*/

	//special case (s1 is 1)
	if(s1.length == 1){
	    offset = binIndex(s2, s1[0]);
	    System.arraycopy(s2, 0, ret, 0, offset);
	    count2 = offset;
	}
	
	else if(s1[0] > s2[0]){
	    offset = binIndex(s2, s1[0]);
	    //  System.out.printf("line 124: %d %d %d\n", s1.length, s2.length, offset);
	    System.arraycopy(s2, 0, ret, 0, offset);    
	    count2 = offset; 
	}

	else{
	    offset = binIndex(s1, s2[0]);
	    //System.out.printf("line 131: %d %d %d\n", s2.length, s1.length, offset);
	    System.arraycopy(s1, 0, ret, 0, offset);
	    count1 = offset;
	}


	
	
	while(count1 < s1.length && count2 < s2.length){
	    //System.out.printf("s1: %d, s2 %d\n", s1[count1], s2[count2]);
	    if(s1[count1] < s2[count2]){
		ret[offset++] = s1[count1];
		count1++;
	    }
		
	    else if(s1[count1] > s2[count2]){
		ret[offset++] = s2[count2];
		count2++;
	    }
	    else{
		ret[offset++] = s1[count1++];
		ret[offset++] = s2[count2++];
	    }
	}		

	
	if(count1 < s1.length)
	    System.arraycopy(s1, count1, ret, offset, s1.length - count1);

	else if(count2 < s2.length)
	    System.arraycopy(s2, count2, ret, offset, s2.length - count2);


	//debug//
	//for(int i = 0; i < ret.length; i++)
	//  System.out.printf("%d\n", ret[i]);

	return ret;
	
    }

    public void invar(Stack<int[]> stack, int[] payload){

	/*
	  System.out.printf("\n");
	  for(int i = 0; i < payload.length; i++)
	  System.out.printf("payload%d: %d\n", i, payload[i]);
	  System.out.printf("\n");
	*/
	
	if(stack.isEmpty()){
	    stack.push(payload);
	    return;
	}
	
	if(inv != null){	    
	    
	    int[] fused = merge(payload, inv);
        	    	
	    if(stack.peek().length * 2 < fused.length){	
		stack.push(fused);
		inv = null;
		return;
	    }
	    inv = fused;
	    
	    return;
	}
	
	if(stack.peek().length * 2 < payload.length){
	    stack.push(payload);
	    return;
	}

	inv = payload;
	return;
    }
    
    public long sortTime(){
	
	long t = System.nanoTime();
	int count = 0;
	int aux;
	Stack<int[]> stack = new Stack<int[]>();	
	int[] insta;

	size = this.size - 1;

	while(count < size){
	    
	    aux = count;	   	    	    

	    while(arr[count] <= arr[count+1]){		
		if(count < size - 1)
		    count++;
	    }
	    
	    if(count-aux != 0){
		
		insta = new int[count-aux+1];
		System.arraycopy(arr, aux, insta, 0, count-aux+1);
		invar(stack, insta);
		count++;
		aux = count;
		
		/**debug**
		   System.out.printf("\n");
		   for(int i = 0; i < insta.length; i++)
		   System.out.printf("%d\n", insta[i]);
		*/			
		
	    }	    			    	       
	    
	    if(count == size){
		insta = new int[1];
		insta[0] = arr[size];
		invar(stack, insta);
		break;
	    }
	    
	    while(arr[count] > arr[count+1]){		
		if(count < size)
		    count++;    
	    }

	    if(count-aux != 0){		
		insta = new int[count-aux+1];
		System.arraycopy(arr, aux, insta, 0, count-aux+1);
		reverse(insta);
		invar(stack, insta);
		count++;

		/**debug*
		   System.out.printf("\n");
		   for(int i = 0; i < insta.length; i++)
		   System.out.printf("%d\n", insta[i]);
		*/				  
	    }	      
	}

	if(inv == null)
	    retArr = stack.pop();
	else
	    retArr = inv;

	//System.out.printf("%d\n", retArr.length);
	while(!stack.isEmpty()){	    
	    retArr = merge(retArr, stack.pop());
	    //System.out.printf("%d\n", retArr.length);
	}

	//System.out.printf("%d\n", retArr[0]);
	return System.nanoTime() - t;
    }
    

}
