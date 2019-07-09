import java.util.Arrays;

public class Rabbit extends Animal {

    

    public Rabbit(Model model, int row, int column) {
        super(model, row, column);
    }

    private static final int S_R = 2;
    private static int quadrant;
    // public static int sigsafe = 0;

    private int goSafePlace(){
	//rows and cols = 20
	final int bf = 31415; //an arbitrary int which I know will be smaller to determine the direction under an algorithm to find the minimum.
	
	
	//I basically need all these independent variables to map out the 8 possible directions along the foxFlag which is the direction of the fox
	int foxFlag, bushFlag, wallFlag, nLen, eLen, wLen, sLen, neLen, seLen, swLen, nwLen;
	foxFlag = bushFlag = wallFlag = nLen = eLen = wLen= sLen = neLen = seLen = swLen =  nwLen = bf;
	int[] direction = new int[8];

	//calculating the distances to wall, this is for the rabbit to seek the center of the board as oppose to going in random directions. I wanted to add some math and determine it on special cases but its too complicated as it is.

	
	for(int i = 0; i < 8; i++){
	    direction[i] = look(i);
	    switch (direction[i]){
	    case Model.EDGE:
		wallFlag = 1;
		switch (i){
		case Model.N:
		    nLen = distance(i) -1;
		    sLen = 19 - nLen;
		    break;
		case Model.E:
		    eLen = distance(i) -1;
		    wLen = 19 - eLen;
		    break;
		case Model.W:
		    wLen = distance(i) -1;
		    eLen = 19 - wLen;
		    break;
		case Model.S:
		    sLen = distance(i) -1;
		    nLen = 19 - sLen;
		    break;
		case Model.NE:
		    neLen = distance(i) -1;
		    break;
		case Model.SE:
		    seLen = distance(i) -1;
		    break;
		case Model.NW:
		    nwLen = distance(i) -1;
		    break;
		case Model.SW:
		    swLen = distance(i) -1;
		    break;		
		}
		break;
	    case Model.FOX:
		foxFlag = 1;
		break;
	    case Model.BUSH:
		bushFlag = 1;
		break;	   
	    }
	}


	//determining direction to the closest bush
	int dir, bushLen = 21;
	dir = Model.STAY;
	for(int i = 0; i < 8; i++)
	    if(look(i) == Model.BUSH)
		if(distance(i)-1 < bushLen)
		    {
			bushLen = distance(i) -1;
			dir = i;
		    }

	//for iteration purposes
	int[] arrConv = {nLen, neLen, eLen, seLen, sLen, swLen, wLen, nwLen};


	
	if(bushLen == 21)
	    {
		bushLen = 0;
		for (int i = 0; i<8; i++)
		    if(arrConv[i] > bushLen)
			{
			    bushLen = arrConv[i];
			    dir = i;
			}
	    }
	


	//parking algoritm call here
	if(bushLen == 0)
	    {		
		return parking(dir);
	    }

	
	//returns whichever direction it chose either through by running away from the fox or parking algorithm call below.
	//I dont call the escape algorithm here but on the decideMove()
	return dir;
    }

    
    //this algorithm determines the optimal position configuration for the rabbit to be in. I think this code is entirely redundant now as I changed my escape() function to deal with most scenarios. Either way it is still useful when I need to go back to the bush or the scenario where the fox sees rabbit from the start and manages to escape() which it can as it highly flexible on many scenarios if there is a bush.
    private int parking(int dir){
	int tempV1, tempV2;
	int count = 0;
	for(int i=0; i<8; i++)
	    if(look(i) == Model.BUSH)
		count++;
		
	if (count < 5){
	    //switch cases are coded for different bush configurations(now redundant).
	    switch (dir){
	    case Model.N:
		tempV1 = look(Model.NE);
		tempV2 = look(Model.NW);

		if((tempV1 == Model.BUSH) && (tempV2 == Model.BUSH) && !canMove(Model.NE) && !canMove(Model.NW))
		    {
			if (canMove(Model.E))
			    return Model.E;
			else if(canMove(Model.W))
			    return Model.W;
		    }
		break;
	    case Model.NE:
		if(canMove(Model.N) && canMove(Model.E))
		    {
		
			if (canMove(Model.N)){
			   
			    return Model.N;
			}
			else
			    return Model.E;
		    }
		break;
	    case Model.E:
		tempV1 = look(Model.NE);
		tempV2 = look(Model.SE);
		if((tempV1 == Model.BUSH) && (tempV2 == Model.BUSH) && !canMove(Model.NE) && !canMove(Model.SE))
		    {
		    

			if(canMove(Model.N))
			    return Model.N;
			else if (canMove(Model.S))
			    return Model.S;
		    }
		break;
	    case Model.SE:
		if(canMove(Model.E) && canMove(Model.S)){
		    if (canMove(Model.E))
			return Model.E;
		    else
			return Model.S;
		}
		break;
	    case Model.S:
		tempV1 = look(Model.SE);
		tempV2 = look(Model.SW);

		if((tempV1 == Model.BUSH) && (tempV2 == Model.BUSH) && !canMove(Model.SE) && !canMove(Model.SW))
		    {
			if (canMove(Model.E))
			    return Model.E;
			else
			    return Model.W;
		    }
		break;
	    case Model.SW:
		if (canMove(Model.S) && canMove(Model.W)){
		    if (canMove(Model.S))
			return Model.S;
		    else
			return Model.W;
		}
		break;
	    case Model.W:
		tempV1 = look(Model.NW);
		tempV2 = look(Model.SW);

		if((tempV1 == Model.BUSH) && (tempV2 == Model.BUSH) && !canMove(Model.NW) && !canMove(Model.SW))
		    {
			if (canMove(Model.N))
			    return Model.N;
			else
			    return Model.S;
		    }
		break;
	    case Model.NW:
		if(canMove(Model.N) && canMove(Model.W)){
		    if(canMove(Model.N))
			return Model.N;
		    else
			return Model.W;
		}
		break;
	
		       
	    }
	}

	else if (count < 1 && count >=5)
	    return Model.STAY; //give up and die because no solution exists due to cornering with a wall prob.

  
	return Model.STAY;
	
    }

    

