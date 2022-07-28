package ships;

import projectiles.DefenderProjectile;
import projectiles.Projectile;
import ui.SpaceInvaders;
import utils.Position;

/**
 * a class for the DefenderShip.
 * 
 * @author Sylvia Nguyen
 *
 */
public class DefenderShip extends SpaceShip {
	/**
	 * The image file of the Defender ship.
	 */
	public static final String IMG_FILE = "res/spaceship.png";
	/**
	 * The height of the ship.
	 */
	public static final double SHIP_HEIGHT = 60;
	/**
	 * The width of the ship.
	 */
	public static final double SHIP_WIDTH = 50;
	/**
	 * The starting y position of the ship.
	 */
	public static final double Y_START_POS = SpaceInvaders.HEIGHT - SHIP_HEIGHT;
	/**
	 * The starting x position of the ship.
	 */
	public static final double X_START_POS = SpaceInvaders.WIDTH / 2;
	/**
	 * The speed which the defender ship move.
	 */
	public static final double MOVE_DELTA = 5;

	/**
	 * create an object for defender ship.
	 */
	public DefenderShip() {
		super(new Position(X_START_POS, Y_START_POS), MAX_ARMOR);
	}

	/**
	 * create a weapon for the defender ship.
	 * 
	 * @return the weapon.
	 */
	public Projectile[] fire() {
		if (!canFire()) {
			return null;
		}

		lastShotTime = System.currentTimeMillis();
		Projectile[] out = new Projectile[1];
		Position p = new Position(pos.getX() + DefenderShip.SHIP_WIDTH / 2, pos.getY() - DefenderShip.SHIP_HEIGHT / 2);
		out[0] = new DefenderProjectile(p, PROJECTILE_SPEED);

		return out;
	}

	/**
	 * create an image of the defender ship.
	 * 
	 * @return the image of the Defender Ship
	 */

	@Override
	public String imgPath() {
		return IMG_FILE;
	}

}
