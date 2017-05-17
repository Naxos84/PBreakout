package de.hpi.javaide.breakout.starter;

import de.hpi.javaide.breakout.basics.Color;

/**
 * Convient way to access the most important values for the game
 *
 * @author Matze
 *
 */
public class GameConstants {

	public static final int SCREEN_X = 800;
	public static final int SCREEN_Y = 600;

	public static final Color BACKGROUNDFILL = Color.BLACK;

	public static final String DEFAULT_FONT = "Arial Black Standard";

	public static final int BALL_SPEED = 6;
	public static final int BALL_RADIUS = 10;
	public static final Color BALL_COLOR = Color.RED;

	public static final int BALLDEPOT_NUM_BALLS = 3;

	private GameConstants() {
		// prevent instantiation
	}
}
