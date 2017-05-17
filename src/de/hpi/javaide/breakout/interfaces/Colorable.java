package de.hpi.javaide.breakout.interfaces;

import de.hpi.javaide.breakout.basics.Color;

/**
 * Interface for all objects that have some sort of color. Defines Getters for
 * the separate Red, Green, and Blue components of the RGB spectrum and a
 * combined Setter for all components.
 *
 * @author Ralf Teusner and Tom Staubitz
 */
public interface Colorable {
	/**
	 * Get the red portion of a color
	 *
	 * @return the red portion of a color as an int within the range of 0 and
	 *         255
	 */
	int getRed();

	/**
	 * Get the green portion of a color
	 *
	 * @return the green portion of a color as an int within the range of 0 and
	 *         255
	 */
	int getGreen();

	/**
	 * Get the blue portion of a color
	 *
	 * @return the blue portion of a color as an int within the range of 0 and
	 *         255
	 */
	int getBlue();

	/**
	 * Sets tht color to the given {@link Color}
	 *
	 * @param color
	 *            the color to set
	 */
	void setColor(final Color color);
}
