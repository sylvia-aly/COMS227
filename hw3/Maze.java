package hw3;

import maze.MazeObserver;
import maze.Status;

/**
 * A Maze consists of MazeCells arranged in a 2D grid.
 * 
 * @author Sylvia Nguyen
 */
public class Maze {

	/**
	 * Observer to be notified in case of changes to cells in this maze.
	 */
	private MazeObserver observer;
	/**
	 * The start cell from the maze.
	 */
	private MazeCell start;
	/**
	 * The goal cell from the maze.
	 */
	private MazeCell goal;
	/**
	 * The 2D array of the maze.
	 */
	private MazeCell[][] cells;
	/**
	 * The amount of rows in the maze.
	 */
	private int amountOfRows;
	/**
	 * The amount of columns in the maze.
	 */
	private int amountOfColumns;

	/**
	 * Constructs a maze based on a 2D grid. The given strings, rows, represent rows
	 * of a maze, where '#' represents a wall, a blank represents a possible path,
	 * 'S' represents the starting cell, and '$' represents the goal cell. The maze
	 * must be rectangular, which means the Strings in the given rows must have the
	 * same length. Also, there must be only one start cell and one goal cell in the
	 * maze. For each MazeCell in the maze, set its owner to be the current maze,
	 * its status as GOAL if it is the goal cell, UNVISITED if it is not the goal
	 * nor the wall. For each MazeCell that is accessible (not a wall), its
	 * accessible neighbors MUST be added in the order of top, left, bottom, right.
	 * Cells on the boundary of the maze or near a wall will have fewer accessible
	 * neighbors.
	 * 
	 * @param rows the maze
	 */
	public Maze(String[] rows) {
		amountOfRows = rows.length;
		amountOfColumns = rows[0].length();
		cells = new MazeCell[amountOfRows][amountOfColumns];

		for (int i = 0; i < rows.length; i++) {
			String individualRow = rows[i];

			for (int j = 0; j < rows[0].length(); j++) {
				cells[i][j] = new MazeCell(i, j);
				char individualCell = individualRow.charAt(j);
				cells[i][j].setOwner(this);

				if (individualCell == ('#')) {
					cells[i][j].setStatus(Status.WALL);
				}
				if (individualCell == (' ')) {
					cells[i][j].setStatus(Status.UNVISITED);

				}
				if (individualCell == ('S')) {
					cells[i][j].setStatus(Status.UNVISITED);
					start = cells[i][j];

				}
				if (individualCell == ('$')) {
					cells[i][j].setStatus(Status.GOAL);
					goal = cells[i][j];

				}

			}
		}

		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[0].length(); j++) {

				if (cells[i][j].getStatus() != Status.WALL) {
					if (i - 1 >= 0 && cells[i - 1][j].getStatus() != Status.WALL) {
						cells[i][j].addNeighbor(cells[i - 1][j]);
					}
					if (j - 1 >= 0 && cells[i][j - 1].getStatus() != Status.WALL) {
						cells[i][j].addNeighbor(cells[i][j - 1]);
					}
					if (i + 1 < rows.length && cells[i + 1][j].getStatus() != Status.WALL) {
						cells[i][j].addNeighbor(cells[i + 1][j]);
					}
					if (j + 1 < rows[0].length() && cells[i][j + 1].getStatus() != Status.WALL) {
						cells[i][j].addNeighbor(cells[i][j + 1]);

					}
				}
			}

		}

	}

	/**
	 * Returns the cell at the given position.
	 * 
	 * @param row The row
	 * @param col The column
	 * @return the cells at given row and column
	 */
	public MazeCell getCell(int row, int col) {
		return cells[row][col];
	}

	/**
	 * Returns the number of rows in this maze.
	 * 
	 * @return row number
	 */
	public int getRows() {
		return amountOfRows;
	}

	/**
	 * Returns the number of columns in this maze.
	 * 
	 * @return column number
	 */
	public int getColumns() {
		return amountOfColumns;
	}

	/**
	 * Returns the start cell for this maze.
	 * 
	 * @return The maze cell
	 */
	public MazeCell getStart() {
		return start;

	}

	/**
	 * Returns the goal cell for this maze.
	 * 
	 * @return The goal cell
	 */
	public MazeCell getGoal() {
		return goal;
	}

	/**
	 * Sets the observer for the cells of this maze.
	 * 
	 * @param obs The observer
	 */
	public void setObserver(MazeObserver obs) {
		observer = obs;
	}

	/**
	 * Notifies the observer that the given cell's status has changed.
	 * 
	 * @param cell a given cell in the maze
	 */
	public void updateStatus(MazeCell cell) {
		if (observer != null) {
			observer.updateStatus(cell);
		}
	}

}