    //main algorithm I call to evade fox. This takes precedence in the decideMove() above any other method.
    private int escape(){
	int foxdir = 1000;
	for (int i = 0; i<8; i++)
	    if (look(i) == Model.FOX)
		foxdir = i;   
        if(foxdir == 1000) return Model.STAY;
	
	int[] arrlegal = {0,0,0,0,0,0,0,0};
	for (int i=0; i<8; i++){
	    if(canMove(i)){
		arrlegal[i] = 1;
	    }
	}

	//I encode a priority system: 0 means illegal move, 1 means legal, 2 means priority. ie if 2 is available, go there and not 1/

	//same as above, I code for most scenarios with switch statements.
	for(int i = 0; i < 8; i+=2){
		
	    if((look(i) == Model.BUSH) && !(canMove(i))){
		switch (i){
		case Model.N:
		  
		    if(canMove(Model.NE)){
			arrlegal[Model.NE] = 2;
		    }
		    
		    if(canMove(Model.NW)){
			arrlegal[Model.NW] = 2;
		    }
		    
		    arrlegal[Model.SE] = 0;
		    arrlegal[Model.SW] = 0;
		    arrlegal[Model.S] = 0;

		    break;
		    
		case Model.E:
		
		    if(canMove(Model.NE)){
			arrlegal[Model.NE] = 2;
		    }

		    if(canMove(Model.SE)){
			arrlegal[Model.SE] = 2;
		    }

		    arrlegal[Model.NW] = 0;
		    arrlegal[Model.SW] = 0;
		    arrlegal[Model.W] = 0;
		    break;

		case Model.S:
		   
		    if(canMove(Model.SW)){
			arrlegal[Model.SW] = 2;
		    }

		    if (canMove(Model.SE)){
			arrlegal[Model.SE] = 2;
		    }

		    arrlegal[Model.NE] = 0;
		    arrlegal[Model.NW] = 0;
		    arrlegal[Model.N] = 0;
		    break;

		case Model.W:
		
		    if(canMove(Model.NW)){
			arrlegal[Model.NW] = 2;
		    }
		    if(canMove(Model.SW)){
			arrlegal[Model.SW] = 2;
		    }

		    arrlegal[Model.NE] = 0;
		    arrlegal[Model.SE] = 0;
		    arrlegal[Model.E] = 0;
		    break;
		}
	    }
	}
    

	arrlegal[foxdir] = 0;
		
	switch (foxdir){

	case Model.N:
	    arrlegal[Model.W] = 0;
	    arrlegal[Model.E] = 0;
	    arrlegal[Model.NE] = 0;
	    arrlegal[Model.NW] = 0;
	    break;

	case Model.NE:
	    arrlegal[Model.N] = 0;
	    arrlegal[Model.E] = 0;
	    break;

	case Model.E:
	    arrlegal[Model.N] = 0;
	    arrlegal[Model.S] = 0;
	    arrlegal[Model.NE] = 0;
	    arrlegal[Model.SE] = 0;
	    break;

	case Model.SE:
	    arrlegal[Model.S] = 0;
	    arrlegal[Model.E] = 0;
	    break;

	case Model.S:
	    arrlegal[Model.E] = 0;
	    arrlegal[Model.W] = 0;
	    arrlegal[Model.SE] = 0;
	    arrlegal[Model.SW] = 0;
	    break;

	case Model.SW:
	    arrlegal[Model.S] = 0;
	    arrlegal[Model.W] = 0;
	    break;

	case Model.W:
	    arrlegal[Model.N] = 0;
	    arrlegal[Model.S] = 0;
	    arrlegal[Model.NW] = 0;
	    arrlegal[Model.SW] = 0;
	    break;

	case Model.NW:
	    arrlegal[Model.N] = 0;
	    arrlegal[Model.W] = 0;
	    break;
	}
		    

	for(int i = 0; i < 8; i++){
	    if (arrlegal[i] == 2){
		return i;
	    }
	}

	for(int i = 0; i < 8; i++){
	    if (arrlegal[i] == 1){
		return i;
	    }
	}

	return Model.STAY;
		    
    }
		
    
    
    int decideMove(){

	
	int dist = 100; //100 is arbitrary. So long as it is larger than ehmm, max distance I guess?
	
	for(int i = 0; i < 8; i++)
	    if(look(i) == Model.FOX)
		dist = distance(i) - 1;

	//give precedence to escape algorithm if fox is within 2 blocks away and is visible
	if (dist < 3){
	    return escape();
	}
	
	//else just go to a safe place
	return goSafePlace();
	
	
	//System.out.println(sigsafe);
	
	//return random(Model.MIN_DIRECTION, Model.MAX_DIRECTION);
    }
}

