package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RefCountTopologicalSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {
	private Set<T> visited = new HashSet<T>();
	
	private HashMap<T,Integer> refCountTable = new HashMap<T,Integer>();
	
	@Override
	public List<T> getSort() throws GraphError {
		setUpRefCounts();
	}

	private void setUpRefCounts() throws GraphError {
		initialiseRefCounts();
		countReferences();		
	}

	private void countReferences() throws GraphError {
		for (T node: getNodes()) {
			for (T neighbour: getNeighbours(node)) {
				int currentCount = refCountTable.get(neighbour);
				//refCountTable.put(neighbour, 1);
			}
		}
	}

	private void initialiseRefCounts() {
		for (T node: getNodes()) {
			refCountTable.put(node, 0);
		}
	}
	
}
