package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

public class Score extends UIObject {

	private int points;

	public Score(final Game game) {
		super(game);
	}

	@Override
	public void display() {
		game.textFont(Font.getFont24());
		game.text(points, 10, 30);

	}

	@Override
	public void update(final String input) {
		points = Integer.parseInt(input);

	}
}
