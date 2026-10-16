package hw4;

import api.Point;
import api.PositionVector;
import api.Path;
/**
 * Represents a straight track with a midpoint
 * Shows the traversal between the start to the end with 
 * a midpoint reference 
 * @param a first endpoint
 * @param b second endpoint
 * @param c midpoint
 * @author [redacted]
 */
public class StraightLink extends AbstractLink{

	private Point a;
	private Point b;
	private Point c;
	
	public StraightLink(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c= c;
	}
	/**
	 * Returns the point connected to the given point
	 * @param p the point to check
	 * @return the connected endpoint or null
	 */
	@Override
	public Point getConnectedPoint(Point p) {
		if(p.equals(a)) return b;
		if(p.equals(b)) return a;
		return null;
	}
	/**
	 * updates position vector by shifting one index forward
	 * @param positionVector the current position
	 */
	@Override
	public void shiftPoints(PositionVector positionVector) {
		Point here =  positionVector.getPointB();
		Point there = getConnectedPoint(here);
		if(there== null) return;
		
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