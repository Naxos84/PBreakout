package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.GameFont;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;
import processing.core.PFont;

//TODO nach dem die fehlenden Methoden ergänzt wurden, muss hier noch ein Konstruktorparameter
//     an das zugehörige Attribut übergeben werden.

public class Info extends UIObject<String> {

	private String content;
	private final PFont font = GameFont.getFont24();

	public Info(final Game game, final String content) {
		super(game);
		this.content = content;
	}

	@Override
	public void display() {
		game.textFont(GameFont.getFont24());
		game.text(content, xPosition, yPosition);
	}

	@Override
	public void update(final String input) {
		content = input;

	}

	public float getFontWidth() {
		return GameFont.getTextWidth(content, font);
	}
}
