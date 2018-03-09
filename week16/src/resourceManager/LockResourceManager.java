package resourceManager;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockResourceManager extends BasicResourceManager {	
    final Lock lock = new ReentrantLock();
    final Condition[] conditions = new Condition[NO_OF_PRIORITIES];
    final Condition using  = lock.newCondition(); 
    final Condition notUsing = lock.newCondition();

    boolean resourceInUse = false;
    
	public LockResourceManager(Resource resource, int maxUseages) {
		super(resource, maxUseages);
		
		// Define the locks for each possible priority
        for (int priority = 0; priority < NO_OF_PRIORITIES; priority++) {
        	conditions[priority] = lock.newCondition();
        }
	}

	// Request resource method ran by the resource user class
	public void requestResource(int priority) throws ResourceError {
		// Locks the method to one process
		lock.lock();
				
		try {
			if (resourceInUse) {	
				// Increases the number of processes waiting for this priority
				increaseNumberWaiting(priority);
				conditions[priority].await();
			}
		// Sets the resource in use boolean to true until set to false once the resource has been used
		resourceInUse = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public int releaseResource() throws ResourceError {
		int highestPriority = -1;
		
		lock.lock();

		try {	
			// For loop which goes through each priority to find the highest priority waiting
			for (int i = 0; i < NO_OF_PRIORITIES; i++) {
				if(getNumberWaiting(i) > 0) {
					highestPriority = i;
				}
			}
			
			resourceInUse = false;

			if (highestPriority == -1) {
				return NONE_WAITING;
			} else {
				decreaseNumberWaiting(highestPriority); // Decreases the number of processes waiting with the highest priority
				conditions[highestPriority].signal();	// Signals the process with the highest priority waiting to run	
				return highestPriority; 
			}
		} finally {
			lock.unlock();
		}
	}
}