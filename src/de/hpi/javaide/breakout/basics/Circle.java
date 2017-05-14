package de.hpi.javaide.breakout.basics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

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

	public Circle(final Game game, final Point position, final int radius) {
		super(game, position, new Dimension(radius, radius));
		this.radius = radius;
		geometry = new Ellipse2D.Float(getXPosition(), getYPosition(), radius, radius);
	}

	/**
	 * Constructor to create the basis for a round object
	 *
	 * @param game
	 *            Game reference to the game that provides access to the
	 *            Processing features
	 * @param position
	 *            Position x,y position of the Object
	 * @param dimension
	 */
	public Circle(final Game game) {
		this(game, new Point(0, 0), 10);
	}

	/**
	 * Default constructor to create a round object with a size 10,10 at the top
	 * left corner of the screen.
	 * 
	 * @param game
	 *            Game
	 */
	@Override
	public void update(final Point position, final Dimension dimension) {
		super.update(position, dimension);
		((Ellipse2D) getGeometry()).setFrame(getXPosition(), getYPosition(), getWidth(), getHeight());
	}

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
