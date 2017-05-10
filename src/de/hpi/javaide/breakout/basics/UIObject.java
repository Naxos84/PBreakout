package de.hpi.javaide.breakout.basics;

import java.awt.Point;

import de.hpi.javaide.breakout.interfaces.Displayable;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Provides a common basis for non-collidable user interface objects, such as a high score, etc.
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public abstract class UIObject implements Displayable {

	/**
	 * The reference to the Game that provides access to the Processing methods.
	 */
	protected Game game;
	protected int xPosition;
	protected int yPosition;

	/**
	 * Constructor to pass the Game reference to the new UIobject
	 * @param game
	 */
	public UIObject(final Game game) {
		this.game = game;
	}

	/**
	 * Method to update the contents of a UIObject
	 * @param input String the new content of the UIObject
	 */
	public abstract void update(String input);

	public void setPosition(final Point position) {
		xPosition = position.x;
		yPosition = position.y;
	}

	public Point getPosition() {
		return new Point(xPosition, yPosition);
	}
}
