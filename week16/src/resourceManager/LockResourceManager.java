package resourceManager;

import java.util.Random;

public class LockResourceManager implements ResourceManager {
    // The resource this resource manager is managing.
    private Resource resource;
    
    // The maximum priority with which the resource can be requested. Valid priorities are in the interval [0,MAX_PRIORITY]
    private static final int MAX_PRIORITY = 10;
    /**
     * The number of priority levels.
     */
    public static final int NO_OF_PRIORITIES = MAX_PRIORITY+1;
    // Used to keep track of the number of resource users waiting at each priority level.
    private int numberWaiting[] = new int[NO_OF_PRIORITIES];
    
    /**
     * This value should be returned by the releaseResource() method if no waiting resource user can be found.
     */
    public static final int NONE_WAITING = -1;
        
    // Used for generating random priority levels
    private Random random = new Random(System.currentTimeMillis());
    
    // The number of users using the resource.  This should never be more than one.
    private int numberOfUsers;
    
    // The number of times that the resource can still be used.  The resource becomes
    // unavailable when this reaches zero.
    private int usesLeft;
	
    protected int priority;
    
	public LockResourceManager(Resource resource, int maxUseages) {
		this.resource = resource;
        for (priority = 0; priority < NO_OF_PRIORITIES; priority++) {
            numberWaiting[priority] = 0;            
        }
        numberOfUsers = 0;
        usesLeft = random.nextInt(maxUseages)+1;
	}

	public synchronized void requestResource(int localPriority) {
		//System.out.println("priority = " + priority + " localpriority = " + localPriority);
		if (localPriority >= priority) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		--priority;
		notify();
	}

	public synchronized int releaseResource() throws ResourceError {
		if (priority >= 10) {
			
		}
		++priority;
		notify();
		return priority;
	}

	@Override
	public int getRandomPriority() {
		return random.nextInt(MAX_PRIORITY+1);
	}

	@Override
	public String getResourceName() {
        return resource.toString();
	}

	@Override
	public boolean resourceIsExhausted() {
		return usesLeft <= 0;
	}

	@Override
	public void useResource(int timeRequired) throws ResourceError {
        numberOfUsers++;
        if (numberOfUsers > 1) {
            throw new ResourceError(((ResourceUser) Thread.currentThread()) + " cannot use " + resource + " because it is already in use by another user");
        }
        if (resourceIsExhausted()) {
            System.out.println(((ResourceUser) Thread.currentThread()) + " cannot use " + resource + " as the resource is exhausted"); 
        } else {
        	resource.use(timeRequired);
        	usesLeft--;
        }
        System.out.println(resource + " has " + usesLeft + " uses left");
        numberOfUsers--;
	}
}
