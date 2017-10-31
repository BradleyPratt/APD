package arraySorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Quick sort class to sort the array using a quick sort
 * 
 * @author Bradley Pratt
 */
public class QuickSort<T extends Comparable<? super T>> extends ArraySortTool<T> {
	public void sort(T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				T pivot = null;
				array[0] = pivot;
			}
		}
    }
	
	public static void main(String[] args){
	    QuickSort sort = new QuickSort();

	    Integer[] arr = {3,4,1,5};
	    System.out.println(Arrays.toString(arr));
	    sort.sort(arr);
	    System.out.println(Arrays.toString(arr));
	}
}

