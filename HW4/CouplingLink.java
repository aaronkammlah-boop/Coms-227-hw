package hw4;

import api.Path;
import api.Point;
import api.PositionVector;

/**
 * This class represents the link between two points
 * It helps support two way traversal between endpoints.
 * It extends AbstractLink to reuse traversal logic and interface structure. 
 * @param a first connection point
 * @param b the second connection point
 * 
 * @author [redacted]
 */
public class CouplingLink extends AbstractLink{

	private Point a;
	private Point b;
	
	public CouplingLink(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	/**
	 * Returns the point connected to a given point
	 * If the point isnt in the link returns null
	 * @param p point to check
	 * @return the connected point or null
	 * 
	 */
	@Override
	public Point getConnectedPoint(Point p) {
		if(p.equals(a)) return b;
		if(p.equals(b)) return a;
		return null;
	}
	/**
	 * Updates the position vector by moving it one step forward on the path
	 * assumes current location on the end of link
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
		return 2;
	}
	

}
