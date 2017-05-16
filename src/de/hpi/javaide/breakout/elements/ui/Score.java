package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.GameFont;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

public class Score extends UIObject<String> {

	public Score(final Game game) {
		super(game);
	}

	@Override
	public void display() {
		game.textFont(GameFont.getFont24());
		game.text(Game.getScore(), 10, 30);

	}

	@Override
	public void update(final String input) {
		// score is self updating see display()
	}
}
