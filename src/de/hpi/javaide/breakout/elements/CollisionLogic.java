package de.hpi.javaide.breakout.elements;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.CollisionObject;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PApplet;

//TODO den Fehler unten haben wir absichtlich eingebaut, um zu zeigen, dass hier noch was getan werden muss.
//     Hier sollen alle Kollisionen geprüft werden. Trifft der Ball das Paddle.
//     Für jeden Stein in der Mauer: wurde er getroffen?
//     Erreicht der Ball den Spielfeld Rand.
//     Tipp: Schleifen könnten sich als hilfreich erweisen.
public class CollisionLogic {

	private static final Logger LOGGER = Logger.getLogger(CollisionLogic.class.getPackage().getName());

	/**
	 * The constructor of this class is private to make sure that it is only
	 * used as a static class. - it cannot be instantiated, - it cannot hold a
	 * state, - it contains only static methods
	 */
	private CollisionLogic() {
	}

	/**
	 * This method provides a way to determine if the ball collides with any of
	 * the collidable elements on the screen. Paddle, Bricks, ...
	 *
	 * @param game
	 * @param ball
	 * @param paddle
	 * @param wall
	 */
	public static void checkCollision(final Game game, final Ball ball, final Paddle paddle, final Wall wall) {
		LOGGER.trace("Checking collisions");
		if (ball.getXPosition() <= 0 || ball.getXPosition() >= GameConstants.SCREEN_X) {
			ball.bounceX();
		} else if (ball.getYPosition() <= 0 || ball.getYPosition() >= GameConstants.SCREEN_Y) {
			ball.bounceY();
		}
		if (checkBallPaddleCollision(ball, paddle)) {

			ball.bounceY();
		}

	}

	private static boolean checkBallPaddleCollision(final Ball ball, final Paddle paddle) {
		// Collision logic taken from
		// http://www.jeffreythompson.org/collision-detection/circle-rect.php
		int testX = ball.getXPosition();
		int testY = ball.getYPosition();

		if (ball.getXPosition() < paddle.getXPosition()) {
			testX = paddle.getXPosition(); // left edge
		} else if (ball.getXPosition() > paddle.getXPosition() + paddle.getWidth()) {
			testX = paddle.getXPosition() + paddle.getWidth(); // right edge
		}

		if (ball.getYPosition() < paddle.getYPosition()) {
			testY = paddle.getYPosition(); // top edge
		} else if (ball.getYPosition() > paddle.getXPosition() + paddle.getHeight()) {
			testY = paddle.getYPosition() + paddle.getHeight(); // bottom edge
		}

		final int distX = ball.getXPosition() - testX;
		final int distY = ball.getYPosition() - testY;

		final float distance = PApplet.sqrt((distX * distX) + (distY * distY));

		if (distance < ball.getRadius()) {
			LOGGER.debug("ball and paddle collided");
			LOGGER.debug("distance: " + distance + " radius: " + ball.getRadius());
			return true;
		}
		return false;

	}

	private static boolean hasCollision(final CollisionObject o1, final CollisionObject o2) {
		if (checkX(o1, o2) && checkY(o1, o2)) {
			LOGGER.debug(o1 + "collided with" + o2);
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkX(final CollisionObject o1, final CollisionObject o2) {
		return o1.getXPosition() >= o2.getXPosition() && o1.getXPosition() <= o2.getXPosition() + o2.getWidth();
	}

	private static boolean checkY(final CollisionObject o1, final CollisionObject o2) {
		return o1.getYPosition() >= o2.getYPosition() && o1.getYPosition() <= o2.getYPosition() + o2.getHeight();
	}

}
