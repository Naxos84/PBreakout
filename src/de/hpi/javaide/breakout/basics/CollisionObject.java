package de.hpi.javaide.breakout.basics;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;

import de.hpi.javaide.breakout.interfaces.Collidable;
import de.hpi.javaide.breakout.interfaces.Colorable;
import de.hpi.javaide.breakout.interfaces.Displayable;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Base class to build collidable objects from. Provides the required basis for
 * Elliptics and Rectangulars
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public abstract class CollisionObject implements Collidable, Displayable, Colorable {

	/**
	 * the geometrical Shape that defines the form of the object.
	 */
	protected Shape geometry;
	/**
	 * a reference to the game class to provide access to the processing methods
	 */
	protected Game game;
	/**
	 * width and height of the object
	 */
	protected Dimension dimension;
	/**
	 * the x,y position of an object
	 */
	protected Point2D.Float position;
	/**
	 * the color of the object
	 */
	private Color color;

	/**
	 * Constructor. Initializes the reference to the Game, the size and the
	 * position of the object
	 *
	 * @param game
	 *            a {@link Game}reference, to access the Processing features
	 * @param position
	 *            a {@link Point2D.Float} representing the position
	 * @param dimension
	 *            a {@link Dimension}representing the width and height of the
	 *            object
	 */
	public CollisionObject(final Game game, final Point2D.Float position, final Dimension dimension) {
		this.game = game;
		this.position = position;
		this.dimension = dimension;
		color = new Color(255, 255, 255);
	}

	@Override
	public int getWidth() {
		return dimension.width;
	}

	@Override
	public int getHeight() {
		return dimension.height;
	}

	@Override
	public float getXPosition() {
		return position.x;
	}

	protected void setXPosition(final int x) {
		position.x = x;
	}

	@Override
	public float getYPosition() {
		return position.y;
	}

	protected void setYPosition(final int y) {
		position.y = y;
	}

	@Override
	public int getRed() {
		return color.getRed();
	}

	@Override
	public int getGreen() {
		return color.getGreen();
	}

	@Override
	public int getBlue() {
		return color.getBlue();
	}

	@Override
	public Shape getGeometry() {
		return geometry;
	}

	@Override
	public void update(final Point2D.Float position, final Dimension dimension) {
		this.position = position;
		this.dimension = dimension;
	}

	@Override
	public void setColor(final Color color) {
		this.color = color;
	}
}
