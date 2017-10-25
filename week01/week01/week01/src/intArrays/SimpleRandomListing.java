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
        super(size); //call the constructors of the parent class.
    }
    
    /**
     * Randomise the array
     */
    protected void randomise() {
        int[] copy = new int[getArray().length];
        // used to indicate if elements have been used
        boolean[] used = new boolean[getArray().length];
        Arrays.fill(used,false);
        for (int index = 0; index < getArray().length; index++) { //if index is smaller than the array length, increment index and perform for method.
            int randomIndex;
        	do {
                randomIndex = getRandomIndex(); //declare randomIndex as equal to the getRandomIndex method.
            } while (used[randomIndex]);
            copy[index] = array[randomIndex];
            used[randomIndex] = true;
        }
        for (int index = 0; index < getArray().length; index++) { // if index is less than the array length, 
            getArray()[index] = copy[index]; 
        }
    }
    
    public static void main(String[] args) {
    	RandomListing count = new SimpleRandomListing(1000000); // create a new list, as long as the specified length.
    	System.out.println(Arrays.toString(count.getArray())); // prints the .getArray method
    }

} // End of class SimpleRandomCount