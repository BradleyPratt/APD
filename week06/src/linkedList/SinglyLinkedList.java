package linkedList;

public class SinglyLinkedList<T> implements List<T> {
	private Node<T> head = null;
	private Node<T> tail = null;
	private int noOfNodes = 0;
	
	@Override
	public boolean isEmpty() {
		return (head == null); // Checks if empty by seeing if there is a head
	}

	@Override
	public void add(int index, T value) throws ListAccessError {
	    Node<T> addNode = new Node<T>(value); //Defines the value as a new "addNode"
	    if (isEmpty()) { //If the list is empty it adds it at the head
	      head = addNode;
	      tail = head;
	    } else {
	    	Node<T> node = head;
	    	for (int i = 0; i < index-1; i++) { //Traverse through each node but -1 to set next later
	    		node = node.getNext(); 
	    	}
	    	node.setNext(addNode); //Continuation of the -1 so the next position can be set
	    	node = addNode; //Sets the node to addNode from the beginning 
	    }
	    noOfNodes++;
	}

	@Override
	public T remove(int index) throws ListAccessError {
    	Node<T> node = head;
    	Node<T> temp = getNode(index);
    	//Node<T> previousNode = null;
    	
	    //Node<T> removeNode = new Node<T>(value); //Defines the value as a new "addNode"
	    if (isEmpty()) { //If the list is empty it adds it at the head
	    	return null;
	    } else {
	    	if (index < 0 || index >= noOfNodes) { // invalid index
	    		throw new ListAccessError("Index out of bounds");
	    	}
	    	for (int i = 0; i < index-1; i++) { //Traverse through each node but -1 to set next later
		    	node.getNext(); //Continuation of the -1 so the next position can be set
	    		//aheadNode = node.getNext();
	    		//previousNode = getNode(i-1);
	    	}
	    	node.setNext(temp);
	    	//node = addNode; //Sets the node to addNode from the beginning 
	    }
	    noOfNodes--;	
	    return node.getValue();
	}

	@Override
	public T get(int index) throws ListAccessError {
		return getNode(index).getValue();
	}
    private Node<T> getNode(int index) throws ListAccessError {
    	if (index < 0 || index >= noOfNodes) { // invalid index
    		throw new ListAccessError("Index out of bounds");
    	}
    	Node<T> node = head; // start at head of list
    	for (int i = 0; i < index; i++) { // walk through list to desired index
    		node = node.getNext(); // by following next references
    	}
    	return node; // return the node at the required index
    }
}
