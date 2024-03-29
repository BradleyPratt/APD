package search;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import intArrays.*;

public class TimedTests {
	
	private static long testStart, testEnd;
	
	private int[] smallArray = {3,2,5,1,4};
	private SearchTimer simpleTimer = new SimpleSearchTimer();

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

	public void testKofN(SearchTimer searcher,int k,int n) throws IndexingError {
		RandomListing generator = new SimpleRandomListing(n);
		testStart = System.nanoTime();
		assertEquals(n,k+searcher.findKthElement(generator.getArray(),k));
	}
	
	/*public void findKthElement(SimpleSearchTimer simpleSearchTimer, int i, int j) {
		SimpleSearchTimer generator = new SimpleSearchTimer();
		assertEquals();
	}*/

	
	@Test
	public void smallTest() throws IndexingError {
    	assertEquals(simpleTimer.findKthElement(smallArray, 2),4);
	}

}
