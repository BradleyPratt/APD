package linkedList;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;

public class SinglyLinkedListTest {
	
	private static long testStart, testEnd;

	@BeforeClass
	public static void setUpBeforeClass() throws ListAccessError {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
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
	public void testGetFromEmpty() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.get(0);
	}

	@Test
	public void testGetSingleton() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		assertEquals(new Integer(5),list.get(0));
	}

	@Test
	public void testGetSingletonNegative() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.get(-2);
	}

	@Test
	public void testGetSingletonOutOfBounds() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.get(2);
	}

	@Test
	public void testGet() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		assertEquals(new Integer(-6),list.get(3));
	}

	@Test
	public void testGetHead() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		assertEquals(new Integer(5),list.get(0));
	}

	@Test
	public void testGetTail() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		assertEquals(new Integer(42),list.get(5));
	}

	@Test
	public void testGetNegative() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.get(-1);
	}

	@Test
	public void testGetOutOfBounds() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.get(6);
	}
	
//////
	@Test
	public void testRemoveFromEmpty() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.remove(0);
	}

	@Test
	public void testRemoveSingleton() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		assertEquals(new Integer(5),list.remove(5));
	}

	@Test
	public void testRemoveSingletonNegative() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.remove(-2);
	}

	@Test
	public void testRemoveSingletonOutOfBounds() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.remove(2);
	}

	@Test
	public void testRemove() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		assertEquals(new Integer(-6),list.remove(3));
	}

	@Test
	public void testRemoveHead() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		assertEquals(new Integer(5),list.remove(0));
	}

	@Test
	public void testRemoveTail() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		assertEquals(new Integer(42),list.remove(5));
	}

	@Test
	public void testRemoveNegative() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.remove(-1);
	}

	@Test
	public void testRemoveOutOfBounds() throws ListAccessError {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 7);
		list.add(2, 23);
		list.add(3, -6);
		list.add(4, 0);
		list.add(5,42);
		thrown.expect(ListAccessError.class);
		thrown.expectMessage("Index out of bounds");
		list.remove(6);
	}
}
