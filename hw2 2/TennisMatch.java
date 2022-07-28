package hw2;

/**
 * 
 * This is a class that simulate a tennis game
 * 
 * @author Sylvia Nguyen
 *
 */

public class TennisMatch {
	private TennisPlayer player1;
	private TennisPlayer player2;
	private boolean bestOfThree;
	private boolean ballInPlay;
	private boolean gameOver;
	private boolean ballServed;
	private boolean serveFault;
	private int faultTracker;
	private TennisPlayer ballFrom, ballTo;
	private TennisPlayer server, receiver;

	/**
	 * Create a new tennis match
	 * 
	 * @param p1Name           the name of player 1
	 * @param p2Name           the name of player 2
	 * @param playBestOfThree  determine whether the game is best of three or best
	 *                         of five. True = bestOfThree. False = BestOfFive.
	 * @param initialServer    determine whether player 1 or player 2 will be the
	 *                         initial server.
	 * @param initialServerEnd determine whether the initial server start at end 1
	 *                         or end 2
	 */

	public TennisMatch(String p1Name, String p2Name, boolean playBestOfThree, int initialServer, int initialServerEnd) {

		bestOfThree = playBestOfThree;
		if (initialServer == 1 && initialServerEnd == 1) {
			player1 = new TennisPlayer(p1Name, initialServerEnd);
			player2 = new TennisPlayer(p2Name, initialServerEnd + 1);
			server = player1;
			receiver = player2;
			ballFrom = player1;
			ballTo = player2;
			player1.setEnd(initialServerEnd);
			player2.setEnd(initialServerEnd + 1);

		}

		else if (initialServer == 1 && initialServerEnd == 2) {
			player1 = new TennisPlayer(p1Name, initialServerEnd);
			player2 = new TennisPlayer(p2Name, initialServerEnd - 1);
			server = player1;
			receiver = player2;
			ballFrom = player1;
			ballTo = player2;
			player1.setEnd(initialServerEnd);
			player2.setEnd(initialServerEnd - 1);

		} else if (initialServer == 2 && initialServerEnd == 1) {
			player1 = new TennisPlayer(p1Name, initialServerEnd + 1);
			player2 = new TennisPlayer(p2Name, initialServerEnd);
			server = player2;
			receiver = player1;
			ballFrom = player2;
			ballTo = player1;
			player1.setEnd(initialServerEnd + 1);
			player2.setEnd(initialServerEnd);

		} else if (initialServer == 2 && initialServerEnd == 2) {
			player1 = new TennisPlayer(p1Name, initialServerEnd - 1);
			player2 = new TennisPlayer(p2Name, initialServerEnd);
			server = player2;
			receiver = player1;
			ballFrom = player2;
			ballTo = player1;
			player1.setEnd(initialServerEnd - 1);
			player2.setEnd(initialServerEnd);

		}
	}

	/**
	 * simulate the ball being served
	 */
	public void serve() {

		if (gameOver == false) {
			ballInPlay = true;
			ballServed = true;
			serveFault = false;

		}
	}

	/**
	 * Registered a serve fault
	 */
	public void fault() {
		faultTracker++;
		if (faultTracker == 2) {
			serveFault = true;
			incrementGamePoints(ballTo, ballFrom);

		}
		ballServed = false;
		ballInPlay = false;

	}

	/**
	 * Reverse the ball direction
	 */

	public void returnBall() {

		if (ballTo == player1 || ballFrom == player2) {
			ballTo = player2;
			ballFrom = player1;
		} else {
			ballTo = player1;
			ballFrom = player2;
		}

		ballServed = false;

	}

	/**
	 * Takes the ball out of play
	 */
	public void failedReturn() {
		incrementGamePoints(ballFrom, ballTo);
		ballFrom = server;
		ballTo = receiver;
		ballServed = false;

	}

	/**
	 * Ends the current point early without a point being scored
	 */

	public void let() {
		ballFrom = server;
		ballTo = receiver;
		ballServed = false;
		ballInPlay = false;
	}

	/**
	 * Set the game, match and set scores.
	 * 
	 * @param p1Game  the new Game score for player 1
	 * @param p2Game  the new Game score for player 2
	 * @param p1Set   the new Set score for player 1
	 * @param p2Set   the new Set score for player 2
	 * @param p1Match the new Match score for player 1
	 * @param p2Match the new Match score for player 2
	 */
	public void setScore(int p1Game, int p2Game, int p1Set, int p2Set, int p1Match, int p2Match) {
		player1.setGamePoints(p1Game);
		player2.setGamePoints(p2Game);
		player1.setSetPoints(p1Set);
		player2.setSetPoints(p2Set);
		player1.setMatchPoints(p1Match);
		player2.setMatchPoints(p2Match);

	}

