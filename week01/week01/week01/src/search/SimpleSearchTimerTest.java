package search;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import intArrays.SimpleRandomListing;

public class SimpleSearchTimerTest extends TimedTests{

	private static long testStart, testEnd;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Rule public TestName testName = new TestName();

	@Before
	public void setUp() throws Exception {
		testStart = System.nanoTime();
	}

	@After
	public void tearDown() throws Exception {
		testEnd = System.nanoTime();
		System.out.println("Test \"" + testName.getMethodName() + "\" took " + (testEnd-testStart)/1000 + " microseconds");
	}
	
	@Test
	public void testFiveArray() throws IndexingError {
		int size = 25;
		int[] testNumbers;
		testNumbers = new SimpleRandomListing(size).getArray();
		SearchTimer search = new SimpleSearchTimer();
		assertEquals(21,search.findKthElement(testNumbers, 4));
	}

}
