
	int[] states = {bf, /* 0 safezone is 1 */ 
			bf, /* 1 north close */
			bf, /* 2 south close*/
			bf, /* 3 east close */
			bf, /* 4 west close */ };
			    
	//don't have time to code extra for an unlucky start where fox intercepts rabbit before entering into the escape loop pattern.

	//This part gives x and y axis awareness to the rabbit indicating it is too close to the border.
	//edit: The code becomes incredibly complicated if the rabbit just wants to distance itself from the border. Instead its quadrant is saved and will seek the complementary quadrant.

	
	if(wallFlag != bf){
	    if((nLen >= this.S_R) && (sLen >= S_R)){
		if((eLen >= S_R) && (wLen >= S_R))
		    states[0] = 1;
	    }
	    if((eLen != bf) && (eLen < S_R))
		states[3] = 1;
	    if ((wLen != bf) && (wLen < S_R))
		states[4] = 1;
		
	    if((sLen != bf) && (sLen <  S_R))
		states[2] = 1;
	    if ((nLen != bf) && (nLen <  S_R))
		states[1] = 1;
	}

	
	//this part gives (partially) diagonal awareness to the rabbit indicating that it is too close to the border like above. I will add this part if I got time but as for now it won't work.
	/*
	if(states[0] != 1)
	    {
		for(int i = 1; i < 5; i++)
		    {
			switch (i){
			case 1:
			    if(states[i] == bf)
				if(((neLen < S_R) && (seLen < S_R)) || ((nwLen < S_R) && (swLen < S_R)) || (swLen < (19-S_R)) || (seLen < (19 - S_R)))
				states[i] = 1;
			    break;
			case 2:
			    if(states[i] == bf)
				if((seLen < S_R) || (swLen < S_R) || (nwLen < (19 - S_R)) || (neLen < (19 - S_R)))
				    states[i] = 1;
			    break;
			case 3:
			    if(states[i] == bf)
				if((neLen < S_R) || (seLen < S_R) || (nwLen < (19 - S_R)) || (swLen < (19-S_R)))
				    states[i] = 1;
			    break;
			case 4:
			    if (states[i] == bf)
				if((nwLen < S_R) || (swLen < S_R) || (neLen < (19 - S_R)) || (seLen < (19-S_R)))
				    states[i] = 1;
			    break;
			}
		    }
	    }
	*/

	/*
	
	System.out.printf("\nnLen:%d  sLen:%d  wLen:%d  eLen:%d\nneLen:%d  seLen:%d  swLen:%d  nwLen:%d\n", nLen, sLen, wLen, eLen, neLen, seLen, swLen, nwLen);
	System.out.println(Arrays.toString(states));

	int[] arrlegals = {1,1,1,1,1,1,1,1};
	int[] arrConv = {nLen, neLen, eLen, seLen, sLen, swLen, wLen, nwLen};
	for(int i = 0; i < 8; i++)
	    if(canMove(i) == false)
		arrlegals[i] = 0;

	*/

