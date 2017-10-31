package arraySorter;

import java.util.Arrays;

import org.junit.Test;

import RandomArray.RandomArray;
import RandomArray.RandomIntegerArray;

public class QuickSortTest extends SortTester<Integer>{
	private QuickSort<Integer> sorter = new QuickSort<Integer>();
	private RandomArray<Integer> generator = new RandomIntegerArray(1000000);
	
    /**
     * Default constructor for test class SelectionSortTest
     */
    public QuickSortTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    public void test10()
    {
        super.test(sorter,generator,10);
    }

    public void test100()
    {
        super.test(sorter,generator,100);
    }

    public void test1000()
    {
        super.test(sorter,generator,1000);
    }

    public void test10000()
    {
        super.test(sorter,generator,10000);
    }
    
    @Test
    public void arrayTest() {
	    QuickSort firstsort = new QuickSort();

	    Integer[] arr = {3,4,1,5};
	    System.out.println(Arrays.toString(arr));
	    firstsort.sort(arr);
	    System.out.println(Arrays.toString(arr));
    }
}
