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
	static int highestPriority;

	public LockResourceManager(Resource resource, int maxUseages) {
		super(resource, maxUseages);
		
        for (int priority = 0; priority < NO_OF_PRIORITIES; priority++) {
        	conditions[priority] = lock.newCondition();
        }
	}

	public synchronized void requestResource(int priority) {
		lock.lock();
						
		try {
			increaseNumberWaiting(priority);

			if (resourceInUse) {
				System.out.println("HighestPriority = " + highestPriority);
				/*if(conditions[priority] != null) {
					conditions[priority].await();
				}
				conditions[priority] = lock.newCondition();*/
				//notUsing.signal();
			}
						
			for (int i = 0; i < NO_OF_PRIORITIES; i++ ) {
				if (getNumberWaiting(i) > 0) {
					highestPriority = i;
				}
			}
			
			if (priority < highestPriority) {
				conditions[priority].await();
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
		/*try {
			notUsing.await();
			using.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		lock.lock();
		System.out.println("ran");

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
