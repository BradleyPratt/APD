package railways;

import errors.RailwaySystemError;
import errors.SetUpError;
import tools.Clock;
import tools.Delay;

public class Bolivia extends Railway {
	/**
	 * @throws RailwaySystemError if there is an error in constructing the delay
	 * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 */
	public Bolivia() throws SetUpError{
		super("Bolivia",new Delay(0.1,0.3));
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
    	while (!clock.timeOut()) { // Whilst the clock is ticking
    		choochoo();
    		basket.putStone(this); // Place a stone in this railway systems basket
    		while (nextRailway.getBasket().hasStone(this)) { // While the next railway system has a stone in its basket
	    		if(!getSharedBasket().hasStone(this)) { // If the shared basket has a stone
		    		basket.takeStone(this);
		    		while(!getSharedBasket().hasStone(this)) { // // While the shared basket has the stone
		    			siesta();
		    		}
		    		basket.putStone(this);
	    		}
    		}
    		crossPass();
    		getSharedBasket().takeStone(this);
    		basket.takeStone(this);    	
    	}
    }
}