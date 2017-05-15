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
// TODO neben dem Ergänzen der vom Interface erwarteten Methoden,
// sollte der Ball Eigenschaften wie Größe und Richtung mitbringen.
// Richtung wird in der Regel als Vector definiert.
// Vermutlich sollte er die Richtung ändern können und sehr wahrscheinlich
// wird früher oder später
// jemand wissen wollen in welche Richtung er fliegt.
public class Ball extends Circle {

	private static final int SPEED = 6;

	private final int red = 255;
	private final int green = 0;
	private final int blue = 0;

	private PVector vector;

	public Ball(final Game game, final Point2D.Float position) {
		super(game, position, 10);
		vector = new PVector();
		vector.set(1, 1);

	}

	@Override
	public void display() {
		game.fill(red, green, blue);
		game.ellipseMode(PApplet.CENTER);
		game.ellipse(position.x, position.y, getRadius(), getRadius());
		game.fill(GameConstants.BACKGROUNDFILL);
	}

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
		return SPEED;
	}

}
