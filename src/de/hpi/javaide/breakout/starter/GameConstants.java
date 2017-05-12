package de.hpi.javaide.breakout.starter;

import java.awt.Point;

public class GameConstants {
	public static final int LIVES = 3;
	public static final int SCREEN_X = 800;
	public static final int SCREEN_Y = 600;
	public static final Point STARTPOSITION = new Point(SCREEN_X / 2, SCREEN_Y / 2);
	public static final int		BACKGROUNDFILL	= 0;

	private GameConstants() {
		// prevent instantiation
	}
}
