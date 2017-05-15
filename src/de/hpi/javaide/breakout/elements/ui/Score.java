package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

public class Score extends UIObject {

	public Score(final Game game) {
		super(game);
	}

	@Override
	public void display() {
		game.textFont(Font.getFont24());
		game.text(Game.getScore(), 10, 30);

	}

	@Override
	public void update(final String input) {

	}
}
