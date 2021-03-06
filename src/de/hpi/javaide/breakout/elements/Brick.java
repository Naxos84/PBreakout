package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;
import processing.core.PImage;

public class Brick extends Rectangular {
	private static final int TOTAL_IMAGES = 3;

	private int hitPoints;

	private static PImage[] images;

	public Brick(final Game game, final Point2D.Float position, final Dimension dimension) {
		this(game, position, dimension, TOTAL_IMAGES);
	}

	public Brick(final Game game, final Point2D.Float position, final Dimension dimension, final int hitPoints) {
		super(game, position, dimension);
		if (images == null) {
			initImages(game);
		}
		this.hitPoints = hitPoints;
	}

	private static void initImages(final Game game) {
		images = new PImage[3];
		images[0] = game.loadImage("resources\\images\\Brick0.png");
		images[1] = game.loadImage("resources\\images\\Brick1.png");
		images[2] = game.loadImage("resources\\images\\Brick2.png");
	}

	@Override
	public void display() {
		if (hasHitPoints()) {
			game.image(images[TOTAL_IMAGES - hitPoints], position.x, position.y, dimension.width, dimension.height);
		}
	}

	/**
	 * checks wether this brick has remaining hitPoints
	 *
	 * @return {@code true} if this Brick still has some hitPoints and
	 *         {@code false} otherwise
	 */
	public boolean hasHitPoints() {
		return hitPoints > 0;
	}

	/**
	 * handle that this brick was hit
	 */
	public void onHit() {
		hitPoints--;
		Game.addToScore(1);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" P: [").append(getXPosition()).append(",").append(getYPosition()).append("]");
		sb.append(" - ");
		sb.append(" D: [").append(getWidth()).append(",").append(getHeight()).append("]");
		return sb.toString();
	}

}
