package de.hpi.javaide.breakout.basics;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import de.hpi.javaide.breakout.starter.Game;

/**
 * Extends the CollisionObject to provide the basis for the round collidable
 * Elements, such as the Ball
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public abstract class Circle extends CollisionObject {

	private final int radius;

	/**
	 * Constructor to create the basis for a round object
	 *
	 * @param game
	 *            a {@link Game} reference to the game that provides access to
	 *            the Processing features
	 * @param position
	 *            a {@link Point2D.Float} representing the position of this
	 *            circle
	 * @param radius
	 *            the radius of the circle
	 */
	public Circle(final Game game, final Point2D.Float position, final int radius) {
		super(game, position, new Dimension(radius, radius));
		this.radius = radius;
		geometry = new Ellipse2D.Float(getXPosition(), getYPosition(), radius, radius);
	}

	/**
	 * Constructs a default circle
	 *
	 * @param game
	 *            a {@link Game} reference to the game that provides access to
	 *            the Processing features
	 */
	public Circle(final Game game) {
		this(game, new Point2D.Float(0, 0), 10);
	}

	@Override
	public void update(final Point2D.Float position, final Dimension dimension) {
		super.update(position, dimension);
		((Ellipse2D) getGeometry()).setFrame(getXPosition(), getYPosition(), getWidth(), getHeight());
	}

	/**
	 *
	 * @return the radius of the circle
	 */
	public int getRadius() {
		return radius;
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
