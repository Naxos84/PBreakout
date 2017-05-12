package de.hpi.javaide.breakout.elements;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.interfaces.Displayable;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Blueprint for the Wall
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class Wall implements Displayable, Iterable<Brick> {

	private static final Logger LOGGER = Logger.getLogger(Wall.class.getPackage().getName());

	/**
	 * Datastructure to keep the Bricks
	 */
	private List<Brick> bricks;


	public Wall(final Game game, final int i, final int j) {
		buildWall(game, i, j);
	}
	@Override
	public Iterator<Brick> iterator() {
		return bricks.iterator();
	}
	/**
	 * Build the wall by putting the single bricks into their position
	 * Hint: You might want to use one or two for-loops
	 *
	 * @param game
	 * @param columns
	 * @param rows
	 */
	private void buildWall(final Game game, final int columns, final int rows) {
		LOGGER.debug("Building Wall");
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}
}