	/**
	 * Set the Game score
	 * 
	 * @param p1Game the new Game score for player 1
	 * @param p2Game the new Game score for player 2
	 */
	public void setGameScore(int p1Game, int p2Game) {
		player1.setGamePoints(p1Game);
		player2.setGamePoints(p2Game);
	}

	/**
	 * Set the Set Score
	 * 
	 * @param p1Set the new Set score for player 1
	 * @param p2Set the new Set score for player 2
	 */
	public void setSetScore(int p1Set, int p2Set) {
		player1.setSetPoints(p1Set);
		player2.setSetPoints(p2Set);

	}

	/**
	 * set the Match Score
	 * 
	 * @param p1Match the new Match score for player 1
	 * @param p2Match the new Match score for player 2
	 */
	public void setMatchScore(int p1Match, int p2Match) {
		player1.setMatchPoints(p1Match);
		player2.setMatchPoints(p2Match);
	}

	/**
	 * Set the server
	 * 
	 * @param player the new server
	 */
	public void setServe(int player) {
		if (player == 1) {
			server = player1;
		} else {
			server = player2;
		}
	}

	/**
	 * Set the server's end
	 * 
	 * @param end the new end for the server
	 */
	public void setServerEnd(int end) {
		if (server == player1 && end == 1) {
			player1.setEnd(1);
			player2.setEnd(2);
		} else if (server == player1 && end == 2) {
			player1.setEnd(2);
			player2.setEnd(1);

		} else if (server == player2 && end == 1) {
			player1.setEnd(2);
			player2.setEnd(1);

		} else if (server == player2 && end == 2) {
			player1.setEnd(1);
			player2.setEnd(2);

		}

	}

	/**
	 * Swap the ends for the two players
	 */

	public void changeEnds() {
		if (player1.getEnd() == 1 || player2.getEnd() == 2) {
			player1.setEnd(2);
			player2.setEnd(1);

		} else {
			player1.setEnd(1);
			player2.setEnd(2);

		}

	}

	/**
	 * Swap the server and the receiver
	 */

	public void changeServer() {
		TennisPlayer tmp = server;
		server = receiver;
		receiver = tmp;
	}

	/**
	 * Adds one game point to addTo's game total. Zeros game score and increments
	 * set score if game has ended. Removes ball from play. Clears faults.
	 * 
	 * @param addTo the player who has scored a point
	 * @param noAdd the other player
	 */
	public void incrementGamePoints(TennisPlayer addTo, TennisPlayer noAdd) {
		addTo.incrementGamePoints();
		faultTracker = 0;
		ballInPlay = false;

		if (player1.getGamePoints() >= 4 && (player1.getGamePoints() - player2.getGamePoints()) % 2 == 0
				&& player2.getGamePoints() < 3) {
			incrementSetPoints(player1, player2);
			player1.setGamePoints(0);
			player2.setGamePoints(0);
		} else if (player2.getGamePoints() >= 4 && (player2.getGamePoints() - player1.getGamePoints()) % 2 == 0
				&& player1.getGamePoints() < 3) {
			incrementSetPoints(player2, player1);
			player1.setGamePoints(0);
			player2.setGamePoints(0);
		} else if (player1.getGamePoints() >= 3 && player2.getGamePoints() >= 3
				&& (player1.getGamePoints() - player2.getGamePoints()) % 2 == 0
				&& (player1.getGamePoints() - player2.getGamePoints()) != 0
				&& (player1.getGamePoints() - player2.getGamePoints()) > 0) {
			incrementSetPoints(player1, player2);
			player1.setGamePoints(0);
			player2.setGamePoints(0);
		} else if (player2.getGamePoints() >= 3 && player1.getGamePoints() >= 3
				&& (player2.getGamePoints() - player1.getGamePoints()) % 2 == 0
				&& (player2.getGamePoints() - player1.getGamePoints()) != 0
				&& (player2.getGamePoints() - player1.getGamePoints()) > 0) {
			incrementSetPoints(player2, player1);
			player2.setGamePoints(0);
			player1.setGamePoints(0);
		}

	}

