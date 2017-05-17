package de.hpi.javaide.breakout.elements;

import java.util.Iterator;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.Circle;
import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.screens.GameScreen;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PApplet;

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
	 *            a game instance
	 * @param ball
	 *            a ball instance
	 * @param paddle
	 *            a paddle instance
	 * @param wall
	 *            a wall instance
	 */
	public static void checkCollision(final Game game, final Ball ball, final Paddle paddle, final Wall wall) {
		LOGGER.trace("Checking collisions");
		if (ball.getXPosition() - ball.getRadius() < 0 || ball.getXPosition() + ball.getRadius() > GameConstants.SCREEN_X) {
			LOGGER.debug("Collision with side wall. BallX = " + ball.getXPosition());
			ball.bounceX();
		} else if (ball.getYPosition() - ball.getRadius() < 0) {
			LOGGER.debug("Collision with top wall. BallY = " + ball.getYPosition());
			ball.bounceY();
		} else if (ball.getYPosition() + ball.getRadius() > GameConstants.SCREEN_Y) {
			LOGGER.debug("Ball fell down.");
			GameScreen.destroyCurrentBall(game);
		}
		switch (checkCircelRectangleCollision(ball, paddle)) {
		case HORIZONTAL:
			ball.bounceY();
			break;
		case VERTICAL:
			ball.bounceX();
			break;
		case NONE:
		default:
			break;
		}
		for (final Iterator<Brick> iterator = wall.iterator(); iterator.hasNext();) {
			final Brick brick = iterator.next();
			switch (checkCircelRectangleCollision(ball, brick)) {
			case HORIZONTAL:
				LOGGER.trace(ball + " collided with " + brick);
				ball.bounceY();
				brick.onHit();
				break;
			case VERTICAL:
				LOGGER.trace(ball + " collided with " + brick);
				ball.bounceX();
				brick.onHit();
				break;
			case NONE:
			default:
				break;
			}
			if (!brick.hasHitPoints()) {
				iterator.remove();
			}
		}

	}

	private static RectangularEdge checkCircelRectangleCollision(final Circle circle, final Rectangular rect) {
		// Collision logic taken from
		// http://www.jeffreythompson.org/collision-detection/circle-rect.php
		LOGGER.trace("Checking Collistion between " + circle + " and " + rect);
		float testX = circle.getXPosition();
		float testY = circle.getYPosition();
		RectangularEdge collidedRectEdge = RectangularEdge.NONE;

		if (circle.getXPosition() < rect.getXPosition()) {
			testX = rect.getXPosition(); // left edge
			collidedRectEdge = RectangularEdge.VERTICAL;
			LOGGER.trace("Collision could be left edge");
		} else if (circle.getXPosition() > rect.getXPosition() + rect.getWidth()) {
			testX = rect.getXPosition() + rect.getWidth(); // right edge
			collidedRectEdge = RectangularEdge.VERTICAL;
			LOGGER.trace("Collision could be right edge");
		}

		if (circle.getYPosition() < rect.getYPosition()) {
			testY = rect.getYPosition(); // top edge
			collidedRectEdge = RectangularEdge.HORIZONTAL;
			LOGGER.trace("Collision could be top edge");
		} else if (circle.getYPosition() > rect.getYPosition() + rect.getHeight()) {
			testY = rect.getYPosition() + rect.getHeight(); // bottom edge
			collidedRectEdge = RectangularEdge.HORIZONTAL;
			LOGGER.trace("Collision could be bottom edge");
		}

		LOGGER.trace("testX: " + testX);
		LOGGER.trace("testY: " + testY);

		final float distX = circle.getXPosition() - testX;
		LOGGER.trace("distX: " + distX);
		final float distY = circle.getYPosition() - testY;
		LOGGER.trace("distY " + distY);

		final float distance = PApplet.sqrt(distX * distX + (distY * distY));
		LOGGER.trace("Distance between " + circle + " and " + rect + " is " + distance);
		if (distance < circle.getRadius()) {
			LOGGER.debug(circle + " and " + rect + " collided --> " + collidedRectEdge);
			LOGGER.debug("distance: " + distance + " radius: " + circle.getRadius());
			return collidedRectEdge;
		}
		return RectangularEdge.NONE;

	}

	private enum RectangularEdge {
		HORIZONTAL(), VERTICAL(), NONE();
	}

}
