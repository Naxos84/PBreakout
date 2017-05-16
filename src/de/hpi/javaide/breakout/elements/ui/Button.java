package de.hpi.javaide.breakout.elements.ui;

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

	public void setFocus(final boolean isFocussed) {
		if (isFocussed) {
			color.setColor(0, 255, 0);
		} else {
			color = getDefaultColor();
		}
	}

}
