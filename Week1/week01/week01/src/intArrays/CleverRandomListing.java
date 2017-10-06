package intArrays;

import java.util.Arrays;
import java.util.Collections;

public class CleverRandomListing extends RandomListing {
	
	public CleverRandomListing (int size) {
		super(size);		
	}
	
	protected void randomise() {
		Integer[] copy = new Integer[getArray().length]; // Creates a new Array from the SortedListing class
		for (int index = 0; index < copy.length; index++) {
			copy[index] = index; // Builds the array until it meets the specified length.
		}
		Collections.shuffle(Arrays.asList(copy)); // Shuffles the array using a method from java.util.collections
		
		for (int index = 0; index < copy.length; index++) { 
			getArray()[index] = copy[index]; // Matches the arrays
		} 
	}
	
    public static void main(String[] args) {
    	RandomListing count = new CleverRandomListing(50); // create a new list, as long as the specified length.
    	System.out.println(Arrays.toString(count.getArray())); // prints the array to the console
    }
}