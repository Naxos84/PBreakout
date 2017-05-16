package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.GameFont;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

public class Timer extends UIObject<String> {

	private final int seconds;

	public Timer(final Game game) {
		super(game);
		seconds = 60;
	}

	@Override
	public void display() {
		game.fill(255);
		game.textFont(GameFont.getFont16());
		game.text("Time left: " + seconds, game.width - 150f, game.height - 80f);
	}

	@Override
	public void update(final String input) {
		// TODO Auto-generated method stub

	}
}
