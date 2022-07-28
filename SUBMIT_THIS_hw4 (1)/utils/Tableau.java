package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import projectiles.Bomb;
import projectiles.DefenderProjectile;
import projectiles.Projectile;
import ships.BomberShip;
import ships.DefenderShip;
import ships.InvaderShip;
import ships.MultiShooterShip;
import ships.ShooterShip;
import ships.SpaceShip;
import ships.TsarBombaShip;
import ui.SpaceInvaders;

/**
 * Control how the games move.
 * 
 * @author Sylvia Nguyen
 *
 */
public class Tableau {
	/**
	 * The speed at which the invaders' fleet moves horizontally.
	 */
	public static final double FLEET_MOVE_X = 4;
	/**
	 * The speed at which the invaders' fleet moves vertically.
	 */
	public static final double FLEET_MOVE_Y = 10;
	/**
	 * Creating a 2D arrays of the enemy ships.
	 */
	private InvaderShip[][] enemyFleet;
	/**
	 * Creating a defender ship.
	 */
	private DefenderShip defender;
	/**
	 * Create an ArrayList of projectiles.
	 */
	private ArrayList<Projectile> projectiles;
	/**
	 * Creating a tracker to be able to randomize the creation of enemyFleet.
	 */
	private Random r;
	/**
	 * to keep track of whether the enemyFleet move in the positive x direction or
	 * negative x direction.
	 */
	private boolean fleetMovePositiveX;
	/**
	 * to keep track of the score in the game.
	 */
	private int score;

	/**
	 * creates the enemy ships for the game. Also, set the initial position to move
	 * in the positive x direction, set score to equal to 0, and create a new
	 * defendership.
	 */
	public Tableau() {
		fleetMovePositiveX = true;
		r = new Random();
		enemyFleet = new InvaderShip[InvaderShip.SHIPS_Y][InvaderShip.SHIPS_X];
		for (int y = 0; y < InvaderShip.SHIPS_Y; y++) {
			for (int x = 0; x < InvaderShip.SHIPS_X; x++) {
				Position pos = new Position(((SpaceInvaders.WIDTH / 4) + (x * InvaderShip.SHIP_SPACING)),
						((y * InvaderShip.SHIP_SPACING)));
				switch (r.nextInt(InvaderShip.NUM_INVADER_SHIPS)) {
				case 0:
					enemyFleet[y][x] = new ShooterShip(pos, r.nextInt(SpaceShip.MAX_ARMOR) + 1);
					break;
				case 1:
					enemyFleet[y][x] = new BomberShip(pos, r.nextInt(SpaceShip.MAX_ARMOR) + 1);
					break;
				case 2:
					enemyFleet[y][x] = new MultiShooterShip(pos, r.nextInt(SpaceShip.MAX_ARMOR) + 1);
					break;
				case 3:
					enemyFleet[y][x] = new TsarBombaShip(pos, r.nextInt(SpaceShip.MAX_ARMOR) + 1);
					break;
				}
			}
		}
		projectiles = new ArrayList<>();
		defender = new DefenderShip();
		score = 0;
	}

