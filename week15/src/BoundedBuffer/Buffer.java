package BoundedBuffer;

/**
 * An implementation of a first in, first out bounded buffer using semaphores
 * 
 * @author Hugh Osborne
 * @version January 2013
 */
import Semaphore.*;

public class Buffer<T>
{
    private Semaphore noOfSpaces,  // measures the number of spaces in the buffer
                      noOfElements, // measures the number of elements in the buffer
                      criticalSection;   // controls criticalSection to the buffer
    private T[] buffer;    // the buffer
    // Note: cannot create arrays of generics in Java, so we use an Object array instead
    // and cast to T when we create the (empty) buffer
    private int putIndex,       // the index of next empty space in the buffer
                getIndex;       // the index of the oldest element in the buffer
    private boolean isOpen;     // is this buffer open for business? 
    private int elements;       // the number of elements currently in the buffer
    private final static int LIMIT = 5; // set default limit for bounded semaphores to 5
    
    /**
     * The constructor sets the size of the buffer, and initialises the semaphores
     * @param size the size of the buffer
     */            
    @SuppressWarnings("unchecked")
	public Buffer(int size) {
        buffer = (T[]) new Object[size];
        noOfSpaces = new Semaphore("noOfSpaces",size,LIMIT); // all spaces in the buffer are initially empty
        noOfElements = new Semaphore("noOfElements",0,LIMIT);   // there are initially no elements in the buffer
        criticalSection = new Semaphore("criticalSection",1,LIMIT);     // allow at most one put or get action at a time
        putIndex = 0;                  // start filling the buffer from index 0
        getIndex = 0;                  // start retrieving data from index 0
        elements = 0;                  // initially no elements in the buffer
        isOpen = true;                 // the buffer is now open for business
    }
    
    /**
     * Close the buffer.
     */
    public synchronized void close() throws BufferError, SemaphoreLimitError {
        try {
            isOpen = false;
            noOfSpaces.vote();
            noOfElements.vote();
            criticalSection.vote();
        } catch (InterruptedException ie) {
            throw new BufferError("Buffer: Could not close the buffer.\n" +
                                  "\t" + ie.getMessage());
        }
    }
    
    /**
     * Check whether the buffer is open for business.
     * @return true iff the buffer is open for business
     */
    public boolean isOpen() {
        return isOpen;
    }
    
    /**
     * Provide criticalSection control when adding data to the buffer
     * @param data the data item to be added to the buffer
     */
    public void put(T item) throws BufferError, SemaphoreLimitError {
        try {
            criticalSection.poll();   // is the buffer available?
            noOfSpaces.poll();  // is there space in the buffer?
            putItem(item);    // add the data item
            criticalSection.vote();   // make the buffer available again
            noOfElements.vote(); // there is now one more element in the buffer
        } catch (InterruptedException ie) {
            throw new BufferError("Buffer: Data item " + item + " could not be added to the buffer.\n" +
                                  "\t" + ie.getMessage());
        }
    }
    
    /**
     * Provide criticalSection control when retrieving data from the buffer
     * @return the data item that has been in the buffer longest
     */
    public T get() throws BufferError, SemaphoreLimitError {
        T item;
        try {
            noOfElements.poll(); // is there at least one data item in the buffer?
            criticalSection.poll();   // is the buffer available?
            item = getItem(); // add the data item
            criticalSection.vote();   // make the buffer available again
            noOfSpaces.vote();  // there is now one more space in the buffer
        } catch (InterruptedException ie) {
            throw new BufferError("Buffer: Data item could not be retrieved from the buffer.\n" +
                                  "\t" + ie.getMessage());
        }
        return item;
    }
    
    /**
     * Add a data item to the buffer.
     * @param item the data item to be added to the buffer
     */
    private void putItem(T item) throws BufferError {
        if (!isOpen()) {
        	throw new BufferError("Buffer: Buffer has closed - cannot add " + item + " to it");
        }
        if (elements >= buffer.length) {
            throw new BufferError("Buffer: Cannot add " + item + " to buffer - buffer is full");
        }
        buffer[putIndex%buffer.length] = item; // put the data item in the buffer
        putIndex++;               // and increase the put index
        elements++;
        System.out.println("Buffer: " + item + " added, " + elements + " item" + (elements == 1 ? "" : "s") + " in the buffer");
    }
    
    /**
     * Retrieve a data item from the buffer.
     * @return datum the data item that has been in the buffer longest
     */
    private T getItem() throws BufferError {

        if (!isOpen()) {
        	throw new BufferError("Buffer: Buffer has closed - cannot get data item from it");
        }
        if (elements <= 0) {
            throw new BufferError("Buffer: Cannot get data item from buffer - buffer is empty");
        }
        T item = buffer[getIndex%buffer.length]; // get the oldest data item in the buffer
        getIndex++; // and increase the get index
        elements--;
        System.out.println("Buffer: " + item + " removed, " + elements + " item" + (elements == 1 ? "" : "s") + " in the buffer");
        if (getIndex > putIndex) { // check that we haven't overtaken the producer
            throw new BufferError("Buffer: The get index has overtaken the put index");
        }
        return item;
    }    
}
