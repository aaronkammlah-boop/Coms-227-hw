package hw3;
/**
 * This a class that was used to create a board
 * for a boulder game. The aim of the game is to manipulate
 * boulders to get your boulder to the exit marked by e
 * 
 * @author [redacted]
 * @version 1.0
 */
import static api.Direction.*;
import static api.Orientation.*;

import java.util.ArrayList;

import api.Cell;
import api.Direction;
import api.Move;
import api.Orientation;

/**
 * Represents a board in the game. A board contains a 2D grid of cells and a
 * list of boulders that slide over the cells.
 */
public class Board {
	
/**
 * amount of moves a player has made
 */
	private int moveCount;
	/**
	 * true or false statement to check if the game is over
	 */
	private boolean gameOver;
	/**
	 * used to symbolize the boulder being grabbed.
	 */
	private Boulder grab;
	/**
	 * which row the boulder was grabbed.
	 */
	private int grabbedRow;
	/**
	 * which collumn the boulder was grabbed in
	 */
	private int grabbedCol;
	
	/**
	 * 2D array of cells, the indexes signify (row, column) with (0, 0) representing
	 * the upper-left corner of the board.
	 */
	private Cell[][] grid;

	/**
	 * A list of boulders that are positioned on the board.
	 */
	private ArrayList<Boulder> boulders;

	/**
	 * A list of moves that have been made in order to get to the current position
	 * of boulders on the board.
	 */
	private ArrayList<Move> moveHistory;

	/**
	 * Constructs a new board from a given 2D array of cells and list of boulders. The
	 * cells of the grid should be updated to indicate which cells have boulders
	 * placed over them (i.e., placeBoulder() method of Cell). The move history should
	 * be initialized as empty.
	 * 
	 * @param grid   a 2D array of cells which is expected to be a rectangular shape
	 * @param boulders list of boulders already containing row-column position which
	 *               should be placed on the board
	 */
	public Board(Cell[][] grid, ArrayList<Boulder> boulders) {
		
		this.boulders = boulders;
		this.grid = grid;
		this.moveHistory = new ArrayList<>();
		this.moveCount = 0;
		this.gameOver = false;
		
		for(Boulder b : boulders) {
			for(int i = 0; i < b.getLength(); i++) {
				int r = b.getFirstRow() + (b.getOrientation() == VERTICAL ? i : 0);
				int c = b.getFirstCol() + (b.getOrientation() == HORIZONTAL ? i : 0);
				grid[r][c].placeBoulder(b);
			}
		}
	}

	/**
	 * DO NOT MODIFY THIS CONSTRUCTOR
	 * <p>
	 * Constructs a new board from a given 2D array of String descriptions.
	 * 
	 * @param desc 2D array of descriptions
	 */
	public Board(String[][] desc) {
		this(GridUtil.createGrid(desc), GridUtil.findBoulders(desc));
	}

	/**
	 * Returns the number of rows of the board.
	 * 
	 * @return number of rows
	 */
	public int getRowSize() {
		
		return grid.length;
	}

	/**
	 * Returns the number of columns of the board.
	 * 
	 * @return number of columns
	 */
	public int getColSize() {
		
		return grid[0].length;
	}

	/**
	 * Returns the cell located at a given row and column.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @return the cell at the specified location
	 */
	public Cell getCellAt(int row, int col) {
		
		return grid[row][col];
	}

	/**
	 * Returns the total number of moves (calls to moveGrabbedBoulder which
	 * resulted in a boulder being moved) made so far in the game.
	 * 
	 * @return the number of moves
	 */
	public int getMoveCount() {
		
		return moveCount;
	}

	/**
	 * Returns a list of all boulders on the board.
	 * 
	 * @return a list of all boulders
	 */
	public ArrayList<Boulder> getBoulders() {
		
		return boulders;
	}

	/**
	 * Returns true if the player has completed the puzzle by positioning a boulder
	 * over an exit, false otherwise.
	 * 
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
	    return gameOver;
	}


	
	/**
	 * Models the user grabbing (mouse button down) a boulder over the given row and
	 * column. The purpose of grabbing a boulder is for the user to be able to drag
	 * the boulder to a new position, which is performed by calling
	 * moveGrabbedBoulder().
	 * <p>
	 * This method should find which boulder has been grabbed (if any) and record
	 * that boulder as grabbed in some way.
	 * 
	 * @param row row to grab the boulder from
	 * @param col column to grab the boulder from
	 */
	public void grabBoulderAt(int row, int col) {
	/**
	 * Attempts to grab the boulder at the specified cell.if the current cell contains a boulder
	 * it saves the current spot col, and row the boulder is at
	 */
		Boulder b = grid[row][col].getBoulder();
		if(b != null) {
			grab = b;
			grabbedRow = row;
			grabbedCol = col;
		}
	}

	/**
	 * Models the user releasing (mouse button up) the currently grabbed boulder
	 * (if any). Update the object accordingly to indicate no boulder is
	 * currently being grabbed.
	 */
	public void releaseBoulder() {
		
		grab = null;
	}

