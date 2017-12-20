package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

public class RefCountTopologicalSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {	
	private HashMap<T,Integer> refCountTable = new HashMap<T,Integer>();
	private Stack<T> sort = new Stack<T>();
	
	@Override
	public List<T> getSort() throws GraphError {
		setUpRefCounts();
		sort();
		
		return sort;
	}

	private void setUpRefCounts() throws GraphError {
		initialiseRefCounts();
		countReferences();		
	}

	private void countReferences() throws GraphError {
		for (T node: getNodes()) { //Get all the nodes and go through them one by one
			for (T neighbour: getNeighbours(node)) { //Get the neighbours of the selected node
				int currentCount = refCountTable.get(neighbour); //Get the number of children of the neighbour
				refCountTable.put(neighbour, ++currentCount); //Increment the current count in the refCountTable for the amount of neighbours
			}
		}
	}

	private void initialiseRefCounts() {
		for (T node: getNodes()) { // Gather the nodes
			refCountTable.put(node, 0); // Declare all nodes in the table as a count of 0
		}
	}
	
	private void sort() throws GraphError {		
		T node; // Declare the node as a variable

		while ((node = nextReferenceZeroNode()) != null) { // Check the nodes next reference  and continue the loop whilst it isn't null
			for (T neighbour: getNeighbours(node)) { // Check the neighbours of the node
				Integer count = refCountTable.get(neighbour); // Get the amount of neighbours from the refCountTable
				if (count != null) {
					refCountTable.put(neighbour, count-1);  // Reduce the count from the refCountTable by 1 for each neighbour
				}
				refCountTable.put(node, count-1);
			}
		refCountTable.remove(node); // Remove the node from the refCountTable
		sort.add(node); // Add the node to the sort
		}
	}
	
    private T nextReferenceZeroNode(){
        for (Entry<T, Integer> entry : refCountTable.entrySet()) { // Get each T and Integer from the entrySet
            if(entry.getValue() == 0){
                return (T) entry.getKey(); // Returns the getKey to the sort
            }
        }
        return null;
    }

    public static void main(String[] args) throws GraphError{
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

        graph.getSort();   
        System.out.println("Reference Count Topological Sort: "+Arrays.toString((graph.sort.toArray())));
    }
}