	/**
	 * Checks all enemy ships to see if they are hit by d. If ship is hit, calls
	 * takeHit(). If ships armor falls to zero or below, score is updated and ship
	 * is destroyed.
	 * 
	 * @param d A projectile
	 * @return true if and only if a ship is hit
	 */
	private boolean checkForEnemyHit(DefenderProjectile d) {
		for (int i = 0; i < enemyFleet[0].length; i++) {
			for (int j = 0; j < enemyFleet.length; j++) {
				if (enemyFleet[i][j] != null) {
					if (d.hit(enemyFleet[i][j].getBounds()) == true) {
						enemyFleet[i][j].takeHit();

						if (enemyFleet[i][j].getArmor() <= 0) {
							score += enemyFleet[i][j].getPoints();
							enemyFleet[i][j] = null;

						}
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * a method that handles collisions in the game.
	 */

	public void handleCollisions() {
		Projectile p;

		for (int i = projectiles.size() - 1; i >= 0; i--) {
			p = projectiles.get(i);

			if (p.isOutOfBounds()) {
				if (p instanceof Bomb) {
					if (defender != null && ((Bomb) p).blowUp(defender.getBounds())) {
						defender.takeHit();
					}
				}
				projectiles.remove(i);
				i--;
			} else if (p instanceof DefenderProjectile) {
				if (checkForEnemyHit((DefenderProjectile) p)) {
					projectiles.remove(i);
					i--;
				}
			} else if (defender != null && p.hit(defender.getBounds())) {
				defender.takeHit();
				projectiles.remove(i);
				i--;
				if (defender.getArmor() <= 0) {
					defender = null;
					return;
				}
			}

			p.nextPosition();
		}
	}

	/**
	 * Returns a reference to the right-most ship in the invaders' fleet, or null if
	 * there are no ships.
	 * 
	 * @return a reference to the right-most ship
	 */
	private SpaceShip getRightMostEnemy() {
		SpaceShip rightMostEnemy = null;

		for (int i = 0; i < enemyFleet.length; i++) {
			for (int j = 0; j < enemyFleet[0].length; j++) {
				if (enemyFleet[j][i] != null) {
					rightMostEnemy = enemyFleet[j][i];
				}

			}
		}

		return rightMostEnemy;
	}

	/**
	 * Returns a reference to the left-most ship in the invaders' fleet, or null if
	 * there are no ships.
	 * 
	 * @return a reference to the left-most ship
	 */
	private SpaceShip getLeftMostEnemy() {
		SpaceShip leftMostEnemy = null;

		for (int i = enemyFleet.length - 1; i >= 0; i--) {
			for (int j = enemyFleet[0].length - 1; j >= 0; j--) {
				if (enemyFleet[j][i] != null) {
					leftMostEnemy = enemyFleet[j][i];
				}

			}
		}

		return leftMostEnemy;
	}

	/**
	 * Returns a reference to the bottom-most ship in the invaders' fleet, or null
	 * if there are no ships.
	 * 
	 * @return a reference to the bottom-most ship
	 */
	private SpaceShip getLowestEnemy() {
		SpaceShip lowestEnemy = null;

		for (int i = 0; i < enemyFleet.length; i++) {
			for (int j = 0; j < enemyFleet[0].length; j++) {
				if (enemyFleet[i][j] != null) {
					lowestEnemy = enemyFleet[i][j];
				}

			}
		}

		return lowestEnemy;
	}

	/**
	 * Move the enemyFleet left to right.
	 * 
	 * @param deltaX the amount it takes for the enemyFleet to move in the x
	 *               direction.
	 * @param deltaY the amount it takes for the enemyFleet to move in the y
	 *               direction.
	 */
	private void translateEnemyFleet(double deltaX, double deltaY) {
		for (int y = 0; y < InvaderShip.SHIPS_Y; y++) {
			for (int x = 0; x < InvaderShip.SHIPS_X; x++) {
				if (enemyFleet[y][x] != null) {
					enemyFleet[y][x].translate(deltaX, deltaY);

					if (defender.getBounds().collide(enemyFleet[y][x].getBounds())) {
						defender = null;
						return;
					}
					/* Overloading to handle firing here, too */
					if (r.nextDouble() < InvaderShip.FIRING_PROBABILITY) {
						Projectile[] p = enemyFleet[y][x].fire();
						if (p != null)
							projectiles.addAll(Arrays.asList(p));
					}
				}
			}
		}
	}

	/**
	 * Does nothing if gameIsOver(), otherwise calculates the deltas to the invaders
	 * next position and calls translateEnemyFleet() on those deltas.
	 * fleetMovePositiveX gives the direction of fleet movement in the horizontal;
	 * if true, the fleet moves in the positive X direction, else it moves in the
	 * negative X direction. The entire fleet moves until it would go out of bounds
	 * (0 to SpaceInvaders.WIDTH), at which time, rather than go out of bounds, its
	 * direction changes and it moves down. Magnitude of the deltas are given by 0,
	 * FLEET_MOVE_X, and FLEET_MOVE_Y.
	 */
	public void moveEnemyFleet() {
		if (gameIsOver() == true) {
			return;
		}

		if (fleetMovePositiveX == true) {
			if (getRightMostEnemy() != null) {
				if (getRightMostEnemy().getPosition().getX() < SpaceInvaders.WIDTH) {

					translateEnemyFleet(FLEET_MOVE_X, 0);
				} else {
					translateEnemyFleet(0, FLEET_MOVE_Y);
					fleetMovePositiveX = false;
				}
			}
		}

		if (fleetMovePositiveX == false) {
			if (getLeftMostEnemy() != null) {
				if (getLeftMostEnemy().getPosition().getX() >= 0) {
					translateEnemyFleet(-FLEET_MOVE_X, 0);
				} else {
					translateEnemyFleet(0, FLEET_MOVE_Y);
					fleetMovePositiveX = true;
				}
			}
		}
	}

	/**
	 * the defender ship is being shifted left or right.
	 * 
	 * @param xAmount the amount that the defender ship moves in the x direction.
	 * @param yAmount the amount that the defender ship moves in the y direction.
	 */

	public void moveDefender(double xAmount, double yAmount) {
		double newX, newY;
		Position pos;

		pos = defender.getPosition();
		newX = pos.getX() + xAmount;
		if (newX > SpaceInvaders.WIDTH - DefenderShip.SHIP_WIDTH / 2) {
			newX = SpaceInvaders.WIDTH - DefenderShip.SHIP_WIDTH / 2;
		}
		if (newX < 0) {
			newX = 0;
		}

		newY = pos.getY() + yAmount;
		if (newY > SpaceInvaders.HEIGHT - DefenderShip.SHIP_HEIGHT / 2) {
			newY = SpaceInvaders.HEIGHT - DefenderShip.SHIP_HEIGHT / 2;
		}
		if (newY < 0) {
			newY = 0;
		}

		defender.setPosition(newX, newY);
	}

	/**
	 * the defender ship being destroyed.
	 * 
	 * @return defender being null is true or not
	 */

	public boolean defenderDestroyed() {
		return defender == null;
	}

	/**
	 * the enemy fleet ship being destroyed.
	 * 
	 * @return the lowest enemy being null is true or not
	 */

	public boolean enemyFleetDestroyed() {
		return getLowestEnemy() == null;
	}

	/**
	 * Indicate whether the game is over or not.
	 * 
	 * @return true if game is over, false if the game is not over
	 */

	public boolean gameIsOver() {
		return defenderDestroyed() || enemyFleetDestroyed()
				|| getLowestEnemy().getPosition().getY() > SpaceInvaders.HEIGHT;
	}

	/**
	 * let the players control the defender ship.
	 * 
	 * @param left  move left.
	 * @param right move right.
	 * @param up    move up.
	 * @param down  move down.
	 * @param fire  defender ship shooting.
	 */

	public void moveDefender(boolean left, boolean right, boolean up, boolean down, boolean fire) {
		if (gameIsOver())
			return;

		if (fire) {
			Projectile[] projectiles = defender.fire();
			if (projectiles != null)
				this.projectiles.addAll(Arrays.asList(projectiles));
		}

		if (right) {
			moveDefender(DefenderShip.MOVE_DELTA, 0);
		} else if (left) {
			moveDefender(-1 * DefenderShip.MOVE_DELTA, 0);
		} else if (up) {
			moveDefender(0, -1 * DefenderShip.MOVE_DELTA);
		} else if (down) {
			moveDefender(0, DefenderShip.MOVE_DELTA);
		}

	}

	/**
	 * Get the enemy fleet.
	 * 
	 * @return the 2D arrays of enemy fleet.
	 */

	public InvaderShip[][] getEnemyFleet() {
		return enemyFleet;
	}

	/**
	 * get the projectiles.
	 * 
	 * @return the projectiles.
	 */

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * get the defender ship.
	 * 
	 * @return the defender ship.
	 */

	public DefenderShip getDefenderShip() {
		return defender;
	}

	/**
	 * get the score.
	 * 
	 * @return score.
	 */

	public int getScore() {
		return score;
	}
}
