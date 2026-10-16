package hw4;

import api.Path;
import api.PositionVector;
import api.Point;
/**
 * TurnLink simulates a curved section of track that only allows movement in a single direction.
 * Often used to simulate rotation-limited track transitions.
 *
 * @param a the start point
 * @param b the end point
 *
 * @author [redacted]
 */
public class TurnLink extends AbstractLink{
	
	private Point a;
	private Point b;
	private Point c;
	
	public TurnLink(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	 * Returns the destination if the current point is the start.
	 * Otherwise returns null.
	 *
	 * @param point the input point
	 * @return the destination point or null
	 */
	@Override
	public Point getConnectedPoint(Point p) {
		if(p.equals(a)) return b;
		if(p.equals(b)) return a;
		return null;
	}
	/**
	 *  Allows movement only in one direction from point a to point b.
	 *
	 * @param positionVector the current position
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
		return 3;
	}

	@Override
	public void trainEnteredCrossing() {
		
		
	}

	@Override
	public void trainExitedCrossing() {
		
		
	}

	
	
}
