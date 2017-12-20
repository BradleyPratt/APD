package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepthFirstTraversal <T> extends AdjacencyGraph <T> implements Traversal <T> {
	private List<T> traversal = new ArrayList<T>();
	private List<T> visited = new ArrayList<T>();
	
	@Override
	public List<T> traverse() throws GraphError {
		while(visited.size() < getNodes().size()) { //Makes sure that the visited array is smaller than the amount of nodes
			for (T node: getNodes()) { //Goes through the nodes one by one to build the array
				getUnvisitedNode(node); //Checks the node to see if it has been visited before
				traverse(node); //Beings the depth first traversal with the new node
			}
		}
		return traversal; // Returns the array once its completed
	}
	
	void traverse(T node) throws GraphError {
		for (T neighbour: getNeighbours(node)) { //Goes through each neighbour of the node
			node = neighbour; //Changes the node to the neighbour
			if(node != null && !visited.contains(node)) // If the node hasn't been visited(to prevent going to the same neighbour over and over) 
			{
				traversal.add(node); //Adds the node to the array
				visited.add(node); //Adds the node to the visited array so it prevents infinite loops
				traverse(node); //Traverses again
			}
		}
	}

	T getUnvisitedNode(T node) throws GraphError {
		visited.add(node); //Adds the node to the visited array to prevent infinite loops
		return node; //Returns the node to the traverse
	}
	
	public static void main(String arg[]) throws GraphError
	{
		DepthFirstTraversal<Integer> graph = new DepthFirstTraversal<Integer>();
        Integer node0 = new Integer(0);
        Integer node1 = new Integer(1);
        Integer node2 = new Integer(2);
        Integer node3 = new Integer(3);
        Integer node4 = new Integer(4);
        Integer node5 = new Integer(5);   
        graph.add(node0);
        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        graph.add(node5);
        graph.add(0, 1);
        graph.add(0, 3);
        graph.add(1, 2);
        graph.add(2, 1);
        graph.add(2, 4);
        graph.add(2, 5);
        graph.add(4, 5);
        graph.add(5, 4);
        
        graph.traverse();   
        System.out.println("Depth First Traversal: "+Arrays.toString((graph.traversal.toArray())));
	}
}
