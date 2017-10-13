package genericMethods;

import java.util.Arrays;

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
	
	public static <T> void swap(T[] array, int index1, int index2) {
		T objectOne = array[index1];
		T objectTwo = array[index2];
		
		array[index1] = objectTwo; //Uses the defined first position and places "objectTwo" there
		array[index2] = objectOne; //Uses the defined second position and places "objectOne" there
	}
	
    public static void main(String[] args) {
    	Integer[] arrayTest = {1,2,3,4,5};
    	swap(arrayTest, 1, 4); //Chooses which objects from the defined index location to swap
    	System.out.println(Arrays.toString(arrayTest));
    }
}
