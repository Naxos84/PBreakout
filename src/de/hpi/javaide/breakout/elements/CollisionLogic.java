package de.hpi.javaide.breakout.elements;

import java.util.Iterator;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.Circle;
import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.screens.GameScreen;
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
				ball.bounceY();
				brick.onHit();
				iterator.remove();
				break;
			case VERTICAL:
				ball.bounceX();
				brick.onHit();
				iterator.remove();
				break;
			case NONE:
			default:
				break;
			}
		}

	}

	private static RectangularEdge checkCircelRectangleCollision(final Circle circle, final Rectangular rect) {
		// Collision logic taken from
		// http://www.jeffreythompson.org/collision-detection/circle-rect.php
		LOGGER.trace("Checking Collistion between " + circle + " and " + rect);
		int testX = circle.getXPosition();
		int testY = circle.getYPosition();
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
		} else if (circle.getYPosition() > rect.getXPosition() + rect.getHeight()) {
			testY = rect.getYPosition() + rect.getHeight(); // bottom edge
			collidedRectEdge = RectangularEdge.HORIZONTAL;
			LOGGER.trace("Collision could be bottom edge");
		}

		LOGGER.trace("testX: " + testX);
		LOGGER.trace("testY: " + testY);

		final int distX = circle.getXPosition() - testX;
		LOGGER.trace("distX: " + distX);
		final int distY = circle.getYPosition() - testY;
		LOGGER.trace("distY " + distY);

		final float distance = PApplet.sqrt((float) (distX * distX) + (distY * distY));

		if (distance < circle.getRadius()) {
			LOGGER.debug(circle + " and " + rect + " collided --> " + collidedRectEdge);
			LOGGER.debug("distance: " + distance + " radius: " + circle.getRadius());
			return collidedRectEdge;
		}
		return RectangularEdge.NONE;

	}

	// private static boolean hasCollision(final CollisionObject o1, final
	// CollisionObject o2) {
	// if (checkX(o1, o2) && checkY(o1, o2)) {
	// LOGGER.debug(o1 + "collided with" + o2);
	// return true;
	// } else {
	// return false;
	// }
	// }
	//
	// private static boolean checkX(final CollisionObject o1, final
	// CollisionObject o2) {
	// return o1.getXPosition() >= o2.getXPosition() && o1.getXPosition() <=
	// o2.getXPosition() + o2.getWidth();
	// }
	//
	// private static boolean checkY(final CollisionObject o1, final
	// CollisionObject o2) {
	// return o1.getYPosition() >= o2.getYPosition() && o1.getYPosition() <=
	// o2.getYPosition() + o2.getHeight();
	// }

	private enum RectangularEdge {
		HORIZONTAL(), VERTICAL(), NONE();
	}

}
