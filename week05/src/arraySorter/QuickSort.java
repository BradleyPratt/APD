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
		sort(array,0,array.length-1);
	}
	
	private void sort(T[] array,int from,int to) {
		if (from >= to) return;
		int pivotIndex = from;
		T pivot = array[pivotIndex];
		int highIndex = to;
		int lowIndex = pivotIndex;
		
		
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				array[0] = pivotIndex;
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

