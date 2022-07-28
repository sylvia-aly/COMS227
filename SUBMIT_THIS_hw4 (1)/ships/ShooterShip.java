package ships;

import projectiles.Projectile;
import utils.Position;

/**
 * a class for the ShooterShip.
 * 
 * @author Sylvia Nguyen
 *
 */
public class ShooterShip extends InvaderShip {
	/**
	 * Constructs a ShooterShip
	 * 
	 * @param p     The initial position
	 * @param armor The initial armor level
	 */
	public ShooterShip(Position p, int armor) {
		super(p, armor);
	}

	/**
	 * Construct a weapon for the ShooterShip.
	 * 
	 * @return can fire will return an array of the weapon, can't fire will return
	 *         null.
	 */
	@Override

	public Projectile[] fire() {
		if (!canFire()) {
			return null;
		}
		lastShotTime = System.currentTimeMillis();
		Projectile[] out = new Projectile[1];
		out[0] = new Projectile(pos, 0, -PROJECTILE_SPEED, Projectile.GRAVITY);
		return out;
	}

	/**
	 * create an image of the ShooterShip.
	 * 
	 * @return the image of the ShooterShip
	 */

	@Override
	public String imgPath() {
		return "res/monster.png";

	}

	/**
	 * the amount of value that the ship is worth.
	 * 
	 * @return score.
	 */

	@Override
	public int getPoints() {
		return 50;
	}
}
