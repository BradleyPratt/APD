package genericMethods;

import java.util.Arrays;
import java.util.Scanner;

public class GenericMethods {	
	/**
	 * Check if two objects are equal
	 * @param object1 the first object
	 * @param object2 the second object
	 * @return true if the objects are equal (according to the equals() method)
	 */
	public static <T> boolean equals(T object1,T object2) {
		if (object1==null) {
			return object2==null;
		} else {
			return object1.equals(object2);
		}
	}
	
	/**
	 * Swaps the specified elements within the array
	 * @param array the array which is passed into the method
	 * @param index1 the index which needs to be swapped with index2
	 * @param index2
	 */
	public static <T> void swap(T[] array, int index1, int index2) {
		T objectOne = array[index1];
		T objectTwo = array[index2];
		
		array[index1] = objectTwo; //Uses the defined first position and places "objectTwo" there
		array[index2] = objectOne; //Uses the defined second position and places "objectOne" there
	}
	
	/**
	 * The purpose of max is to find the largest element in between index1 and index2.
	 * @param array is the array that is passed in
	 * @param index1 is the first index, which elements before it may be ignored
	 * @param index2 is the second index location, which elements after it may be ignored
	 * @return returns the largest element
	 */
    public static <T> String max(String[] array, int index1, int index2) {
    	int index = 0;
    	int elementLength = array[0].length();
    	System.out.println();
    	for (int i = 0; i < array.length; i++) {
    		if (i >= index1 && i <= index2) {
    			if (array[i].length() > elementLength) {
    				index = i;
    				elementLength = array[i].length();
    			}
    		}
    	}
    	return array[index];
    }
	
    public static void main(String[] args) {
    	Integer[] arrayTest = {1,2,3,4,5};
    	swap(arrayTest, 1, 2); //Chooses which objects from the defined index location to swap
    	System.out.println(Arrays.toString(arrayTest));
    	String[] names = {"Hugh", "Andrew", "Ebrahim","Diane","Paula", "Simon"};
    	//names = max(names, 1, 4);
    	//System.out.println(Arrays.toString(names));
    	//return Arrays.toString(arrayTest);
    }
    
}
