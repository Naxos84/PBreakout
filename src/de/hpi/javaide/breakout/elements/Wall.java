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
import processing.data.XML;

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

	private static final String DEFAULT_COLUMS = "10";
	private static final String DEFAULT_ROWS = "5";

	/**
	 * Datastructure to keep the Bricks
	 */
	private final List<Brick> bricks = new ArrayList<>();

	/**
	 * Constructs a wall
	 *
	 * @param game
	 *            a game Instance
	 * @param wallConfig
	 *            an {@link XML} element that was read from the config file
	 */
	public Wall(final Game game, final XML wallConfig) {
		final XML columnsConfig = wallConfig.getChild("columns");
		final XML rowsConfig = wallConfig.getChild("rows");

		if (columnsConfig != null && rowsConfig != null) {
			try {
				final int columns = Integer.parseInt(columnsConfig.getContent(DEFAULT_COLUMS));
				final int rows = Integer.parseInt(rowsConfig.getContent(DEFAULT_ROWS));
				buildWall(game, columns, rows);
			} catch (final NumberFormatException e) {
				LOGGER.error("Error reading values from wallConfig.", e);
				buildWall(game, Integer.parseInt(DEFAULT_COLUMS), Integer.parseInt(DEFAULT_ROWS));
			}
		} else {
			LOGGER.warn("Wall config invalid. Taking default Values");
			buildWall(game, Integer.parseInt(DEFAULT_COLUMS), Integer.parseInt(DEFAULT_ROWS));
		}
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
				LOGGER.debug("Adding Brick: to wall.");
				bricks.add(buildBrick(game, new Point2D.Float(marginLeft + column * columnWidth, marginTop + row * rowHeight),
						new Dimension(columnWidth - spacing, rowHeight - spacing)));
			}
		}
	}

	private Brick buildBrick(final Game game, final Point2D.Float position, final Dimension dimension) {
		return new Brick(game, position, dimension);
	}

	@Override
	public void display() {
		for (final Brick brick : bricks) {
			brick.display();
		}
	}

	/**
	 * checks wether this wall has bricks left
	 * 
	 * @return {@code true} if there is at least 1 brick left and
	 *         {@code false otherwise}
	 */
	public boolean hasBricks() {
		return !bricks.isEmpty();
	}
}
