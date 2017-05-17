package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Color;
import de.hpi.javaide.breakout.basics.GameFont;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;
import processing.core.PFont;

public class Button extends UIObject<String> {

	private String text = "";
	private static final PFont font = GameFont.getFont16();

	public Button(final Game game) {
		super(game);
	}

	public void setText(final String text) {
		this.text = text;
	}

	public float getWidth() {
		return GameFont.getTextWidth(text, font);
	}

	@Override
	public void display() {
		game.fill(color.getRed(), color.getGreen(), color.getBlue());
		game.textFont(font);
		game.text(text, xPosition, yPosition);
	}

	@Override
	public void update(final String input) {
		text = input;
	}

	/**
	 * sets the focus of this button. This is used for selecting them via the
	 * keyboard
	 *
	 * @param isFocussed
	 *            wether this button has focus or not
	 */
	public void setFocus(final boolean isFocussed) {
		if (isFocussed) {
			color.setColor(Color.GREEN);
		} else {
			color = getDefaultColor();
		}
	}

}
