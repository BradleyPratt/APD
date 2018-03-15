package arraySorter;

/**
 * Quick sort class to sort the array using a quick sort
 * 
 * @author Bradley Pratt
 */
public class QuickSort<T extends Comparable<? super T>> extends ArraySortTool<T> {
	public void sort(T[] array) {
		sort(array,0,array.length-1);
	}
	
	/**
	 * Method for the quicksort
	 */
	private void sort(T[] array,int from,int to) {
		if (from < to) {
			int pivotIndex = from;
			int highIndex = to;
			int lowIndex = pivotIndex;
			T pivot = array[(highIndex + lowIndex) / 2];		
			
            do { //Runs a do-while loop so that the method is ran whilst the conditions are true
                while (array[lowIndex].compareTo(pivot) < 0) lowIndex++; //Increases the lowIndex amount by the amount of elements before the pivot
                while (pivot.compareTo(array[highIndex]) < 0) highIndex--; //Reduces the highIndex amount by the amount of elements above it, meaning elements above the pivot are ignored

                if (lowIndex <= highIndex) { //Checks the size of the element to see if it can be swapped
                    T temp = array[lowIndex]; //Gets the lowIndex and places it in the generic temp
                    array[lowIndex] = array[highIndex]; //Moves the smaller element to the higher element
                    array[highIndex] = temp; //Changes the the value of highIndex to the temp
                    lowIndex++;
                    highIndex--;
                }

            } while (lowIndex <= highIndex); //Runs the do while this is true

            sort(array, from, highIndex); //Reruns the do-while loop with the new pivot
            sort(array, lowIndex, to);
		}
    }
}

