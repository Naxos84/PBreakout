package de.hpi.javaide.breakout.basics;

import de.hpi.javaide.breakout.interfaces.Colorable;

public class Color implements Colorable {

	/**
	 * the components of the color
	 */
	private int red;
	private int green;
	private int blue;

	/**
	 * default constructor to create a black color
	 */
	public Color() {
		red = 0;
		green = 0;
		blue = 0;
	}

	/**
	 * Constructor to create a color with the specified values
	 * 
	 * @param red
	 *            red portion of the color 0-255
	 * @param green
	 *            green portion of the color 0-255
	 * @param blue
	 *            blue portion of the color 0-255
	 */
	public Color(final int red, final int green, final int blue) {
		if (red < 0 || red > 255) {
			throw new IllegalArgumentException("The red part of a color can only be between 0 and 255.");
		} else if (green < 0 || green > 255) {
			throw new IllegalArgumentException("The green part of a color can only be between 0 and 255.");
		} else if (blue < 0 || blue > 255) {
			throw new IllegalArgumentException("The blue part of a color can only be between 0 and 255.");
		}
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	@Override
	public int getRed() {
		return red;
	}

	/**
	 * Set the red component
	 *
	 * @param red
	 *            (0-255)
	 */
	public void setRed(final int red) {
		this.red = red;
	}

	@Override
	public int getGreen() {
		return green;
	}

	/**
	 * Set the green component
	 *
	 * @param green
	 *            (0-255)
	 */
	public void setGreen(final int green) {
		this.green = green;
	}

	@Override
	public int getBlue() {
		return blue;
	}

	/**
	 * Set the blue components
	 *
	 * @param blue
	 *            (0-255)
	 */
	public void setBlue(final int blue) {
		this.blue = blue;
	}

	@Override
	public void setColor(final int red, final int green, final int blue) {
		setRed(red);
		setGreen(green);
		setBlue(blue);
	}
}