package graph;
import java.util.ArrayList;
import java.util.List;

public class DepthFirstTraversal <T> extends AdjacencyGraph <T> implements Traversal <T> {
	private List<T> traversal = new ArrayList<T>();
	
	@Override
	public List<T> traverse() throws GraphError {
		T node = getUnvisitedNode();
		while (node != null) {
			traverse(node);
			node = getUnvisitedNode();
		}
		return traversal;
	}
	
	void traverse(T node) {
		
	}

	T getUnvisitedNode() {
		return null;
	}
}
