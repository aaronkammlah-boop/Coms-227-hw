package hw4;

import api.PointPair;
import api.PositionVector;
import api.Point;
import api.Path;
/**
 * MultiSwitchLink helps with switching between multiple point pairs
 * allows connections to change while running
 * @param p the intital pointpairs used to populate the connection pool
 * @author [redacted]
 */
public class MultiSwitchLink extends AbstractLink{
	private PointPair[] activePair;
	private PointPair[] allPair;
	
	
	public MultiSwitchLink(PointPair[] p) {
		this.allPair = p;
		this.activePair = new PointPair[allPair.length];
		System.arraycopy(allPair, 0, activePair, 0, allPair.length);
	}
	/**
	 * replaces a pair at a given index
	 * @param newPair the new pointpair to insert
	 * @param index the position to replace in the array
	 */
	public void switchConnection(PointPair newPair, int index) {
		if(index >= 0 && index <= activePair.length) {
			activePair[index] = newPair;
		}
	}
	/**
	 * returns the connected point to the input
	 * @param point- point to look up
	 * @return the connected point or null
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		for(PointPair p : activePair) {
			if(point.equals(p.getPointA())) return p.getPointB();
			if(point.equals(p.getPointB())) return p.getPointA();
		}
		return null;
	}
	/**
	 * updates the position vector using the current pair and moving it forward 1
	 * @param position vector current position
	 */
	@Override
	public void shiftPoints(PositionVector positionVector) {
		Point here =  positionVector.getPointB();
		Point there = getConnectedPoint(here);
		if(there == null) return;
		
		Path path = there.getPath();
		int index = there.getPointIndex();
		
		if(index + 1 < path.getNumPoints()) {
			positionVector.setPointA(path.getPointByIndex(index));
			positionVector.setPointB(path.getPointByIndex(index + 1));
		}
	}
	
	@Override
	public int getNumPaths() {
		return allPair.length * 2;
	}

	@Override
	public void trainEnteredCrossing() {
		
		
	}

	@Override
	public void trainExitedCrossing() {
		
	}

	
	
}
