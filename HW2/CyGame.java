package hw2;
/**
 * A class representing a Monopoly game that requires two players
 * to roll a dice the game should not end until
 * One player goes bankrupt or when somebody gets more 
 * money then the MONEY_TO_WIN
 
 * @author [redacted]
 * @version 1.0
 */
public class CyGame {
	
		/**
		* The endzone square type.
		*/
		public static final int ENDZONE = 0;
		/**
		* The CyTown square type.
		*/
		public static final int CYTOWN = 1;
		/**
		* The pay rent square type.
		*/
		public static final int PAY_RENT = 2;
		/**
		* The fall behind square type.
		*/
		public static final int FALL_BEHIND = 3;
		/**
		* The blizzard square type.
		*/
		public static final int BLIZZARD = 4;
		/**
		* The pass class square type.
		*/
		public static final int PASS_CLASS = 5;
		/**
		* Points awarded when landing on or passing over the endzone square.
		*/
		public static final int ENDZONE_PRIZE = 200;
		/**
		* The standard rent payed to the other player when landing on a
		* pay rent square.
		*/
		public static final int STANDARD_RENT_PAYMENT = 80;
		/**
		* The cost to by CyTown.
		*/
		public static final int CYTOWN_COST = 200;
		/**
		* The amount of money required to win.
		*/
		public static final int MONEY_TO_WIN = 400;
		
		 /**
		  * stores value of number of squares on the board
		  */
		private int numSquares;
		/**
		 * Stores value of the starting money
		 */
		private int startingMoney;
		/**
		 * Stores a value to determine the current player
		 */
		private int currentPlayer;
		/**
		 * Used to store player ones location
		 */
		private int playerOnePos;
		/**
		 * Stores player twos location
		 */
		private int playerTwoPos;
		/**
		 * stores player ones money
		 */
		private int p1Money;
		/**
		 * stores player twos money
		 */
		private int p2Money;
		/**
		 * stores true or false if CYTOWN is owned by a player
		 */
		private boolean cyTownOwned;
		/**
		 * Stores a value for which player has ownership of CYTOWN
		 */
		private int cyTownOwner;
		/**
		 * The constructor initializes a game with the current parameters 
		 * Some factors were initialized to zero like the position 
		 * while currentplayer was set to one to start the game with P1
		 * p1 and p2 money were set to starting money to start the game with the starting amount
		 * cytownowned set to false because nobody owns it at the start of the game 
		 * and cytownowner was set to -1 as nobody could own it (its not at a given number as 1 or 2 represent who owns it)
		 * @param numSquares Amount of squares on a given board
		 * @param startingMoney Amount of money each player starts with
		 */
		public CyGame(int numSquares, int startingMoney) {
			this.numSquares = numSquares;
			this.currentPlayer = 1;
			this.startingMoney = startingMoney;
			this.playerOnePos = 0; 
			this.playerTwoPos = 0;
			this.p1Money = startingMoney;
			this.p2Money = startingMoney;
			this.cyTownOwned = false;
			this.cyTownOwner = -1;
			
		}
		/**
		 * Returns the current player (whos turn it is)
		 * @return an int value representing which player is currently playing
		 */
		public int getCurrentPlayer() {
			return currentPlayer; 
		}
		/**
		 * Returns the other player who is not playing (at the moment)
		 * @return an int value of the other player who is not playing
		 */
		public int getOtherPlayer() {
			if(currentPlayer == 1 ) {
				return 2;
			} else return 1;
		}
		/**
		 * gets the current square either player is on
		 * @param player is the current player playing the game
		 * @return an int value representing the position of player one or two (depending on who is up)
		 */
		public int getPlayerSquare​(int player) {
			if(player == 1) {
				return playerOnePos;
			} else {
				return playerTwoPos;
			}
		}
		/**
		 * gets the current amount of money for each player
		 * @param player current player playing the game
		 * @return an int value representing the amount of money player one or player two has
		 */
		public int getPlayerMoney(int player) {
			if(player == 1) {
				return p1Money;
			} else {
				return p2Money;
			}
		}
		/**
		 * determines if player one owns CYTOWN
		 * @return true or false depending on if P1 owns Cytown
		 */
		public boolean isPlayer1CyTownOwner() {
			return cyTownOwned && cyTownOwner == 1;
		}
		/**
		 * determines if player two owns CYTOWN
		 * @return true or false depending on if P2 owns Cytown
		 * 
		 */
		public boolean isPlayer2CyTownOwner() {
			return cyTownOwned && cyTownOwner ==2;
		}
		/**
		 * gets which square type the current player is on
		 * @param square number representing each square 
		 * @return an int of which square was landed on
		 *uses equations below to determine if the square landed on was a 
		 *special square
		 */
		public int getSquareType(int square) {
			if(square == 0) {
				return ENDZONE;
			}else if(square == numSquares -1){
				return CYTOWN;
			} else if(square % 5 == 0) {
				return PAY_RENT;
			}else if(square % 7 == 0 || square % 11 == 0) {
				return FALL_BEHIND;
			}else if(square % 3 == 0) {
				return BLIZZARD;
			} else {
				return PASS_CLASS;
			}
		}
		/**
		 * Was used to simulate a 6 sided dice being rolled 
		 * had many exceptions, if the game is over it quits
		 * if player rolled on a blizzard return the new position (equation is used to determine new pos)
		 * used the equations below to determine the new position of each player
		 * added a statement to determine if a player earns the endzone prize (if player passes or lands on the final square)
		 * added a statement to determine if a user had to pay rent to another player
		 * and if rent is increased
		 * finally made statements to determine what happens to the user when they land on
		 * FAll behind or Pass Class (Fall behind pushes the user back one and makes them do whatever square they landed on)
		 * same with pass class but it addes four
		 * if land on cytown they could buy it
		 * @param value is the amount the dice is rolled on (the given face 1-6 )
		 */
		public void roll​(int value) {
			if(isGameEnded())  return;
			int player =currentPlayer;
			int playerPos = getPlayerSquare​(player);
			if(getSquareType(playerPos) == BLIZZARD && value % 2 == 0) {
				endTurn();
				return;
			}
			int newPos = (playerPos + value) % numSquares;
			if(player ==1) {
				playerOnePos = newPos;
				
			}else {
				playerTwoPos = newPos;
				
			}
			if(playerPos + value >= numSquares) {
				if(player == 1) {
				p1Money += ENDZONE_PRIZE;
				}
			else { 
				p2Money += ENDZONE_PRIZE;
			}	
			}
			int square = getSquareType(newPos);
			if(square == PAY_RENT) {
				int rent = STANDARD_RENT_PAYMENT;
				if(cyTownOwned && cyTownOwner == getOtherPlayer()) {
					rent = rent * 2;
				}
				if(player ==1) {
					p1Money -=rent;
					p2Money += rent;
				}
				else {
					p1Money +=rent;
					p2Money -= rent;
				}
			}
				else if(square == FALL_BEHIND) {
					if(player == 1) {
						playerOnePos = (playerOnePos -1 + numSquares) % numSquares;
					}else {
						playerTwoPos = (playerTwoPos -1 + numSquares) % numSquares;
					}
					roll​(0);
					return;
				}
				else if(square == PASS_CLASS){
					playerPos = newPos;
					newPos = (playerPos + 4) % numSquares;
					if(player ==1) {
						playerOnePos = newPos;
					} else {
						playerTwoPos = newPos;
					}
					if(playerPos + 4 >= numSquares) {
						if(player ==1) {
							p1Money += ENDZONE_PRIZE;
						}
						else {
							p2Money += ENDZONE_PRIZE;
						}
					}
				}
				else if(square == CYTOWN) {
					return;
				}
				endTurn();
			}
			
