package linkedList;

public class SinglyLinkedList<T> implements List<T> {
	private Node<T> head = null;
	private Node<T> tail = null;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (head == null);
	}

	@Override
	public void add(int index, T value) throws ListAccessError {
		Node<T> nd = new Node<T>(value);
        //nd.setValue(value);
        System.out.println("Adding: "+value);
        /**
         * check if the list is empty
         */
        if(head == null){
            head = nd;
            tail = nd;
        } else {
            tail.setNextRef(nd);
            tail = nd;
        }		
	}

	@Override
	public T remove(int index) throws ListAccessError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(int index) throws ListAccessError {
		// TODO Auto-generated method stub
		return null;
	}

}
