package intArrays;

import java.util.Arrays;

public class CleverRandomListing extends RandomListing {
	
	public CleverRandomListing (int size) {
		super(size);		
	}
	
	protected void randomise() {
		
		for (int index = 0; index < getArray().length; index++) {
			int randomArray = getRandomIndex(); // Uses the getRandomIndex method to randomise the array index
			
			int newInt = getArray()[randomArray];
			getArray()[randomArray] = getArray()[index]; // Changes the grabbed array to randomise its index
			getArray()[index] = newInt; // Builds the array using its new index
		}
	}
	
    public static void main(String[] args) {
    	RandomListing count = new CleverRandomListing(50); // create a new list, as long as the specified length.
    	System.out.println(Arrays.toString(count.getArray())); // prints the array to the console
    }
}