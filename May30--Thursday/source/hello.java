import java.util.*;    

public class hello{

    public static void main(String[] args){
	/*linked list */
	System.out.println("hello");
	Scanner kb = new Scanner(System.in);

	//generics (<>) cannot use primitive classes. ie int is Integer class.
	LinkedList<Integer> listInt = new LinkedList<>();

	listInt.add(5); //add without location will append to the end
	listInt.add(8);
	listInt.add(18);

	System.out.println(listInt);

	listInt.add(0, 10);
	listInt.add(3, 30);
	listInt.remove(2);

	listInt.set(3, 0); //setter
	int i = listInt.get(3); //getter

	System.out.println(listInt);

	/*array list*/
	List<String> myList = new ArrayList<String>();
	
	KWArrayList<Integer> newList = new KWArrayList<Integer>();

	newList.add(10);
	newList.add(125);
	System.out.println(newList.toString());
	
	
	/* arrylist implementation 
	 * note: size is the total number of populated members
	 * capacity is the total number of members in a list regardless of being populated or not.
	 */

	
	
    }

}
