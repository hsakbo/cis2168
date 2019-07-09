import java.util.Map;
import java.util.HashMap;

public class hello{

    public static void main(String[] args){

	/*data collisions are ambiguous data represented on a same key.
	 *anagrams are data collision
	 *
	 */

	Map<String, Integer> hmap = new HashMap<>();
	hmap.put("Smith", 30);
	hmap.put("Anderson", 31);
	hmap.put("Smith", 20);
	
	for(Map.Entry<String, Integer> entry: hmap.entrySet())
	    {
		System.out.print("Key: " + entry.getKey() + "\t");
		System.out.println("Value: " + entry.getValue());

	    }
	

	/*
	String strText = "Good morning. Have a good class. Have a good visit. Have fun!";

	HashMap<String, Integer> map = new HashMap<>();
	String[] words = strText.toLowerCase().split("[ .!]");

	
	for(int i = 0; i < words.length; i++){
	    //complete here
	    
	}
	*/
	
    }

}
