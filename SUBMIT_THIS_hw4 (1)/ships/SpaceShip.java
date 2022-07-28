package ships;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import projectiles.Projectile;
import utils.BoundingRing;
import utils.Position;

/**
 * An abstract class for a SpaceShip.
 * 
 * @author Sylvia Nguyen
 *
 */

public abstract class SpaceShip {
	/**
	 * Hold the specific position of the ships.
	 */
	protected Position pos;
	/**
	 * Hold the armor level of the specific spaceship.
	 */
	protected int armorLevel;
	/**
	 * Hold the bounding ring size of the spaceship.
	 */
	protected BoundingRing size;
	/**
	 * Hold the specific time that the spaceship have shot.
	 */
	protected long lastShotTime;
	/**
	 * The size of the bounding ring.
	 */
	public static final double HALO_SIZE = 10;
	/**
	 * The max armor that the spaceship can have.
	 */
	public static int MAX_ARMOR = 3;
	/**
	 * The speed of the projectiles.
	 */
	public static final double PROJECTILE_SPEED = -5;
	/**
	 * A place holder of the image.
	 */
	protected BufferedImage img;
	/**
	 * A boolean place holder for whether the ship should blink or not.
	 */
	protected boolean blink;

	/**
	 * Create an object of the spaceship.
	 * 
	 * @param p     the position
	 * @param armor the amount of armor that the spaceship have.
	 */

	public SpaceShip(Position p, int armor) {
		pos = p;
		armorLevel = armor;
		size = new BoundingRing(p, HALO_SIZE);
		lastShotTime = 0;

		try {
			if (this.img == null) {
				this.img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imgPath()));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}

	public abstract String imgPath();

	/**
	 * Abstract method that relates to projectile firing from the spaceship.
	 * 
	 * @return An array of one or more projectiles.
	 */
	public abstract Projectile[] fire();

	/**
	 * create a specific time for the spaceship can fire.
	 * 
	 * @return true if and only if the current time - lastShotTime is more than 100
	 */

	public boolean canFire() {
		if (System.currentTimeMillis() - lastShotTime > 100) {
			return true;
		}
		return false;
	}

	/**
	 * get armor level of the spaceship.
	 * 
	 * @return armor level
	 */

	public int getArmor() {
		return armorLevel;
	}

	/**
	 * get position of the spaceship.
	 * 
	 * @return
	 */

	public Position getPosition() {
		return pos;
	}

	/**
	 * Moves the ship a distance of deltaX horizontally and deltaY vertically.
	 */
	public void translate(double deltaX, double deltaY) {
		pos.set(pos.getX() + deltaX, pos.getY() + deltaY);
	}

	/**
	 * Places the ship at (newX, newY).
	 */
	public void setPosition(double newX, double newY) {
		pos.set(newX, newY);
	}

	/**
	 * Reduces the armor level of the ship and returns true if its bounding ring
	 * intersects r, otherwise returns false
	 * 
	 * @return true if and only if there is a collision.
	 */
	public boolean collide(BoundingRing r) {
		if (size.collide(r)) {
			takeHit();
			return true;
		}
		return false;
	}

	/**
	 * get the size of the bounding ring.
	 * 
	 * @return the size of the bounding ring.
	 */

	public BoundingRing getBounds() {
		return size;
	}

	/**
	 * spaceship lose a armor level and blinks if hit.
	 */

	public void takeHit() {
		armorLevel--;
		blink = true;
	}

	/**
	 * indicate whether the spaceship should blink or not.
	 * 
	 * @return true if and only if the spaceship is hit.
	 */

	public boolean isHit() {
		return blink;
	}

	/**
	 * set the ship to be blinked.
	 * 
	 * @param blink
	 */

	public void setHit(boolean blink) {
		this.blink = blink;
	}

	/**
	 * handle the graphics of the game.
	 * 
	 * @param g2d
	 */
	public void draw(Graphics2D g2d) {
		BufferedImage img = getImg();
		g2d.drawImage(img, (int) this.pos.getX(), (int) this.pos.getY(), img.getWidth(), img.getHeight(), null);

		g2d.setColor(Color.WHITE);
		if (this.getArmor() > 1)
			g2d.drawRect((int) this.pos.getX(), (int) this.pos.getY(), 2, 2);
		if (this.getArmor() > 2)
			g2d.drawRect((int) this.pos.getX() + 5, (int) this.pos.getY(), 2, 2);

	}

	/**
	 * get the image of the spaceship.
	 * 
	 * @return the image
	 */

	public BufferedImage getImg() {
		return this.img;
	}
}