	/**
	 * Adds one set point to addTo's total. Zeros set score and increments match
	 * score if set has ended. Changes server. Changes ends after odd numbered sets.
	 * 
	 * @param addTo the player who has scored a set point
	 * @param noAdd the other player
	 */
	public void incrementSetPoints(TennisPlayer addTo, TennisPlayer noAdd) {
		addTo.incrementSetPoints();
		if (player1.getSetPoints() >= 6 && (player1.getSetPoints() - player2.getSetPoints()) % 2 == 0
				&& (player1.getSetPoints() - player2.getSetPoints()) != 0 && player2.getSetPoints() <= 4) {
			incrementMatchPoints(player1, player2);
			player2.setSetPoints(0);
			player1.setSetPoints(0);

		}
		if (player2.getSetPoints() >= 6 && (player2.getSetPoints() - player1.getSetPoints()) % 2 == 0
				&& (player2.getSetPoints() - player1.getSetPoints()) != 0 && player1.getSetPoints() <= 4) {
			incrementMatchPoints(player2, player1);
			player2.setSetPoints(0);
			player1.setSetPoints(0);

		} else if (player1.getSetPoints() >= 5 && player2.getSetPoints() >= 5
				&& (player1.getSetPoints() - player2.getSetPoints()) % 2 == 0
				&& (player1.getSetPoints() - player2.getSetPoints()) != 0
				&& (player1.getSetPoints() - player2.getSetPoints()) > 0) {
			incrementMatchPoints(player1, player2);
			player2.setSetPoints(0);
			player1.setSetPoints(0);

		} else if (player1.getSetPoints() >= 5 && player2.getSetPoints() >= 5
				&& (player2.getSetPoints() - player1.getSetPoints()) % 2 == 0
				&& (player2.getSetPoints() - player1.getSetPoints()) != 0
				&& (player2.getSetPoints() - player1.getSetPoints()) > 0) {
			incrementMatchPoints(player2, player1);
			player2.setSetPoints(0);
			player1.setSetPoints(0);

		}
		// Change Server
		if (server == player1) {
			server = player2;
			ballFrom = player2;
			receiver = player1;
			ballTo = player1;
		} else {
			server = player1;
			ballFrom = player1;
			receiver = player2;
			ballTo = player2;
		}
		// Change ends after odd numbered sets
		if ((player1.getSetPoints() + player2.getSetPoints()) % 2 != 0) {
			if (player1.getEnd() == 1) {
				player1.setEnd(2);
				player2.setEnd(1);

			} else {
				player1.setEnd(1);
				player2.setEnd(2);

			}

		}
	}

	/**
	 * Adds one match point to addTo's total. Sets game over if match has ended.
	 * 
	 * @param addTo the player who has scored a match point
	 * @param noAdd the other player
	 */
	public void incrementMatchPoints(TennisPlayer addTo, TennisPlayer noAdd) {
		addTo.incrementMatchPoints();
		if (bestOfThree == true) {

			if (player1.getMatchPoints() == 2) {
				gameOver = true;

			}
			if (player2.getMatchPoints() == 2) {
				gameOver = true;

			}
		} else {
			if (player1.getMatchPoints() == 3) {
				gameOver = true;

			}
			if (player2.getMatchPoints() == 3) {
				gameOver = true;

			}
		}
	}

	/**
	 * return player1 end position
	 * 
	 * @return player1 end
	 */
	public int getP1End() {

		return player1.getEnd();
	}

	/**
	 * return player2 end position
	 * 
	 * @return player2 end
	 */
	public int getP2End() {

		return player2.getEnd();

	}

	/**
	 * return serve fault status
	 * 
	 * @return serve fault status
	 */
	public boolean getServeFault() {
		return serveFault;
	}

	/**
	 * return the server's name
	 * 
	 * @return server's name
	 */
	public String getServer() {
		if (server == player1) {
			return player1.getName();
		} else {
			return player2.getName();
		}
	}

	/**
	 * Returns the name of the player whom the ball is heading toward or "Ball not
	 * in play"
	 * 
	 * @return player's name that the ball is heading to
	 */
	public String getBallTo() {
		if (ballTo == player1 && ballInPlay == true) {
			return player1.getName();
		} else if (ballTo == player2 && ballInPlay == true) {
			return player2.getName();
		} else {
			return "Ball not in play.";
		}
	}

	/**
	 * return the name of the receiver
	 * 
	 * @return the name of the receiver
	 */
	public String getReceiver() {
		if (receiver == player1) {
			return player1.getName();
		} else {
			return player2.getName();
		}

	}

