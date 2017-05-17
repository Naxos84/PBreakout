package de.hpi.javaide.breakout.interfaces;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Interface for objects that might collide. Can set their internal state
 * (position, dimension) via update and return their geometry as a @Shape
 * object.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public interface Collidable extends Measureable {
	/**
	 * Combined setter for position and dimension.
	 *
	 * @param position
	 *            a {@link Point2D.Float} representing the Position
	 * @param dimension
	 *            a {@link Dimension} representing width and height.
	 */
	void update(Point2D.Float position, Dimension dimension);

	/**
	 * Getter for the geometry behind a collidable object on the screen The
	 * geometry behind a collidable object is defined by a java.awt.Shape. In
	 * our case probably a Rectangle or an Ellipse.
	 *
	 * @return the Shape that defines the geometry behind a collidable object.
	 */
	Shape getGeometry();
}