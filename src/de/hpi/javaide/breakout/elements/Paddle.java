package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import de.hpi.javaide.breakout.basics.Color;
import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;
import processing.core.PApplet;

/**
 * Create the paddle
 *
 * @param game
 *            Game provide access to the Processing features
 */
public class Paddle extends Rectangular {
	public Paddle(final Game game) {
		super(game, new Point2D.Float(game.width / 2, game.height - 20), new Dimension(100, 20));
		setColor(Color.GREY);
	}

	@Override
	public void display() {
		game.rectMode(PApplet.CORNER);
		game.noStroke();
		game.fill(getRed(), getGreen(), getBlue());
		game.rect(getXPosition(), getYPosition(), getWidth(), getHeight());
	}

	/**
	 * moves the paddle to the current mouse y -position
	 */
	public void move() {
		update(new Point2D.Float(game.mouseX - getWidth() / 2, getYPosition()), new Dimension(getWidth(), getHeight()));
	}
}
