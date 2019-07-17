package sortings;
import java.util.Stack;

//just need to inherit one/two methods that needn't be copy pasted
public class TimSortCandE extends TimSortTime{

    private int[] CE; //size 2: 0 index is comparisons, 1 is exchanges

    public TimSortCandE(int[] unsorted, int size){
	super(unsorted, size);
	this.CE = new int[2];
    }

    private void reverse(int[] src){
	int temp;
	int len = src.length/2;
	int pos = src.length - 1;

	for(int i = 0; i < len; i++){
	    temp = src[i];
	    src[i] = src[pos-i];
	    src[pos-i] = temp;
	    CE[1] += 2;
	}	
    }

    public int binIndex(int[] src, int target){
	
	int range = src.length/2;
	int left = 0;
	int right = src.length-1;
	int conv = range;
	
	while(range > 0){

	    CE[0] += 2;
	    if(target == src[conv]){		
		return conv+1;
	    }	    
	    else if(target < src[conv]){
		CE[0]++;
		right = conv;
		range /= 2;
		conv = (right+left)/2;
		
	    }	    
	    else{
		CE[0]++;
		left = conv;
		range /= 2;
		conv = (right+left)/2;
	    }	    	   

	}

	CE[0] += 2;
	if(target == src[conv])
	    return conv+1;

	
	else if(target < src[conv]){
	    CE[0]++;
	    right = conv;
	    range /= 2;
	    conv = (right+left)/2;	    
	}
	
	else{
	    CE[0]++;
	    left = conv;
	    range /= 2;
	    conv = (right+left)/2;
	}	
	return right;
   }

    private int[] merge(int[] s1, int[] s2){

	int[] ret = new int[s1.length + s2.length];
	int offset;
	int count1 = 0;
	int count2 = 0;

	CE[0]++;
	if(s1.length == 1){
	    offset = binIndex(s2, s1[0]);
	    System.arraycopy(s2, 0, ret, 0, offset);    
	    count2 = offset;
	}

	
	else if(s1[0] > s2[0]){
	    CE[0]++;
	    offset = binIndex(s2, s1[0]);	    
	    System.arraycopy(s2, 0, ret, 0, offset);    
	    count2 = offset; 
	}

	else{
	    CE[0]++;
	    offset = binIndex(s1, s2[0]);
	    System.arraycopy(s1, 0, ret, 0, offset);
	    count1 = offset;
	}

	CE[1]++;
	
	while(count1 < s1.length && count2 < s2.length){  
	    CE[0] += 3; //accounting the and comparison ofwhile loop.
	    
	    if(s1[count1] < s2[count2]){
		ret[offset++] = s1[count1];
		count1++;
		CE[1]++;
	    }
	    
	    else if(s1[count1] > s2[count2]){
		CE[0]++;
		ret[offset++] = s2[count2];
		count2++;
		CE[1]++;
	    }
	    else{
		CE[0]++;
		ret[offset++] = s1[count1++];
		ret[offset++] = s2[count2++];
		CE[1] += 2;
	    }
	}		


	CE[0] += 2;
	if(count1 < s1.length)
	    System.arraycopy(s1, count1, ret, offset, s1.length - count1);
	    
	
	
	else if(count2 < s2.length)
	    System.arraycopy(s2, count2, ret, offset, s2.length - count2);

	CE[1]++;
	return ret;
	
    }

    public int[] sortCE(){	
	int count = 0;
	int aux;
	Stack<int[]> stack = new Stack<int[]>();	
	int[] insta;

	size = this.size - 1;

	while(count < size){
	    
	    aux = count;	   	    	    

	    while(arr[count] <= arr[count+1]){
		CE[0] += 2;
		count++;
		if(count == size - 1)
		    break;
	    }

	    CE[0] += 2;
	    if(count-aux != 0){
		
		insta = new int[count-aux+1];
		System.arraycopy(arr, aux, insta, 0, count-aux+1);
		CE[1]++;
		invar(stack, insta);
		count++;
		aux = count;			
		
	    }	    			    	       

	    CE[0]++;
	    if(count == size){
		insta = new int[1];
		insta[0] = arr[size];
		invar(stack, insta);
		CE[1]++;
		break;
	    }
	    
	    while(arr[count] > arr[count+1]){		
		CE[0] += 2;
		count++;
		if(count == size-1)
		    break;    
	    }

	    CE[0] += 2;
	    if(count-aux != 0){		
		insta = new int[count-aux+1];
		System.arraycopy(arr, aux, insta, 0, count-aux+1);
		CE[1]++;
		reverse(insta);
		invar(stack, insta);
		count++;       				  
	    }	      
	}

	CE[0]++;
	if(inv == null)
	    retArr = stack.pop();
	else
	    retArr = inv;

	//only comparisons with arrays
	while(!stack.isEmpty()){	    
	    retArr = merge(retArr, stack.pop());
	    
	}
	
	return this.CE;
    }
    
    

}
