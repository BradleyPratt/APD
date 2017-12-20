package linkedList;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SinglyLinkedListTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
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
}
