package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
	
	/**
	 * Sorts the graph by decrementing the count of each neighbour node
	 * @throws GraphError
	 */
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
}