	/**
	 * Returns the currently grabbed boulder. If there is no currently grabbed
	 * boulder the method return null.
	 * 
	 * @return the currently grabbed boulder or null if none
	 */
	public Boulder getGrabbedBoulder() {
		
		return grab;
	}

	/**
	 * Returns true if the cell at the given row and column is available for a
	 * boulder to be placed over it. Boulders can only be placed over ground
	 * and exits. Additionally, a boulder cannot be placed over a cell that is
	 * already occupied by another boulder.
	 * 
	 * @param row row location of the cell
	 * @param col column location of the cell
	 * @return true if the cell is available for a boulder, otherwise false
	 */
	public boolean isAvailable(int row, int col) {
		
		if(row < 0 || row >= getRowSize() || col < 0 || col >= getColSize()) return false;
		
		if(!grid[row][col].isGround() && !grid[row][col].isExit()) return false;
		
		if(grid[row][col].getBoulder() != null) return false;
		
		return true;
	}

	/**
	 * Moves the currently grabbed boulder by one cell in the given direction. A
	 * horizontal boulder is only allowed to move right and left and a vertical boulder
	 * is only allowed to move up and down. A boulder can only move over a cell that
	 * is a floor or exit and is not already occupied by another boulder. The method
	 * does nothing under any of the following conditions:
	 * <ul>
	 * <li>The game is over.</li>
	 * <li>No boulder is currently grabbed by the user.</li>
	 * <li>A boulder is currently grabbed by the user, but the boulder is not allowed to
	 * move in the given direction.</li>
	 * </ul>
	 * If none of the above conditions are meet, the method does at least the following:
	 * <ul>
	 * <li>Moves the boulder object by calling its move() method.</li>
	 * <li>Calls placeBoulder() for the grid cell that the boulder is being moved into.</li>
	 * <li>Calls removeBoulder() for the grid cell that the boulder is being moved out of.</li>
	 * <li>Adds the move (as a Move object) to the end of the move history list.</li>
	 * <li>Increments the count of total moves made in the game.</li>
	 * </ul>
	 * 
	 * @param dir the direction to move
	 */
	
	public void moveGrabbedBoulder(Direction dir) {
	    if (gameOver || grab == null) return;

	    if ((grab.getOrientation() == HORIZONTAL && (dir == UP || dir == DOWN)) ||
	        (grab.getOrientation() == VERTICAL   && (dir == LEFT || dir == RIGHT))) {
	        return; //Prevents there from being an invalid movement of the boulder. ex if horizontal it cant move up or down.
	    }

	    int dRow = (dir == UP)    ? -1 : (dir == DOWN)  ? 1 : 0;// Determines the change in row based on direction
	    int dCol = (dir == LEFT)  ? -1 : (dir == RIGHT) ? 1 : 0;// Determines the change in column based on direction.
	    
	    for (int i = 0; i < grab.getLength(); i++) {
	        int r = grab.getFirstRow() + (grab.getOrientation() == VERTICAL   ? i : 0) + dRow; // used to calculate the row for each segment of boulder after movement 
	        int c = grab.getFirstCol() + (grab.getOrientation() == HORIZONTAL ? i : 0) + dCol;// used to calculate the change in column for each segment of boulder after movement.

	        if (r < 0 || r >= getRowSize() || c < 0 || c >= getColSize()) return; // make sure the next position is in bounds

	        Cell cell = grid[r][c];
	        if (!cell.isGround() && !cell.isExit()) return; //makes sure the next cell isnt ground or an exit

	        if (cell.hasBoulder() && cell.getBoulder() != grab) { //makes sure not to move into a cell that has a boulder that isn't grabbed.
	            return;
	        }
	    }
	    
	    for (int i = 0; i < grab.getLength(); i++) {
	        int r = grab.getFirstRow() + (grab.getOrientation() == VERTICAL   ? i : 0); // removes the row for the boulder cell before moving
	        int c = grab.getFirstCol() + (grab.getOrientation() == HORIZONTAL ? i : 0);// removes column for the boulder cell before moving
	        grid[r][c].removeBoulder(); // statement clears the boulder from old position
	    }

	    grab.move(dir); //moves the boulder

	    for (int i = 0; i < grab.getLength(); i++) {
	        int r = grab.getFirstRow() + (grab.getOrientation() == VERTICAL   ? i : 0);// place row cell segment of boulder in new location
	        int c = grab.getFirstCol() + (grab.getOrientation() == HORIZONTAL ? i : 0);// place column cell segment of the boulder in new location
	        grid[r][c].placeBoulder(grab); // used to place the boulder in its new location on the grid
	    }
	    //records the move to the history and adds to move count
	    moveHistory.add(new Move(grab, dir));
	    moveCount++;

	    gameOver = false;
	    for (int i = 0; i < grab.getLength(); i++) {
	        Cell cell = grid[
	            grab.getFirstRow() + (grab.getOrientation()==VERTICAL? i:0) //both are used to make sure if the boulder is over an exit
	        ][
	            grab.getFirstCol() + (grab.getOrientation()==HORIZONTAL? i:0)
	        ];
	        if (cell.isExit()) {
	            gameOver = true;
	            break; //stops checking for exit as its true
	        }
	    }
	}


	
	/**
	 * Resets the state of the game back to the start, which includes the move
	 * count, the move history, and whether the game is over. The method calls the
	 * reset method of each boulder object. It also updates each grid cells by calling
	 * their placeBoulder method to either set a boulder if one is located over the cell
	 * or set null if no boulder is located over the cell.
	 */
	public void reset() {
		//removes all boulders from the grid
		for(int r = 0; r < getRowSize(); r++) {
			for(int c = 0; c < getColSize(); c++) {
				grid[r][c].removeBoulder();
			}
		}
		
		for(Boulder b : boulders) {
			b.reset(); //reset each boulder to original position.
			for(int i = 0; i < b.getLength(); i++) {// place each segment of the boulder back on the board
				int r = grab.getFirstRow() + (grab.getOrientation() == VERTICAL ? i : 0);// calculates next row for the segment of the boulder
				int c = grab.getFirstCol() + (grab.getOrientation() == HORIZONTAL ? i : 0);// calculates next column for the next segment of the boulder
				grid[r][c].placeBoulder(b);
			}
		}
		
		moveHistory.clear();
		moveCount = 0;
		gameOver = false;
		releaseBoulder();
	}

