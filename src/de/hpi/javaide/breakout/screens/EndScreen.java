package de.hpi.javaide.breakout.screens;

import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.ui.Info;
import de.hpi.javaide.breakout.starter.Game;

/**
 * The Screen can be in three states, either the StartScreen, the GameScreen, or
 * the EndScreen. The game logic takes care, which of those is the currently
 * active screen.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class EndScreen extends Screen {

	private static final Logger LOGGER = Logger.getLogger(EndScreen.class.getPackage().getName());

	/**
	 * This variable is needed for the Singleton pattern
	 */
	private static Screen instance;
	/**
	 * A UIObject to display some information
	 */
	private UIObject infoBox;

	private EndScreen(final Game game) {
		super(game);
	}

	/**
	 * EndScreen implements a "Lazy Instantiation" of the Singleton Design
	 * Patterns (Gang of Four) This approach is not "Thread safe", but is
	 * sufficient for our current needs.
	 *
	 * Please, be aware that Singletons need to be handled with care. There are
	 * various ways to implement them, all have there pros and cons. In his
	 * book, Effective Java, Joshua Bloch recommends to create Singletons using
	 * an enum, which is a language concept that we have not discussed here so
	 * far. For those of you who want to go further we suggest to follow this
	 * recommendation at some point of time.
	 *
	 * @return the EndScreen
	 */
	public static Screen getInstance(final Game game) {
		if (instance == null) {
			instance = new EndScreen(game);
		} else {
			instance.init();
		}
		return instance;
	}

	/*
	 * Currently, we are within the EndScreen. The only action that is required
	 * here is to restart the game (switch back to the start screen.) Of course
	 * you can add any additional features if you want to.
	 */
	@Override
	public void init() {
		gameInstance.background(0);
		String info = "Game over!!!\n";
		info += "Press Enter to restart!\n";
		infoBox = new Info(gameInstance, info);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void display() {
		LOGGER.info("Game over: Hit enter to restart.");
		infoBox.display();
	}

	@Override
	public void handleKeyPressed(final int key) {
		switch (key) {
		case KeyEvent.VK_ENTER:
			LOGGER.info("restart..");
			ScreenManager.setScreen(gameInstance, Screen.START);
			break;
		case KeyEvent.VK_ESCAPE:
			LOGGER.info("Quitting game...");
			gameInstance.exit();
			break;
		default:
			break;
		}
	}

	@Override
	public void handleMouseClick(final int mouseX, final int mouseY) {
		// do nothing
	}
}
