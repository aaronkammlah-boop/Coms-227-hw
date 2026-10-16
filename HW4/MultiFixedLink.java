package hw4;

import api.Path;
import api.Point;
import api.PositionVector;
import api.PointPair;
/**
 * MultiFixedLink represents a set of points that go in two directions 
 *  All of the connections are symmetric and final.
 *  @param p the array of PointParis to be fixed
 *  @author [redacted]
 */
public class MultiFixedLink extends AbstractLink{

	private PointPair[] pairs;
	
	public MultiFixedLink(PointPair[] p) {
		this.pairs = p;
	}
	/**
	 * Returns the matching point of the given point if it exists.
	 * @param point- the point to connect
	 * @return the matching point or null
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		for(PointPair p : pairs) {
			if(point.equals(p.getPointA())) return p.getPointB();
			if(point.equals(p.getPointB())) return p.getPointA();
		}
		return null;
	}
	/**
	 * Moves the position vector along the path by one
	 * @param positionVector current position
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
		return pairs.length * 2;
	}

	@Override
	public void trainEnteredCrossing() {
		
		
	}

	@Override
	public void trainExitedCrossing() {
		
		
	}

	

}
