package de.hpi.javaide.breakout.elements;

import java.awt.geom.Point2D;
import java.util.Random;

import de.hpi.javaide.breakout.interfaces.Displayable;
import de.hpi.javaide.breakout.interfaces.Measureable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PVector;

//TODO hier werden wir sicher eine Collection brauchen um die Bälle unterzubringen.
//     Vermutlich werden wir wissen wollen wann das Depot leer ist.
//     Irgendwie müssen die Bälle an den Start gebracht werden.
public class BallDepot implements Displayable, Measureable {

	private final Game game;
	private final Random random;
	private int numberOfBalls;

	public BallDepot(final Game game) {
		this(game, 3);
	}

	public BallDepot(final Game game, final int numberOfBalls) {
		this.game = game;
		random = new Random();
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
		game.fill(GameConstants.BACKGROUNDFILL);
	}

	public boolean isEmpty() {
		return numberOfBalls <= 0;
	}

	public Ball dispense() {
		if (numberOfBalls > 0) {
			final Ball ball = new Ball(game, new Point2D.Float(GameConstants.STARTPOSITION.x, GameConstants.STARTPOSITION.y));
			// final PVector v = new
			// PVector(random.nextInt(GameConstants.SCREEN_X),
			// random.nextInt(GameConstants.SCREEN_Y));
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