		/**
		 * First checked if cytown is owned, if the player had enough money, and if they are on the cytown square
		 * if true code doesn't run
		 * if it is false it turns cytown owned to true and makes the owner the current player
		 * then takes the amount cytown cost out of the current players money
		 */
		public void buyCyTown() {
			if(cyTownOwned|| getPlayerMoney(currentPlayer) < CYTOWN_COST ||getSquareType (getPlayerSquare​(currentPlayer)) != CYTOWN ) {
				return;
			}
			cyTownOwned = true;
			cyTownOwner = currentPlayer;
				if(currentPlayer == 1 ) {
					p1Money -= CYTOWN_COST;
					
				} else {
					p2Money -= CYTOWN_COST;
				}
				endTurn();
			}
		/**
		 * ends the current users turn and changes the current player
		 */
		
		public void endTurn() {
			if(currentPlayer == 1) {
			currentPlayer = 2;
			}
			else {
				currentPlayer = 1;
			}
			}
		/**
		 * determines if the game is over by using the given parameters
		 * @return returns a true or false if one of the conditions has been met
		 */
		public boolean isGameEnded() {
			return p1Money >= MONEY_TO_WIN || p2Money >= MONEY_TO_WIN ||p1Money < 0 || p2Money < 0;
				
			
		}
		/**
		* Returns a one-line string representation of the current game state. The
		* format is:
		* <p>
		* <tt>Player 1*: (0, false, $0) Player 2: (0, false, $0)</tt>
		* <p>
		* The asterisks next to the player's name indicates which players turn it
		* is. The values (0, false, $0) indicate which square the player is on,
		* if the player is the owner of CyTown, and how much money the player has
		* respectively.
		*
		* @return one-line string representation of the game state
		*/
		public String toString() {
		String fmt = "Player 1%s: (%d, %b, $%d) Player 2%s: (%d, %b, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
		player1Turn = "*";
		} else {
		player2Turn = "*";
		}
		return String.format(fmt,
		player1Turn, getPlayerSquare​(1), isPlayer1CyTownOwner(),
		getPlayerMoney(1),
		player2Turn, getPlayerSquare​(2), isPlayer2CyTownOwner(),
		getPlayerMoney(2));
		}
}

