package de.hpi.javaide.breakout.basics;

import java.awt.geom.Point2D;

import de.hpi.javaide.breakout.interfaces.Displayable;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Provides a common basis for non-collidable user interface objects, such as a
 * high score, etc.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public abstract class UIObject<T> implements Displayable {

	private static final int DEFAULT_RED = 255;
	private static final int DEFAULT_GREEN = 255;
	private static final int DEFAULT_BLUE = 255;

	/**
	 * The reference to the Game that provides access to the Processing methods.
	 */
	protected Game game;
	protected float xPosition;
	protected float yPosition;
	protected Color color;

	/**
	 * Constructor to pass the Game reference to the new UIobject
	 *
	 * @param game
	 *            a {@link Game} for access to the processing features
	 */
	public UIObject(final Game game) {
		this.game = game;
		color = getDefaultColor();
	}

	protected static Color getDefaultColor() {
		return new Color(DEFAULT_RED, DEFAULT_GREEN, DEFAULT_BLUE);
	}

	/**
	 * Method to update the contents of a UIObject
	 *
	 * @param input
	 *            the new content of the UIObject
	 */
	public abstract void update(T input);

	/**
	 * sets the position of this UIObject
	 *
	 * @param position
	 *            a {@link Point2D.Float} representing the position
	 */
	public void setPosition(final Point2D.Float position) {
		xPosition = position.x;
		yPosition = position.y;
	}

	/**
	 * returns the position of this UIObject
	 *
	 * @return the position as {@link Point2D.Float}
	 */
	public Point2D.Float getPosition() {
		return new Point2D.Float(xPosition, yPosition);
	}
}
