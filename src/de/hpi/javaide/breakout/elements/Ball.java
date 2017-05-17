package de.hpi.javaide.breakout.elements;

import java.awt.geom.Point2D;

import de.hpi.javaide.breakout.basics.Circle;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Blueprint for a Ball
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class Ball extends Circle {

	private PVector vector;

	public Ball(final Game game, final Point2D.Float position) {
		super(game, position, GameConstants.BALL_RADIUS);
		vector = new PVector();
		vector.set(1, 1);

	}

	@Override
	public void display() {
		game.fill(GameConstants.BALL_COLOR.getRed(), GameConstants.BALL_COLOR.getGreen(), GameConstants.BALL_COLOR.getBlue());
		game.ellipseMode(PApplet.CENTER);
		game.ellipse(position.x, position.y, getRadius(), getRadius());
	}

	/**
	 * moves the ball according to its vector
	 */
	public void move() {
		position.x += vector.x;
		position.y += vector.y;
	}

	public void setVector(final PVector newVector) {
		vector = newVector;
	}

	public void bounceY() {
		vector.y *= -1;
	}

	public void bounceX() {
		vector.x *= -1;
	}

	public float getSpeed() {
		return GameConstants.BALL_SPEED;
	}

}
