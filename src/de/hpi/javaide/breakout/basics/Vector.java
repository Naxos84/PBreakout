package de.hpi.javaide.breakout.basics;

import processing.core.PVector;

/**
 * Adapter to Processing's PVector
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class Vector {

	/**
	 * The PVector object to be wrapped
	 */
	private PVector pVector;

	/**
	 * Constructor to set the x and the y value of the Vector
	 * @param x float
	 * @param y float
	 */
	public Vector(float x, float y) {
		pVector = new PVector(x, y);
	}

	public float getX() {
		return pVector.x;
	}

	public float getY() {
		return pVector.y;
	}

	public void setX(float x) {
		pVector.x = x;
	}

	public void setY(float y) {
		pVector.y = y;
	}
	
	/**
	 * Multiply the given Vector with the given number
	 * @param n
	 */
	public void multiply(float n) {
		pVector.mult(n);
	}

	/**
	 * Normalize the wrapped vector
	 */
	public void normalize() {
		pVector.normalize();
	}

}