	/**
	 * Returns a list of all legal moves that can be made by any boulder on the
	 * current board.
	 * 
	 * @return a list of legal moves
	 */
	public ArrayList<Move> getAllPossibleMoves() {
		
		ArrayList<Move> moves = new ArrayList<>();// stores move list
		for(Boulder b : boulders) { // loops through every boulder 
			int r = b.getFirstRow(); // set r to boulder first row
			int c = b.getFirstCol();// set c to boulder first column
			grabBoulderAt(r , c);
			
			if(b.getOrientation() == HORIZONTAL) {// checks if moving left is a legal move
				int leftCol = b.getFirstCol() - 1;// moves to left col
				if(isAvailable(b.getFirstRow(), leftCol)) {
					moves.add(new Move(grab, LEFT));
				}
				
				int rightCol = b.getFirstCol() + b.getLength();// move right
				if(isAvailable(b.getFirstRow(), rightCol)) { // check if right is a legal move
					moves.add(new Move(grab, RIGHT));
				}	
			}
			else {
				int upRow = b.getFirstRow() - 1;// move up
				if(isAvailable(upRow, b.getFirstCol())) {// check if up is a legal move
					moves.add(new Move(grab, UP));
				}
				
				int downRow = b.getFirstRow() + b.getLength();// move down
				if(isAvailable(downRow, b.getFirstCol())) {//check if down is a legal move
					moves.add(new Move(grab, DOWN));
				}
			}
		}
		
		releaseBoulder();
		return moves;
	}

	/**
	 * Gets the list of all moves performed to get to the current position on the
	 * board.
	 * 
	 * @return a list of moves performed to get to the current position
	 */
	public ArrayList<Move> getMoveHistory() {
		
		return moveHistory;
	}

	/**
	 * EXTRA CREDIT 5 POINTS
	 * <p>
	 * This method is only used by the Solver.
	 * <p>
	 * Undo the previous move. The method gets the last move on the moveHistory list
	 * and performs the opposite actions of that move, which are the following:
	 * <ul>
	 * <li>if required, sets is game over to false</li>
	 * <li>grabs the moved boulder and calls moveGrabbedBoulder passing the opposite
	 * direction</li>
	 * <li>decreases the total move count by two to undo the effect of calling
	 * moveGrabbedBoulder twice</li>
	 * <li>removes the move from the moveHistory list</li>
	 * </ul>
	 * If the moveHistory list is empty this method does nothing.
	 */
	public void undoMove() {
	    if (moveHistory.isEmpty()) {// if no moves to undo
	        return;
	    }

	    gameOver = false; 

	    Move last = moveHistory.remove(moveHistory.size() - 1); //gets last move from history

	    Boulder b = last.getBoulder(); // gets the boulder that was moved in the last move
	    grabBoulderAt(b.getFirstRow(), b.getFirstCol()); // grabs the boulder at its current position

	    Direction rev = null;
	    switch (last.getDirection()) {// determines the reverse direction of the move
	        case UP: rev = DOWN; break; //if up rev = down
	        case DOWN: rev = UP; break; // if down rev = up
	        case LEFT: rev = RIGHT; break;// if left rev = right
	        case RIGHT: rev = LEFT; break;// if right rev = left
	    }

	    moveGrabbedBoulder(rev); // move boulder in reverse direction

	    moveHistory.remove(moveHistory.size() - 1); // removes the reverse move from the history

	    moveCount -= 2; // adjust move count

	    releaseBoulder();
	}


	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		boolean first = true;
		for (Cell row[] : grid) {
			if (!first) {
				buff.append("\n");
			} else {
				first = false;
			}
			for (Cell cell : row) {
				buff.append(cell.toString());
				buff.append(" ");
			}
		}
		return buff.toString();
	}
}
