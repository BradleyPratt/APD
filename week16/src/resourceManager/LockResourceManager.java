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
		
        for (int priority = 0; priority < NO_OF_PRIORITIES; priority++) {
        	conditions[priority] = lock.newCondition();
        }
	}

	public void requestResource(int priority) throws ResourceError {
		increaseNumberWaiting(priority);

		lock.lock();
				
		try {
			if (resourceInUse) {
				conditions[priority].await();
			}
		resourceInUse = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public int releaseResource() throws ResourceError {
		int highestPriority = 0;
		
		lock.lock();

		try {	
			for (int i = 0; i <= 10; i++) {
				if(getNumberWaiting(i) > 0) {
					highestPriority = i;
					System.out.println("HighestPriority = " + highestPriority + " Num of waiting = " + getNumberWaiting(i));
				}
			}
		
			if (highestPriority >= 0) {
				decreaseNumberWaiting(highestPriority);

				resourceInUse = false;
								
				conditions[highestPriority].signal();
								
				return highestPriority;
			}
			
			resourceInUse = false;
			conditions[highestPriority].signal();

			return NONE_WAITING;
		} finally {
			lock.unlock();
		}
	}
}
