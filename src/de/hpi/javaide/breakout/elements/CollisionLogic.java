package de.hpi.javaide.breakout.elements;

import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PVector;

//TODO den Fehler unten haben wir absichtlich eingebaut, um zu zeigen, dass hier noch was getan werden muss.
//     Hier sollen alle Kollisionen geprüft werden. Trifft der Ball das Paddle.
//     Für jeden Stein in der Mauer: wurde er getroffen?
//     Erreicht der Ball den Spielfeld Rand.
//     Tipp: Schleifen könnten sich als hilfreich erweisen.
public class CollisionLogic {

	/**
	 * The constructor of this class is private to make sure that it is only used as a static class.
	 * - it cannot be instantiated,
	 * - it cannot hold a state,
	 * - it contains only static methods
	 */
	private CollisionLogic() {}

	/**
	 * This method provides a way to determine if the ball collides with any of the collidable elements on the screen.
	 * Paddle, Bricks, ...
	 *
	 * @param game
	 * @param ball
	 * @param paddle
	 * @param wall
	 */
	public static void checkCollision(final Game game, final Ball ball, final Paddle paddle, final Wall wall) {
		if (ball.getXPosition() <= 0 || ball.getXPosition() >= GameConstants.SCREEN_X) {
			ball.getVector().mult(-1f);
		} else if (ball.getYPosition() <= 0 || ball.getYPosition() >= GameConstants.SCREEN_Y) {
			ball.setVector(PVector.mult(ball.getVector(), -1f, new PVector(0, 1)));
		}
	}

}
