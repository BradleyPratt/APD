package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class RefCountTopologicalSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {	
	private HashMap<T,Integer> refCountTable = new HashMap<T,Integer>();
	private ArrayList<T> sort = new ArrayList<T>();
	
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
		for (T node: getNodes()) {
			for (T neighbour: getNeighbours(node)) {
				int currentCount = refCountTable.get(neighbour);
				refCountTable.put(neighbour, currentCount);
			}
		}
	}

	private void initialiseRefCounts() {
		for (T node: getNodes()) {
			refCountTable.put(node, 0);
		}
	}
	
	private void sort() throws GraphError {
		T node;

        while ((node = nextReferenceZeroNode()) != null) {
        	System.out.println(node);
             for (T neighbour: getNeighbours(node)) {
                 Integer count = refCountTable.get(neighbour);
                 if (count != null) {
                     refCountTable.put(neighbour, count--);
                 }
                 refCountTable.put(node, count);
             }
	         refCountTable.remove(node);
	         sort.add(node);
        }
	}
	
    private T nextReferenceZeroNode(){
        for (Entry<T, Integer> entry : refCountTable.entrySet()) {
            if(entry.getValue() == 0){
                return (T) entry.getKey();
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
        graph.add(node0);
        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        graph.add(node5);
        graph.add(node6);
        graph.add(node7);
        graph.add(0, 3);
        graph.add(0, 4);
        graph.add(1, 4);
        graph.add(2, 5);
        graph.add(3, 6);
        graph.add(4, 6);
        graph.add(5, 7);
        graph.add(6, 7);

        graph.getSort();   
        System.out.println("Reference Count Topological Sort: "+Arrays.toString((graph.sort.toArray())));
    }
}
