package de.hpi.javaide.breakout.basics;

import de.hpi.javaide.breakout.interfaces.Colorable;

public class Color implements Colorable {

	public static final Color RED = new Color(255, 0, 0);
	public static final Color GREEN = new Color(0, 255, 255);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color GREY = new Color(150, 150, 150);

	/**
	 * the components of the color
	 */
	private int r;
	private int g;
	private int b;

	/**
	 * default constructor to create a black color
	 */
	public Color() {
		r = 0;
		g = 0;
		b = 0;
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
		this.r = red;
		this.g = green;
		this.b = blue;
	}

	@Override
	public int getRed() {
		return r;
	}

	/**
	 * Set the red component
	 *
	 * @param red
	 *            (0-255)
	 */
	public void setRed(final int red) {
		this.r = red;
	}

	@Override
	public int getGreen() {
		return g;
	}

	/**
	 * Set the green component
	 *
	 * @param green
	 *            (0-255)
	 */
	public void setGreen(final int green) {
		this.g = green;
	}

	@Override
	public int getBlue() {
		return b;
	}

	/**
	 * Set the blue components
	 *
	 * @param blue
	 *            (0-255)
	 */
	public void setBlue(final int blue) {
		this.b = blue;
	}

	@Override
	public void setColor(final Color color) {
		setRed(color.getRed());
		setGreen(color.getGreen());
		setBlue(color.getBlue());

	}
}