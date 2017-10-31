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
    SelectionSort sort = new SelectionSort();

    Integer[] arr = {3,4,1,5};
    System.out.println(Arrays.toString(arr));
    sort.sort(arr);
    System.out.println(Arrays.toString(arr));
	}
}