import java.util.Arrays;

public class Rabbit extends Animal {

    

    public Rabbit(Model model, int row, int column) {
        super(model, row, column);
    }

    private static final int S_R = 2;
    private static int quadrant;
    private static int sigsafe = 0;

    private int goSafePlace(){
	//rows and cols = 20
	final int bf = 31415;
	
	
	int foxFlag, bushFlag, wallFlag, nLen, eLen, wLen, sLen, neLen, seLen, swLen, nwLen;
	foxFlag = bushFlag = wallFlag = nLen = eLen = wLen= sLen = neLen = seLen = swLen =  nwLen = bf;
	int[] direction = new int[8];
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


	int dir, bushLen = 21;
	for(int i = 0; i < 8; i++)
	    if(look(i) == Model.BUSH)
		if(distance(i) < bushLen)
		    {
			bushLen = distance(i);
			dir = i;
		    }

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
	

	if(bushLen == 0)
	    int retVal = parking();		
	
	return Model.STAY;
    }

    private int parking(){

	int dir, tempV1, tempV2;
	int count = 0;
	for(int i=0; i<8; i++)
	    if(look(i) == Model.BUSH){
		count++;
		dir = i;
	    }
	if (count < 5){

	switch (dir){
	case Model.N:
	    tempV1 = look(Model.NE);
	    tempV2 = look(Model.NW);

	    if((tempV1 == Model.BUSH) && (tempV2 == Model.BUSH))
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
	    if (canMove(Model.N))
		return 1;
	    else
		return 3;
		}
	    break;
	case Model.E:
	    tempV1 = look(Model.NE);
	    tempV2 = look(Model.SE);
	    if((tempV1 == Model.BUSH) && (tempV2 == Model.BUSH))
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

	    if((tempV1 == Model.BUSH) && (tempV2 == Model.BUSH))
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

	    if((tempV1 == Model.BUSH) && (tempV2 == Model.BUSH))
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
	    return Model.STAY; //give up and die

        this.sigsafe = 1;
	return Model.STAY;
	
    }
    
    int decideMove(){

	if(this.sigsafe == 0)
	    int a = goSafePlace();

	
	//return random(Model.MIN_DIRECTION, Model.MAX_DIRECTION);
	return Model.STAY;
    }
}
