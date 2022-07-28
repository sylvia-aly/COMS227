package ships;

import utils.Position;

/**
 * a class for the TsarBomba ship.
 * 
 * @author Sylvia Nguyen
 *
 */
public class TsarBombaShip extends BomberShip {
	/**
	 * The explosive radius of the bomb
	 */
	public static final double EXPLOSION_RADIUS = 50;

	/**
	 * Constructs a TsarBombaShip
	 * 
	 * @param p     The initial position
	 * @param armor The initial armor level
	 */
	public TsarBombaShip(Position p, int armor) {
		super(p, armor);
	}

	/**
	 * create an image for the TsarBomba ship.
	 * 
	 * @return image of the TsarBomba ship.
	 */
	@Override
	public String imgPath() {
		return "res/monster4.png";
	}
}
