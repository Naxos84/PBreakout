package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.interfaces.Displayable;
import de.hpi.javaide.breakout.interfaces.Initializable;
import de.hpi.javaide.breakout.interfaces.Updateable;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Provides a common interface for all Screen classes, so that we can easily switch between the states of the Screen.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public abstract class Screen implements Initializable, Displayable, Updateable
{
	public static final String START = "start";
	public static final String GAME = "game";
	public static final String END = "end";

	public static final String KEY_ENTER = "Enter key pressed";
	public static final String KEY_SPACE = "Space key pressed";

	protected final Game gameInstance;

	protected Screen(final Game game) {
		gameInstance = game;
	}

	public abstract void handleKeyPressed(String key);

	public abstract void handleMouseDragged();

	public abstract void increaseScore(int amount);
}
