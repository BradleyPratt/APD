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
	private List<T> visited = new ArrayList<T>();
	
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
				refCountTable.put(neighbour, ++currentCount);  
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

		/*while ((node = nextReferenceZeroNode()) != null) {
			System.out.println(node); 
			for (T neighbour: getNeighbours(node)) {
				Integer count = refCountTable.get(neighbour);
				refCountTable.put(neighbour, count-1); 
			}
			refCountTable.remove(node);
			sort.add(node);
			}*/
		while ((node = nextReferenceZeroNode()) != null) {
			//for (T node: getNodes()) {
				if (!visited.contains(node)) {
					visitNode(node);
				}
				
				//Integer count = getNeighbours(node).size();
				//refCountTable.put(node, count-1);
				//refCountTable.remove(node);
				/*if (count == 0) {
					refCountTable.put(node, count++);
				} 
				refCountTable.put(node, count-1);
		        refCountTable.remove(node);
				sort.add(node);*/
			//}
			//sort.add(refCountTable.);
		}
	}
	
	private void visitNode(T node) throws GraphError {
		if (visited.contains(node)) {
			return;
		}
		visited.add(node);
		for (T neighbour: getNeighbours(node)) {
			System.out.println(getNeighbours(neighbour));
			if (refCountTable.get(neighbour) != null) {
				int currentCount = refCountTable.get(neighbour);
				System.out.println(currentCount);
				refCountTable.put(neighbour, currentCount-1);
			}
			visitNode(neighbour);
		}
		/*while ((node = nextReferenceZeroNode()) != null) {
			//for (T neighbour: getNeighbours(node)) {
				//T neighbour = getNeighbours();
				int currentCount = refCountTable.get(node);
				//System.out.println(currentCount);
				refCountTable.put(node, currentCount-1);
				visitNode(node);
			//}
			//refCountTable.remove(node);

		}*/
		refCountTable.remove(node);
		sort.push(node);
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
