package de.hpi.javaide.breakout.basics;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import de.hpi.javaide.breakout.starter.Game;
import processing.core.PFont;

/**
 * Creates some Fonts in different sizes and make them easily available. Adapter
 * to the PFont class as offered by Processing
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class GameFont {
	private static PFont font16;
	private static PFont font24;
	private static PFont font32;

	private static AffineTransform affineTransform = new AffineTransform();
	private static FontRenderContext frc = new FontRenderContext(affineTransform, true, true);

	private GameFont() {
	}

	public static void init(final Game game) {
		font16 = game.createFont("Arial Black Standard", 16);
		font24 = game.createFont("Arial Black Standard", 24);
		font32 = game.createFont("Arial Black Standard", 32);
	}

	public static PFont getFont16() {
		return font16;
	}

	public static PFont getFont24() {
		return font24;
	}

	public static PFont getFont32() {
		return font32;
	}

	@SuppressWarnings("deprecation")
	public static float getTextWidth(final String text, final PFont pFont) {
		// I need to use this method. because an adequate Processing solution
		// exists only for version 3+
		return (int) pFont.getFont().getStringBounds(text, frc).getWidth();
	}

	@SuppressWarnings("deprecation")
	public static float getTextHeight(final String text, final PFont pFont) {
		// I need to use this method. because an adequate Processing solution
		// exists only for version 3+
		return (int) pFont.getFont().getStringBounds(text, frc).getHeight();
	}
}
