package de.hpi.javaide.breakout.elements;

import java.awt.geom.Point2D;

import de.hpi.javaide.breakout.interfaces.Displayable;
import de.hpi.javaide.breakout.interfaces.Measureable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PVector;

public class BallDepot implements Displayable, Measureable {

	private static final Point2D.Float STARTPOSITION = new Point2D.Float(GameConstants.SCREEN_X / 2f, GameConstants.SCREEN_Y / 2f);

	private final Game game;
	private int numberOfBalls;

	public BallDepot(final Game game) {
		this(game, GameConstants.BALLDEPOT_NUM_BALLS);
	}

	public BallDepot(final Game game, final int numberOfBalls) {
		this.game = game;
		this.numberOfBalls = numberOfBalls;
	}

	@Override
	public float getXPosition() {
		return 50;
	}

	@Override
	public float getYPosition() {
		return 50;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public void display() {
		game.fill(255, 0, 0);
		for (int i = 0; i < numberOfBalls; i++) {
			game.ellipse(getXPosition() + i * 10, getYPosition(), 5, 5);

		}
	}

	public boolean isEmpty() {
		return numberOfBalls <= 0;
	}

	/**
	 * creates a ball and removes one ball from the ball depot
	 * 
	 * @return
	 */
	public Ball dispense() {
		if (numberOfBalls > 0) {
			final Ball ball = new Ball(game, STARTPOSITION);
			final PVector v = new PVector(1, 1);
			v.setMag(ball.getSpeed());
			ball.setVector(v);
			numberOfBalls--;
			return ball;
		} else {
			return null;
		}
	}
}
