package com.xioq.dasacumen.lib.utilities;
import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * Used to generate a unique random value.
 * @author JPartington
 *
 */
@Component("uidValueGenerator")
public class UidValueGenerator {
	
	public UidValueGenerator() {
	}
	
	/**
	 * Used to generate a random value. 
	 * @return the random value
	 */
	public String generate()
	{
		// This generates a random number
		Random rand = new Random();  
    	int aNum = rand.nextInt(89999999) + 10000000;
    	
    	//This changes the int to a String
    	String ranNum = (Integer.toString(aNum));
    	
    	//Check digit algorithm
    	int sum = 0;
    	for(int i = 0; i < ranNum.length(); i++) {
    		
    		char ch = ranNum.charAt(ranNum.length() - i - 1);
    		int digit = (int)ch - 48;
    		int weight;
    		
    		if(i % 2 ==0){
    			weight = (2 * digit) - (int) (digit / 5) * 9;
    		} else {
    			weight = digit;
    		}
    		
    		sum += weight;
    	}

		sum = Math.abs(sum) +10;
		//This is the unique number that is generated from the sum of the numbers within the random number
		int unqNum = (10 - (sum % 10)) % 10;
		
		//Unique number is changed to a string and added onto the random number that was generated.
   		String uid = (ranNum) + Integer.toString(unqNum);
   		
		return "prefix_" + uid ;
	}
}

