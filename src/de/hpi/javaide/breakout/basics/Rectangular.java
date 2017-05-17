package de.hpi.javaide.breakout.basics;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.hpi.javaide.breakout.starter.Game;

/**
 * Extends the CollisionObject to provide the basis for the rectangular
 * collidable Elements, such as the Paddle or a Brick.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public abstract class Rectangular extends CollisionObject {

	/**
	 * Constructor to create the basis for a rectangular object
	 *
	 * @param game
	 *            a {@link Game } reference to the game that provides access to
	 *            the Processing features
	 * @param position
	 *            a {@link Point2D.Float}representing the position of the Object
	 * @param dimension
	 *            a {@link Dimension} representing width and height of the
	 *            object
	 */
	public Rectangular(final Game game, final Point2D.Float position, final Dimension dimension) {
		super(game, position, dimension);
		geometry = new Rectangle2D.Float(getXPosition(), getYPosition(), getWidth(), getHeight());
	}

	@Override
	public void update(final Point2D.Float position, final Dimension dimension) {
		super.update(position, dimension);
		((Rectangle2D) getGeometry()).setFrame(getXPosition(), getYPosition(), getWidth(), getHeight());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" P: [").append(getXPosition()).append(",").append(getYPosition()).append("]");
		sb.append(" - ");
		sb.append(" D: [").append(getWidth()).append(",").append(getHeight()).append("]");
		return sb.toString();
	}
}
