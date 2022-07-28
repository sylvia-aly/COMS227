package ships;

import utils.Position;

/**
 * An abstract class of the InvaderShip.
 * 
 * @author Sylvia Nguyen
 *
 */
public abstract class InvaderShip extends SpaceShip {
	/**
	 * The amount of different types of the InvaderShip.
	 */
	public static final int NUM_INVADER_SHIPS = 4;
	/**
	 * The amount of spacing between the InvaderShips.
	 */
	public static final double SHIP_SPACING = 60;
	/**
	 * The amount of ships in a row.
	 */
	public static final int SHIPS_X = 6;
	/**
	 * The amount of ships in a column.
	 */
	public static final int SHIPS_Y = 6;
	/**
	 * The probability of firing.
	 */
	public static final double FIRING_PROBABILITY = .0012;

	/**
	 * The invaderShip object.
	 * 
	 * @param p     position
	 * @param armor amount of armor
	 */

	public InvaderShip(Position p, int armor) {
		super(p, armor);
	}

	/**
	 * Getting the points value for each ship.
	 */

	public abstract int getPoints();

}