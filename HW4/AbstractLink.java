package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * AbstractLink is an abstract superclass for the track link types
 * The goal of my design was to minimize shared logic (like trainEnteredCrossing) to reduce
 * redundancy in my subclasses
 * The subclasses use their own versions of certain classes example shiftpoint 
 * depending on the classes behavior
 * Using this inherited class it helps avoid code duplication, makes structure that links 
 * the classes to share a similar interface.
 *  
 *  @author [redacted]
 */
public abstract class AbstractLink implements Crossable {
	
	@Override
	public void trainEnteredCrossing() {
		
	}
	
	@Override
	public void trainExitedCrossing() {
		
	}
	
	@Override
	public abstract Point getConnectedPoint(Point point);
	
	@Override
	public abstract void shiftPoints(PositionVector positionVector);
	
	@Override
	public abstract int getNumPaths();

	
}
