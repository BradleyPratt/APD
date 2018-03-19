package graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RefCountTopologicalSortTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	/**
	 * Runs the sort in RefCountTopologicalSort and checks to see 
	 * if the result equals the result used in the Lecture Notes.
	 * @throws GraphError
	 */
	@Test
	public void test() throws GraphError {
        RefCountTopologicalSort<Integer> graph =new RefCountTopologicalSort<Integer>();
        Integer node0 = new Integer(0);
        Integer node1 = new Integer(1);
        Integer node2 = new Integer(2);
        Integer node3 = new Integer(3);
        Integer node4 = new Integer(4);
        Integer node5 = new Integer(5);
        Integer node6 = new Integer(6);
        Integer node7 = new Integer(7);   
        Integer node8 = new Integer(8);
        Integer node9 = new Integer(9); 
        graph.add(node0);
        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        graph.add(node5);
        graph.add(node6);
        graph.add(node7);
        graph.add(node8);
        graph.add(node9);
        graph.add(1, 5);
        graph.add(0, 5);
        graph.add(1, 7);
        graph.add(3, 2);
        graph.add(3, 4);
        graph.add(3, 8);
        graph.add(6, 0);
        graph.add(6, 1);
        graph.add(6, 2);
        graph.add(8, 4);
        graph.add(8, 7);
        graph.add(9, 4);

        assertEquals(new String("[3, 6, 0, 1, 2, 5, 8, 7, 9, 4]"), Arrays.toString(graph.getSort().toArray()));
	}
}
