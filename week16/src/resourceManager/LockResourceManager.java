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
	}

	public synchronized void requestResource(int priority) {
		lock.lock();
		
		int highestPriority = 0;
		
		try {
			if (resourceInUse) {
				increaseNumberWaiting(priority);
				for (int i = 0; i < NO_OF_PRIORITIES; i++ ) {
					if (getNumberWaiting(i) > 0) {
						highestPriority = i;
						conditions[priority] = lock.newCondition();
					}
				}
				System.out.println("HighestPriority = " + highestPriority);
				/*if(conditions[priority] != null) {
					conditions[priority].await();
				}
				conditions[priority] = lock.newCondition();*/
				conditions[priority].await();
				notUsing.signal();
			} 
			
			resourceInUse = true;
			//using.await();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public synchronized int releaseResource() {
		lock.lock();
		System.out.println("ran");
		try {
			notUsing.await();
			using.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			for (int i = 11; i < conditions.length; i--) {
				System.out.println(i);

				if(getNumberWaiting(i) != 0) {
					decreaseNumberWaiting(i);
					//conditions[i].signal();
					System.out.println(i);
					
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