	/**
	 * Returns the name of the player who last successfully served or returned the
	 * ball or "Ball not in play"
	 * 
	 * @return ballFrom's name
	 */
	public String getBallFrom() {

		if (ballFrom == player1 && ballInPlay == true) {
			return player1.getName();
		} else if (ballFrom == player2 && ballInPlay == true) {
			return player2.getName();
		} else {
			return "Ball not in play.";
		}
	}

	/**
	 * Return the status of whether the ball is in play
	 * 
	 * @return the status of the ball in play
	 */
	public boolean getBallInPlay() {
		return ballInPlay;
	}

	/**
	 * return the status if the ball is being served
	 * 
	 * @return the status of the ball being served
	 */
	public boolean getBallServed() {
		return ballServed;
	}

	/**
	 * return the status of whether the game is best of three or best of five.
	 * 
	 * @return the status of the game being best of three or best of five.
	 */
	public boolean getBestOfThree() {
		return bestOfThree;
	}

	/**
	 * return the status of the game is over or not
	 * 
	 * @return the status of the game being over or not
	 */

	public boolean getGameOver() {
		return gameOver;
	}

	/**
	 * return the name of the player
	 * 
	 * @param player the player
	 * @return player's name
	 */
	public String getName(int player) {

		if (player == 1) {
			return player1.getName();
		} else {
			return player2.getName();
		}

	}

	/**
	 * return the name of the winner or an error message that the game is not over
	 * 
	 * @return the name of the winner
	 */
	public String getWinner() {

		if (bestOfThree == true && player1.getMatchPoints() >= 2) {
			return player1.getName();
		} else if (bestOfThree == true && player2.getMatchPoints() >= 2) {
			return player2.getName();
		} else if (bestOfThree == false && player1.getMatchPoints() >= 3) {
			return player1.getName();
		} else if (bestOfThree == false && player2.getMatchPoints() >= 3) {
			return player2.getName();
		} else {
			return "Game is not over.";
		}
	}

	/**
	 * return the match score p1-p2
	 * 
	 * @return the match score
	 */
	public String getMatchScore() {
		return "Match Score: " + player1.getMatchPoints() + "-" + player2.getMatchPoints();

	}

	/**
	 * return the set score p1-p2
	 * 
	 * @return the set score
	 */
	public String getSetScore() {
		return "Set score: " + player1.getSetPoints() + "-" + player2.getSetPoints();

	}

	/**
	 * return the game score p1-p2
	 * 
	 * @return the game score
	 */
	public String getGameScore() {
		// 1-15 2-30 3-40 0-love
		String player1PointsTerm = null;
		String player2PointsTerm = null;
		if (player1.getGamePoints() == 1) {
			player1PointsTerm = "15";
		}
		if (player1.getGamePoints() == 2) {
			player1PointsTerm = "30";
		}
		if (player1.getGamePoints() == 3) {
			player1PointsTerm = "40";
		}
		if (player1.getGamePoints() == 0) {
			player1PointsTerm = "Love";
		}
		if (player1.getGamePoints() == 4) {
			player1PointsTerm = "Game";
		}
		if (player2.getGamePoints() == 1) {
			player2PointsTerm = "15";
		}
		if (player2.getGamePoints() == 2) {
			player2PointsTerm = "30";
		}
		if (player2.getGamePoints() == 3) {
			player2PointsTerm = "40";
		}
		if (player2.getGamePoints() == 0) {
			player2PointsTerm = "Love";
		}
		if (player1.getGamePoints() == 4) {
			player2PointsTerm = "Game";
		}

		if ((player1.getGamePoints() == player2.getGamePoints())
				&& (player1.getGamePoints() >= 3 || player2.getGamePoints() >= 3)) {
			return "Game score: Deuce";
		}
		if (player1.getGamePoints() - player2.getGamePoints() == 1) {
			if (player1.getGamePoints() > 3 || player2.getGamePoints() > 3) {
				return "Game score: Advantage" + " " + player1.getName();
			}
		}
		if (player2.getGamePoints() - player1.getGamePoints() == 1) {
			if (player1.getGamePoints() > 3 || player2.getGamePoints() > 3) {
				return "Game score: Advantage" + " " + player2.getName();
			} else {
				return "Game score: " + player1PointsTerm + "-" + player2PointsTerm;
			}
		} else {
			return "Game score: " + player1PointsTerm + "-" + player2PointsTerm;
		}
	}

	/**
	 * returns the full game, set, and match score
	 * 
	 * @return the full game, set, and match score
	 */
	public String getScore() {
		return getGameScore() + " " + getSetScore() + " " + getMatchScore();

	}

}

