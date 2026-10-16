package hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import api.Move;

/**
 * A puzzle solver for the the game.
 * <p>
 * THE ONLY METHOD YOU ARE CHANGING IN THIS CLASS IS solve().
 */
public class Solver {
	/**
	 * Maximum number of moves allowed in the search.
	 */
	private int maxMoves;

	/**
	 * Associates a string representation of a grid with the move count required to
	 * reach that grid layout.
	 */
	private Map<String, Integer> seen = new HashMap<String, Integer>();

	/**
	 * All solutions found in this search.
	 */
	private ArrayList<ArrayList<Move>> solutions = new ArrayList<ArrayList<Move>>();

	/**
	 * Constructs a solver with the given maximum number of moves.
	 * 
	 * @param givenMaxMoves maximum number of moves
	 */
	public Solver(int givenMaxMoves) {
		maxMoves = givenMaxMoves;
		solutions = new ArrayList<ArrayList<Move>>();
	}

	/**
	 * Returns all solutions found in the search. Each solution is a list of moves.
	 * 
	 * @return list of all solutions
	 */
	public ArrayList<ArrayList<Move>> getSolutions() {
		return solutions;
	}

	/**
	 * Prints all solutions found in the search.
	 */
	public void printSolutions() {
		for (ArrayList<Move> moves : solutions) {
			System.out.println("Solution:");
			for (Move move : moves) {
				System.out.println(move);
			}
			System.out.println();
		}
	}

	/**
	 * EXTRA CREDIT 10 POINTS
	 * <p>
	 * Recursively search for solutions to the given board instance according to the
	 * algorithm described in the assignment pdf. This method does not return
	 * anything its purpose is to update the instance variable solutions with every
	 * solution found.
	 * 
	 * @param board any instance of Board
	 */
	public void solve(Board board) {
		
		ArrayList<ArrayList<Move>> stack = new ArrayList<>();// list used to keep track of all possible move paths
	    ArrayList<Move> moves = new ArrayList<>(); // start empty move sequence
	    stack.add(moves);

	    while (!stack.isEmpty()) {// runs until there are no paths left
	        ArrayList<Move> currentMoves = stack.remove(stack.size() - 1);// takes most recent sequence of moves from the stack

	        board.reset();
	        for (Move m : currentMoves) {// replays all moves in the current move sequence on the board
	            board.grabBoulderAt(m.getBoulder().getFirstRow(), m.getBoulder().getFirstCol());
	            board.moveGrabbedBoulder(m.getDirection());
	        }

	        if (board.isGameOver()) {//if the game solved store the solution
	            solutions.add(new ArrayList<>(currentMoves));
	            continue;
	        }

	        if (currentMoves.size() >= maxMoves) {// if move sequence to long skip it
	            continue;
	        }

	        String state = board.toString();
	        if (seen.containsKey(state) && seen.get(state) <= currentMoves.size()) {
	            continue; // if move count is already been reached in fewer moves or equal skip it
	        }

	        seen.put(state, currentMoves.size());

	        ArrayList<Move> possibleMoves = board.getAllPossibleMoves();
	        for (Move move : possibleMoves) {// for each possible next move create new sequence and add to stack
	            ArrayList<Move> newMoves = new ArrayList<>(currentMoves);
	            newMoves.add(move);
	            stack.add(newMoves);
	        }
	    }
	}
}
