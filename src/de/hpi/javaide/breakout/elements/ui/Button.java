package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

public class Button extends UIObject {

	private final int width;
	private final int height;
	private String text = "";

	public Button(final Game game, final int width, final int height) {
		super(game);
		this.width = width;
		this.height = height;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	@Override
	public void display() {
		game.fill(255, 0, 0);
		game.rect(xPosition, yPosition, width, height);
		game.fill(255);
		game.textFont(Font.getFont16());
		game.text(text, xPosition, yPosition);
	}

	@Override
	public void update(final String input) {
		text = input;

	}

}
