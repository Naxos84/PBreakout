package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

//TODO nach dem die fehlenden Methoden ergänzt wurden, muss hier noch ein Konstruktorparameter
//     an das zugehörige Attribut übergeben werden.

public class Info extends UIObject {

	private String content;

	public Info(final Game game, final String content) {
		super(game);
		this.content = content;
	}



	@Override
	public void display() {
		game.textFont(Font.getFont24());
		game.text(content, xPosition, yPosition);
	}

	@Override
	public void update(final String input) {
		content = input;

	}
}
