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
	//static int highestPriority;

	public LockResourceManager(Resource resource, int maxUseages) {
		super(resource, maxUseages);
		
        for (int priority = 0; priority < NO_OF_PRIORITIES; priority++) {
        	conditions[priority] = lock.newCondition();
        }
	}

	public void requestResource(int priority) throws ResourceError {
		increaseNumberWaiting(priority);

		lock.lock();
		
		//thisPriority = priority;
		
		try {
			if (resourceInUse) {
				/*if(conditions[priority] != null) {
					conditions[priority].await();
				}
				conditions[priority] = lock.newCondition();*/
				//notUsing.signal();
				//notUsing.signal();
				//conditions[priority] = lock.newCondition();
				//conditions[priority] = lock.newCondition();
				conditions[priority].await();
				}
						
			/*for (int i = 0; i < NO_OF_PRIORITIES; i++ ) {
				if (getNumberWaiting(i) > 0) {
					highestPriority = i;
				}
			}*/
			
			/*if (priority < highestPriority) {
				conditions[priority].await();
			}*/ 
			resourceInUse = true;
			//using.await();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public int releaseResource() throws ResourceError {
		/*try {
			notUsing.await();
			//using.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int highestPriority = 0;
		int returnValue = 0;
		
		lock.lock();

		try {
			//decreaseNumberWaiting(thisPriority);
			/*for (int i = 0; i < getNumberWaiting(thisPriority); i++) {
				//System.out.println("i = " + i);

				if(getNumberWaiting(thisPriority) != 0) {
					decreaseNumberWaiting(thisPriority);
					//conditions[i].signal();
					//System.out.println(i);
					conditions[thisPriority].signal();
					
					resourceInUse = false;
					
					return getNumberWaiting(thisPriority);
				}
			}*/
			
			for (int i = 0; i <= 10; i++) {
				//System.out.println(i);
				if(getNumberWaiting(i) > 0) {
					highestPriority = i;
					System.out.println("HighestPriority = " + highestPriority + " Num of waiting = " + getNumberWaiting(i));
				}
				
				/*if (i == 10) {
					//conditions[highestPriority].signal();
					conditions[highestPriority].signal();
					return highestPriority;
				}*/
			}
		
			if (highestPriority >= 0) {
				resourceInUse = false;
				
				decreaseNumberWaiting(highestPriority);
				
				conditions[highestPriority].signal();
								
				return highestPriority;
			}
			
			//decreaseNumberWaiting(thisPriority);
			resourceInUse = false;
			conditions[highestPriority].signal();

			return NONE_WAITING;
		} finally {
			lock.unlock();
		}
	}
}
