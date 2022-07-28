package hw3;

import java.awt.Point;
import java.lang.Math;
import maze.Status;
import java.util.ArrayList;

/**
 * Implementation of MazeCell that has a location in a 2D plane.
 * 
 * @author Sylvia Nguyen
 */
public class MazeCell {
	/**
	 * the maze to which this MazeCell belongs.
	 */
	private Maze owner;
	/**
	 * Status of this cell.
	 */
	private Status status;
	/**
	 * The exact row of where the mazeCell is located.
	 */
	private int rowOfMazeCell;
	/**
	 * The exact column of where the mazeCell is located.
	 */
	private int columnOfMazeCell;
	/**
	 * The time stamp of the MazeCell.
	 */
	private int timeStamp;
	/**
	 * The parents of the MazeCell.
	 */
	private MazeCell parents;
	/**
	 * The list of neighbors for a specific mazeCell .
	 */
	private ArrayList<MazeCell> neighbors = new ArrayList<>();

	/**
	 * Constructing a mazeCell with the wall being set as DEFAULT, parent is null,
	 * no neighbors and time stamp is 0.
	 * 
	 * @param The row of the mazeCell
	 * @param col The column of the mazeCell
	 */
	public MazeCell(int row, int col) {
		rowOfMazeCell = row;
		columnOfMazeCell = col;
		setStatus(Status.WALL);
		parents = null;
		timeStamp = 0;
	}

	/**
	 * Adds an observer for changes in this cell's status.
	 * 
	 * @param the maze to which this cell belong
	 */
	public void setOwner(Maze maze) {
		owner = maze;
	}

	/**
	 * Update the status of this cell and notifies the owner that this current
	 * cell's status has changed.
	 * 
	 * @param the updated status
	 */
	public void setStatus(Status s) {
		status = s;
		if (owner != null) {
			owner.updateStatus(this);
		}
	}

	/**
	 * return the status of the current the status.
	 * 
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Adds a neighbor to this cell.
	 * 
	 * @param m the maze cell.
	 */
	public void addNeighbor(MazeCell m) {

		neighbors.add(m);
	}

	/**
	 * Computes the Manhattan distance between this cell and other cell. The
	 * distance between two points measured along axes at right angles. In a plane
	 * with p1 at (x1, y1) and p2 at (x2, y2), it is |x1 - x2| + |y1 - y2|.
	 * 
	 * @param other the other cell
	 * @return the Manhattan distance
	 */
	public int distance(MazeCell other) {
		int x1 = (int) other.getLocation().getX();
		int y1 = (int) other.getLocation().getY();

		int differenceOfX = Math.abs(x1 - rowOfMazeCell);
		int differenceOfY = Math.abs(y1 - columnOfMazeCell);

		return differenceOfX + differenceOfY;
	}

	/**
	 * Returns this cell's location as a point, which contains its row and column.
	 * 
	 * @return location
	 */
	public Point getLocation() {
		Point p = new Point(rowOfMazeCell, columnOfMazeCell);
		return p;
	}

	/**
	 * Return the neighbors of the current cell. If a cell have no neighbors, return
	 * an empty arrayList.
	 * 
	 * @return the neighbors
	 */
	public ArrayList<MazeCell> getNeighbors() {
		return neighbors;
	}

	/**
	 * Gets the parent of this cell. Return null if the cell has no parents.
	 * 
	 * @return parent cell.
	 */
	public MazeCell getParent() {
		return parents;
	}

	/**
	 * Return the time stamp of this cell.
	 * 
	 * @return time stamp
	 */
	public int getTimeStamp() {

		return timeStamp;
	}

	/**
	 * Sets the parent of this cell with the given parent.
	 * 
	 * @param parent The parent cell
	 */
	public void setParent(MazeCell parent) {
		parents = parent;
	}

	/**
	 * Sets the time stamp of this cell.
	 * 
	 * @param time The time stamp
	 */
	public void setTimeStamp(int time) {
		if (time >= 0) {
			timeStamp = time;
		} else {
			timeStamp = 0;
		}
	}

	/**
	 * Returns a string representation of this cell's row and column number enclosed
	 * by a pair of parenthesis. Example: (3,4).
	 * 
	 * @overrides toString in class java.lang.Object
	 */
	public String toString() {
		return "(" + rowOfMazeCell + "," + columnOfMazeCell + ")";
	}

}
