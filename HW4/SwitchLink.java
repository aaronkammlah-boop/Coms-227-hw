package hw4;

import api.Point;
import api.PositionVector;
import api.Path;
/**
 * Represents a swwitchable track segment one path can be active
 * at a time
 * 
 * @param pairA default active pair
 * @author [redacted]
 */
public class SwitchLink extends AbstractLink{

	private Point a;
	private Point b;
	private Point c;
	private Boolean turns;
	
	public SwitchLink(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.turns = false;
	}
	
	public void setTurn(boolean val) {
		this.turns = val;
	}
	/**
	 * Returns the other point in the active pair.
	 *
	 * @param point one point of the pair
	 * @return the paired point or null
	 */
	@Override
	public Point getConnectedPoint(Point p) {
		if(!turns) {
			if(p.equals(a)) return b;
			if(p.equals(b)) return a;
		}
		else {
			if(p.equals(a)) return c;
			if(p.equals(c)) return a;
		}
		return null;
	}
	/**
	 * Shifts the position forward based on the current active pair.
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
