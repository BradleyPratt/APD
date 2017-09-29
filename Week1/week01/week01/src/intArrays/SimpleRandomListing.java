package intArrays;

import java.util.Arrays; // in order to be able to use the fill(...) method
/**
 * An extension of RandomCount
 * 
 * @author Hugh Osborne
 * @version September 2017
 */


public class SimpleRandomListing extends RandomListing
{
    /**
     * Constructor
     */
    public SimpleRandomListing(int size) {
        super(size);
    }
    
    /**
     * Randomise the array
     */
    protected void randomise() {
        int[] copy = new int[getArray().length];
        // used to indicate if elements have been used
        boolean[] used = new boolean[getArray().length];
        Arrays.fill(used,false);
        for (int index = 0; index < getArray().length; index++) {
            int randomIndex;
        	do {
                randomIndex = getRandomIndex();
            } while (used[randomIndex]);
            copy[index] = array[randomIndex];
            used[randomIndex] = true;
        }
        for (int index = 0; index < getArray().length; index++) {
            getArray()[index] = copy[index];
        }
    }
    
    public static void main(String[] args) {
    	RandomListing count = new SimpleRandomListing(1000000);
    	System.out.println(Arrays.toString(count.getArray()));
    }

} // End of class SimpleRandomCount