package ships;

import projectiles.Projectile;
import utils.Position;

/**
 * A class for MultiShooterShip.
 * 
 * @author Sylvia Nguyen
 *
 */
public class MultiShooterShip extends ShooterShip {
	/**
	 * the amount of cannons from the ship.
	 */
	public static final int NUM_CANNONS = 5;
	/**
	 * the distance between each bullet.
	 */
	public static final double SPREAD = 0.25;

	/**
	 * Constructs a MultiShooterShip
	 * 
	 * @param p     The initial position
	 * @param armor The initial armor level
	 */
	public MultiShooterShip(Position p, int armor) {
		super(p, armor);
	}

	/**
	 * Fires NUM_CANNONS projectiles, that spread out as they fall
	 * 
	 * @return An array of projectiles.
	 */
	@Override
	public Projectile[] fire() {

		if (!canFire()) {
			return null;
		}
		lastShotTime = System.currentTimeMillis();
		Projectile[] out = new Projectile[NUM_CANNONS];
		for (int i = 0; i < NUM_CANNONS; i++) {
			out[i] = new Projectile(pos, (i - (NUM_CANNONS / 2)) * SPREAD, -PROJECTILE_SPEED, Projectile.GRAVITY);
		}

		/*
		 * Hint, to get a spread, second parameter to Projectile() should be something
		 * like (i - (NUM_CANNONS / 2)) * SPREAD
		 */
		return out;
	}

	/**
	 * create an image for the MultiShooterShip.
	 * 
	 * @return an image of the MultiShooterShip.
	 */

	@Override
	public String imgPath() {
		return "res/monster3.png";
	}
}
