package de.hpi.javaide.breakout.helper;

import java.awt.Point;
import java.awt.Rectangle;

public class PointHelper {

	public static boolean isInside(final Point point, final Rectangle rect) {
		boolean inside = false;
		if (point.getX() >= rect.getX() && point.getX() <= rect.getX() + rect.getWidth()) {
			inside = true;
		}
		if (point.getY() >= rect.getY() && point.getY() <= rect.getY() + rect.getHeight()) {
			inside &= true;
		}
		return inside;
	}
}
