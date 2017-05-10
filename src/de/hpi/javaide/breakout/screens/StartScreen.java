package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.ui.Info;
import de.hpi.javaide.breakout.logging.Log;
import de.hpi.javaide.breakout.starter.Game;

/**
 * The Screen can be in three states, either the StartScreen, the GameScreen, or the EndScreen.
 * The game logic takes care, which of those is the currently active screen.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class StartScreen extends Screen {

	/**
	 * This variable is needed for the Singleton pattern
	 */
	private static Screen instance;
	private UIObject infoBox;

	private StartScreen(final Game game){
		super(game);
		init();
	}

	/**
	 * StartScreen implements a "Lazy Instantiation" of the Singleton Design Patterns (Gang of Four)
	 * This approach is not "Thread safe", but is sufficient for our current needs.
	 *
	 * Please, be aware that Singletons need to be handled with care.
	 * There are various ways to implement them, all have there pros and cons.
	 * In his book, Effective Java, Joshua Bloch recommends to create Singletons using an enum,
	 * which is a language concept that we have not discussed here so far.
	 * For those of you who want to go further we suggest to follow this recommendation at some point of time.
	 *
	 * @return the StartScreen
	 */
	public static Screen getInstance(final Game game){
		if(instance == null){
			instance = new StartScreen(game);
		} else {
			instance.init();
		}
		return instance;
	}

	/*
	 * The user should be able to start the game here (by switching to the GameScreen.)
	 *
	 * (non-Javadoc)
	 * @see de.hpi.javaide.breakout.screens.Screen#handleKeyPressed(java.lang.String)
	 */
	@Override
	public void init() {
		gameInstance.noLoop();
		gameInstance.background(0);
		String info = "Press Enter to start!\n";
		info += "Press Enter to launch the balls\n";
		infoBox = new Info(gameInstance, info);
		infoBox.display();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void display() {
		Log.logInfo("Hit enter to start");
		infoBox.display();
	}

	@Override
	public void handleKeyPressed(final String key) {
		switch (key) {
		case Screen.KEY_ENTER:
			Log.logDebug("starting..");
			ScreenManager.setScreen(gameInstance, Screen.GAME);
			break;
		default: break;
		}
	}

	@Override
	public void handleMouseDragged() {
		// Im StartScreen ist keine Interaktion mit der Maus notwendig.
	}

	@Override
	public void increaseScore(final int i) {
		// Im StartScreen gibt es keinen Counter.
	}
}
