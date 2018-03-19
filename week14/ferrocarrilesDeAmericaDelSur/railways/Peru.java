package railways;

import errors.RailwaySystemError;
import errors.SetUpError;
import tools.Clock;
import tools.Delay;

public class Peru extends Railway {
	private static boolean[] procReqCS = {false,false} ;

	/**
	 * @throws RailwaySystemError if there is an error in constructing the delay
	 * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 */
	public Peru() throws SetUpError{
		super("Peru",new Delay(0.1,0.3));
	}

    /**
     * Run the train on the railway.
     * This method provides (incorrect) synchronisation attempting to avoid more than one train in the 
     * pass at any one time.
     */
    public void runTrain() throws RailwaySystemError {
    	Clock clock = getRailwaySystem().getClock();
    	Basket basket = getBasket();
    	Railway nextRailway = getRailwaySystem().getNextRailway(this);
    	
    	int id = 1;

    	while (!clock.timeOut()) { // Whilst the clock is ticking
			choochoo();
    		basket.putStone(this); // Place a stone in this railway systems basket
    		procReqCS[id] = basket.hasStone(this);

    		while (procReqCS[(id+1) % 2]) { // While the next railway system has a stone in its basket
	    		if(basket.hasStone(this) == procReqCS[id]) { // If this has stone or doesnt or the other railway has stone or doesnt
		    		basket.takeStone(this);
		    		while(nextRailway.getBasket().hasStone(this) != basket.hasStone(this)) { // While the next railways basket is not equal to this railways basket
		    			siesta();
		    		}
		    		basket.putStone(this);
	    		}
    		}
    		crossPass();
    		basket.takeStone(this);
    	}
    }
}