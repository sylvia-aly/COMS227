package ships;

import projectiles.Bomb;
import projectiles.Projectile;
import utils.Position;

/**
 * a class for the BomberShip.
 * 
 * @author Sylvia Nguyen
 *
 */

public class BomberShip extends InvaderShip {
	/**
	 * The radius of the bomb when exploded.
	 */
	public static final double EXPLOSION_RADIUS = 10;

	/**
	 * Constructs a BomberShip
	 * 
	 * @param p     The initial position
	 * @param armor The initial armor level
	 */

	public BomberShip(Position p, int armor) {
		super(p, armor);

	}

	/**
	 * Drops a single bomb
	 * 
	 * @return An array containing a single bomb
	 * 
	 */
	@Override
	public Projectile[] fire() {

		if (!canFire()) {
			return null;
		}
		lastShotTime = System.currentTimeMillis();
		Bomb[] out = new Bomb[1];
		out[0] = new Bomb(pos, 0, -PROJECTILE_SPEED, Projectile.GRAVITY, EXPLOSION_RADIUS);
		return out;

	}

	/**
	 * create an image of the bomber ship.
	 * 
	 * @return the image of the ship
	 */

	@Override
	public String imgPath() {
		return "res/monster2.png";
	}

	/**
	 * the score value of bomber ship.
	 * 
	 * @return the value of the ship's points
	 */

	@Override
	public int getPoints() {
		return 100;
	}
}
