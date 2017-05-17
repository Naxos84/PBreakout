package de.hpi.javaide.breakout.basics;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
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
		font16 = game.createFont(GameConstants.DEFAULT_FONT, 16);
		font24 = game.createFont(GameConstants.DEFAULT_FONT, 24);
		font32 = game.createFont(GameConstants.DEFAULT_FONT, 32);
	}

	/**
	 *
	 * @return a default font with size 16
	 */
	public static PFont getFont16() {
		return font16;
	}

	/**
	 *
	 * @return a default font with size 24
	 */
	public static PFont getFont24() {
		return font24;
	}

	/**
	 *
	 * @return a default font with size 32
	 */
	public static PFont getFont32() {
		return font32;
	}

	/**
	 * Returns the width of the given {@link String} and the used {@link PFont}
	 *
	 * @param text
	 *            the text of which the width should be calculated
	 * @param pFont
	 *            the font of the text
	 * @return the calculated width of the text
	 */
	@SuppressWarnings("deprecation")
	public static float getTextWidth(final String text, final PFont pFont) {
		// I need to use this method. because an adequate Processing solution
		// exists only for version 3+
		return (int) pFont.getFont().getStringBounds(text, frc).getWidth();
	}

	/**
	 * Returns the height of the given {@link String} and the used {@link PFont}
	 *
	 * @param text
	 *            the text of which the height should be calculated
	 * @param pFont
	 *            the font of the text
	 * @return the calculated height of the text
	 */
	@SuppressWarnings("deprecation")
	public static float getTextHeight(final String text, final PFont pFont) {
		// I need to use this method. because an adequate Processing solution
		// exists only for version 3+
		return (int) pFont.getFont().getStringBounds(text, frc).getHeight();
	}
}
