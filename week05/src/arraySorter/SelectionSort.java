package arraySorter;

import java.util.Arrays;

/**
 * Selection sort class, sorts the array using selection sort
 * 
 * @author Bradley Pratt
 */
public class SelectionSort<T extends Comparable<? super T>> extends ArraySortTool<T> {
	
	public void sort(T[] array) {
    	for (int i = 0; i < array.length; i++) {
    		int minIndex = i;
    		for (int j = i + 1; j < array.length; j++) {
    			if (array[j].compareTo(array[minIndex]) < 0) {
    				minIndex = j;
    			}
    		}
    		if (minIndex != i) {
    			T temp = array[i];
    			array[i] = array[minIndex];
    			array[minIndex] = temp;
    		}
		}
	}


public static void main(String[] args){
    SelectionSort firstsort = new SelectionSort();

    Integer[] arr = {3,4,1,5};
    System.out.println("before sorting int: "+ Arrays.toString(arr));
    firstsort.sort(arr);
    System.out.println("After sorting int : "+Arrays.toString(arr));
     String[] arr1= {"acd","ded","dal","bad","cle"};
     System.out.println("before sorting String: "+ Arrays.toString(arr1));
     firstsort.sort(arr1);
     System.out.println("After sorting String : "+Arrays.toString(arr1));
     Character[] arr2= {'c','e','a','d','c'};
     System.out.println("before sorting char: "+ Arrays.toString(arr2));
     firstsort.sort(arr2);
     System.out.println("After sorting char : "+Arrays.toString(arr2));
	}
}