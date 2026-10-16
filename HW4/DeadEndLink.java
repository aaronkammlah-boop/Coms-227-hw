package hw4;
import api.Crossable;
import api.Point;
import api.PositionVector;
/**
 * DeadEndLink Represents a "DeadEnd" (one way point)
 * It does not allow traversal beyond the current position and provides no forward path.
 * Returns null for all connections
 * 
 * 
 * @author [redacted]
 */
public class DeadEndLink extends AbstractLink {
	
	
	public DeadEndLink() {
		
	}
	/**
	 * always returns null connects to no points
	 * @param point the input point
	 * @return always null
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		
		return null;
	}

	@Override
	public void trainEnteredCrossing() {
		
		
	}

	@Override
	public void trainExitedCrossing() {
		
		
	}

	@Override
	public int getNumPaths() {
		
		return 1;
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
		
		
	}
	


}
