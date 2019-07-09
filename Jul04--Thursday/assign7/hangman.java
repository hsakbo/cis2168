import java.util.*;
import java.io.File;



public class hangman{

    static int len;
    static int lifes;
    static Scanner kb;
    static Set<String> words;
    static int sols;
    static int remSig;
    static int key;
    static LinkedList<Character> pressed;

    
    //the most complicated method:
    //gets rid of elements that contain c. HOWEVER;
    //if the size of the subset of (word intersect (not c)) has a permutation larger (NOT EQUAL) than (word intersect c):
    //then it will return the subset.
    //remSig: 0 return word intersect c.
    //remSig: 1 if subset of word intersect (not c)
    public static Set remover(Set<String> words, char c){	   //not c set.
	Set<String> notC = new HashSet<String>();
	Iterator<String> iter = words.iterator();
	String tmp;
	while(iter.hasNext()){
	    tmp = iter.next();
	    if(tmp.contains("" + c)){
		iter.remove();
		notC.add(tmp);
	    }
	}
	
	//strategic location for speeeed
	if(notC.size() <= words.size()){
	    remSig = 0;
	    return words;
	}//end of speed block


	
	//start of extreme complications... blame Ian	
	int binary = 1;
	for(int i = 0; i < len; i++){
	    binary *= 2;
	}	    
	
	Set<String> subSet = null;
	//the key integer will hold info on index for each permutation.
	
	//Set<String> is the subset
	Map<Integer, Set<String>> hash = new HashMap<Integer, Set<String>>(); //mappings of permutation keys.
	int index, modulo;	
	String tmpWord = "";
	
	
	int[] binArr;
	//i=1 because truncating the possibility of all 0s (no c)
	for(int i = 1; i < binary; i++){

	    modulo = i;
	    index = len;	    	    
	    binArr = new int[len];
	    subSet = new HashSet<String>();

	    while(modulo > 0){
		index--;
		if(modulo % 2 == 1){
		    binArr[index] = 1;
		    iter = notC.iterator();
		    while(iter.hasNext()){
			tmpWord = iter.next();
			if(tmpWord.charAt(index) == c)
			    subSet.add(tmpWord);	
		    }
		}
		
		else
		    binArr[index] = 0;

		modulo /= 2;
	    }

	    
	    for(int j = 0; j < len; j++){
		if(binArr[j] == 0){
		    iter = subSet.iterator();
		    while(iter.hasNext()){
			if(iter.next().charAt(j) == c)
			    iter.remove();
		    }

		}

		else if(binArr[j] == 1){
		    iter = subSet.iterator();
		    while(iter.hasNext()){
			if(iter.next().charAt(j) != c)
			    iter.remove();
		    }
		}
		
	    }

	    //subSet is now ready to be inserted with the key i (indexes that contain c).
	    hash.put(i, subSet);
	}

	//code below compares subStrings in hash and see if it is suitable to return.
	int size = 0;
	Set<String> subs;
	key = 0;
        for(int i = 1; i < binary; i++){	    
	    subs = hash.get(new Integer(i));
	    if(size <= subs.size()){
		size = subs.size();
		subSet = subs;
		key = i;
	    }
	}

	if(size > words.size()){
	    remSig = 1;
	    return subSet;
	}

	remSig = 0;
	return words;
    }


    //initially wanted to call recursively, hence the name. I forgot midway this was a recursive function, so I decided to just go with it being normal.
    public static Set recall(char[] sub){

	//for(String word: words)
	// System.out.printf("%s\n", word);
	pCorrect(sub);				
	System.out.printf("\nGuess the letter %d, lifes: %d\n", words.size(), lifes);
       
	char c = kb.next().charAt(0);
	pressed.add(c);

	//contains in the substring check
	for(int i = 0; i < sub.length; i++){
	    if(sub[i] == c){
		lifes--;
		System.out.printf("WRONG! lifes left: %d\n\n", lifes);
		return words;
	    }
	}

	//check if scanned char is in set
	int sig = 0;
	for(String word: words){
	    if(word.contains("" + c)){
	       sig++;
	       break;
	    }
	}

	if(sig == 0){
	    lifes--;
	    System.out.printf("WRONG! lifes left: %d\n\n", lifes);
	    return words;
	}

	
	//remover call.
	words = remover(words, c);

	//when priority is given to c.
	if(remSig == 1){
	    System.out.printf("Correct!: %d lifes left\n\n", lifes);
	    int tmp = len;
	    while(key > 0){
		tmp--;
		if(key % 2 == 1){
		    sub[tmp] = c;
		    sols++;
		}
		key /= 2;
	    }
	    
	
	    
	    return words;
	    
	}		

	lifes--;
	System.out.printf("WRONG! lifes left: %d\n\n", lifes);			
	return words;
	
    }


    //printing method
    public static void pCorrect(char[] sub){
	
	for(int i = 0; i < len; i++){
	    
	    if(sub[i] != 0)
		System.out.printf("%c ", sub[i]);

	    else
		System.out.printf("_ ");

	}
	System.out.printf("\n");
	System.out.printf("Pressed: %s\n", pressed.toString());
    }

    

    //extra prints and calls the recall()
    public static void play(){
	char[] sub = new char[len];
	pressed = new LinkedList<Character>();
	
	sols = 0;
	while(lifes >= 0 && sols < len){
	    words = recall(sub);
	}

	if(sols == len){
	    pCorrect(sub);
	    System.out.printf("Congratulations, sorry for all the cheating! :p\n");
	    return;
	}
	
	System.out.printf("It's official, YOU SUCK!\n");
	for(String word: words){
	    System.out.printf("the word: %s\n", word);
	    break;
	}
	
	return;        	    	
    }   
    
    public static void main(String[] args){

	char rep = 0;
	do{
	System.out.printf("\n\n Let's Play A Game of Hangman\n\n");
	words = new HashSet<String>();
	System.out.printf("Enter the length of the word\n");
	kb = new Scanner(System.in);
	len = kb.nextInt();
	String word;
	
	try{
	    File file = new File("words.txt");
	    Scanner sc = new Scanner(file);
	    while(sc.hasNextLine()){
		word = sc.nextLine().toLowerCase();
		if(word.length() == len)
		    words.add(word);
	    }
	    sc.close();
	}
	catch(Exception e){
	    System.out.printf("try block fail\n");
	}



	if(words.isEmpty()){
	    System.out.printf("invalid length\n");
	    kb.close();
	    return;
	}

	System.out.printf("Enter the number of wrong guesses\n");
	lifes = kb.nextInt();

	
	System.out.printf("\n\nA word has been chosen, take a guess\n\n");
	
	play();
	System.out.printf("wanna play again? (y or n) \n");
	rep = kb.next().toLowerCase().charAt(0);
	
	}while (rep == 'y');
	
	kb.close();//kinda redundant because most modern os free orphaned memory. But it's apparently "good practice".
    }
}


