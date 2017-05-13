package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;
import processing.core.PApplet;

/**
 * Create the paddle
 * @param game Game provide access to the Processing features
 */
public class Paddle extends Rectangular {
	public Paddle(final Game game) {
		super(game, new Point(game.width / 2, game.height - 20), new Dimension(100, 20));
		setColor(150, 150, 150);
	}

	@Override
	public void display() {
		game.rectMode(PApplet.CORNER);
		game.noStroke();
		game.fill(getRed(), getGreen(), getBlue());
		game.rect(getXPosition(), getYPosition(), getWidth(), getHeight());
	}

	public void move() {
		update(new Point(game.mouseX, getYPosition()), new Dimension(getWidth(), getHeight()));
	}

	public void moveRight() {
		// TODO Auto-generated method stub

	}
}
