package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.interfaces.Displayable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

/**
 * Blueprint for the Wall
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class Wall implements Displayable, Iterable<Brick> {

	private static final Logger LOGGER = Logger.getLogger(Wall.class.getPackage().getName());
	private static final int marginLeft = 5;
	private static final int marginRight = 5;
	private static final int marginTop = 5;
	private static final int marginBottom = GameConstants.SCREEN_Y / 2 + 50;
	private static final int spacing = 4;

	/**
	 * Datastructure to keep the Bricks
	 */
	private final List<Brick> bricks = new ArrayList<>();

	public Wall(final Game game, final int columns, final int rows) {
		buildWall(game, columns, rows);
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
		LOGGER.debug("Building Wall");
		final int wallWidth = GameConstants.SCREEN_X - marginLeft - marginRight;
		LOGGER.trace("WallWidht: " + wallWidth);
		final int wallHeight = GameConstants.SCREEN_Y - marginTop - marginBottom;
		LOGGER.trace("WallHeight: " + wallHeight);

		final int columnWidth = wallWidth / columns;
		final int rowHeight = wallHeight / rows;

		for (int column = 0; column < columns; column++) {
			for (int row = 0; row < rows; row++) {
				final Brick brick = new Brick(game, new Point2D.Float(marginLeft + column * columnWidth, marginTop + row * rowHeight),
						new Dimension(columnWidth - spacing, rowHeight - spacing));
				brick.coord = column + ":" + row;
				LOGGER.debug("Adding Brick: " + brick + " to wall.");
				bricks.add(brick);
			}
		}
	}

	@Override
	public void display() {
		for (final Brick brick : bricks) {
			brick.display();
		}
	}
}
