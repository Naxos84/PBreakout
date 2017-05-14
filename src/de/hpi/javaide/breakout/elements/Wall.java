package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
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

	// TODO remove Brick when destroyed

	private static final Logger LOGGER = Logger.getLogger(Wall.class.getPackage().getName());
	Brick brick;
	/**
	 * Datastructure to keep the Bricks
	 */
	private final List<Brick> bricks = new ArrayList<>();

	public Wall(final Game game, final int i, final int j) {
		buildWall(game, i, j);
	}

	@Override
	public Iterator<Brick> iterator() {
		return bricks.iterator();
	}

	/**
	 * Build the wall by putting the single bricks into their position Hint: You
	 * might want to use one or two for-loops
	 *
	 * @param game
	 * @param columns
	 * @param rows
	 */
	private void buildWall(final Game game, final int columns, final int rows) {
		bricks.add(new Brick(game, new Point(60, 60), new Dimension(150, 150)));
		LOGGER.debug("Building Wall");
	}

	@Override
	public void display() {
		for (final Brick brick : bricks) {
			brick.display();
		}

	}
}
