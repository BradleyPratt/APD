package resourceManager;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockResourceManager extends BasicResourceManager {
    final Lock lock = new ReentrantLock();
    final Condition[] conditions = new Condition[9];
        
    boolean resourceInUse = false;
    
	public LockResourceManager(Resource resource, int maxUseages) {
		super(resource, maxUseages);
		
	}

	public synchronized void requestResource(int priority) {
		lock.lock();
		
		try {
			if (resourceInUse) {
				increaseNumberWaiting(priority);
				conditions[priority] = lock.newCondition();
				conditions[priority].await();
			} 
			
			resourceInUse = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public synchronized int releaseResource() throws ResourceError {
		lock.lock();
		
		try {
			for (int i = 0; i <  conditions.length; i++) {
				if(getNumberWaiting(i) != 0) {
					decreaseNumberWaiting(i);
					conditions[i].signal();
					
					resourceInUse = false;
									
					return i;
				}
			}
			resourceInUse = false;

			return NONE_WAITING;
		} finally {
			lock.unlock();
		}
	}
}
