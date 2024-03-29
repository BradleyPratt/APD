package genericMethods;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenericMethodsTest {

	/**
	 * A selection of tests for equals
	 */
	
	@Test
	public void testEqualIntegers() {
		assertEquals(true,GenericMethods.equals(3, 3));
	}
	
	@Test
	public void testNotEqualIntegers() {
		assertEquals(false,GenericMethods.equals(3, 7));
	}

	@Test
	public void testEqualStrings() {
		assertEquals(true,GenericMethods.equals("Hugh", "Hugh"));
	}
	
	@Test
	public void testNotEqualStrings() {
		assertEquals(false,GenericMethods.equals("Hugh", "hugh"));
	}

	@Test
	public void testEqualNull() {
		assertEquals(true,GenericMethods.equals(null, null));
	}

	@Test
	public void testNotEqualNull1() {
		assertEquals(false,GenericMethods.equals(null, 4));
	}

	@Test
	public void testNotEqualNull2() {
		assertEquals(false,GenericMethods.equals("Hugh", null));
	}
	
	@Test
	public void testSwap() {
		Integer[] arrayTest = {1,2,3,4,5};
		GenericMethods.swap(arrayTest, 1, 2);
		Integer[] expected = {1,3,2,4,5};
		assertArrayEquals(expected, arrayTest);
	}
	
	@Test
	public void testMax() {
		String[] nameTest = {"Hugh", "Andrew", "Ebrahim","Diane","Paula", "Simon"};
		assertEquals("Ebrahim",GenericMethods.max(nameTest, 0, 5));
	}
}
