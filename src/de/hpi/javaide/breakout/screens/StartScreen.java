package de.hpi.javaide.breakout.screens;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.ui.Info;
import de.hpi.javaide.breakout.elements.ui.Menu;
import de.hpi.javaide.breakout.helper.ResourceManager;
import de.hpi.javaide.breakout.starter.Game;

/**
 * The Screen can be in three states, either the StartScreen, the GameScreen, or
 * the EndScreen. The game logic takes care, which of those is the currently
 * active screen.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class StartScreen extends Screen {

	private static final Logger LOGGER = Logger.getLogger(StartScreen.class.getPackage().getName());
	private static final String START_TEXT = "START_GAME";
	private static final int START_SELECTION = 0;
	private static final String OPTIONS_TEXT = "OPTIONS";
	private static final int OPTIONS_SELECTION = 1;
	private static final String QUIT_TEXT = "QUIT_GAME";
	private static final int QUIT_SELECTION = 2;

	private static final String OPTIONS_NOT_IMPLEMENTED_KEY = "OPTIONS_NOT_IMPLEMENTED";

	/**
	 * This variable is needed for the Singleton pattern
	 */
	private static Screen instance;

	private UIObject<String> infoBox;

	private final Menu menu;

	private final List<String> menuEntries;

	private StartScreen(final Game game) {
		super(game);
		menuEntries = new ArrayList<>();
		menuEntries.add(START_TEXT);
		menuEntries.add(OPTIONS_TEXT);
		menuEntries.add(QUIT_TEXT);
		menu = new Menu(game);
		menu.update(menuEntries);
		init();
	}

	/**
	 * StartScreen implements a "Lazy Instantiation" of the Singleton Design
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
	 * @return the StartScreen
	 */
	public static Screen getInstance(final Game game) {
		if (instance == null) {
			instance = new StartScreen(game);
		} else {
			instance.init();
		}
		return instance;
	}

	/*
	 * The user should be able to start the game here (by switching to the
	 * GameScreen.)
	 *
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hpi.javaide.breakout.screens.Screen#handleKeyPressed(java.lang.String)
	 */
	@Override
	public void init() {
		gameInstance.background(0);
		final String info = "Nutze die Pfeiltasten um im Menü zu navigieren.\nMit Enter bestätigst du deine Auswahl.";
		infoBox = new Info(gameInstance, info);
		infoBox.setPosition(new Point2D.Float(10, 24));
		infoBox.display();

	}

	@Override
	public void update() {
		// nothing to update
	}

	@Override
	public void display() {
		infoBox.display();
		drawMenu();
	}

	private void drawMenu() {
		menu.display();
	}

	@Override
	public void handleKeyPressed(final int key) {
		menu.onKeyPress(key);
		if (key == KeyEvent.VK_ENTER) {
			switch (menu.getMenuSelection()) {
			case START_SELECTION:
				startGame();
				break;
			case OPTIONS_SELECTION:
				showOptions();
				break;
			case QUIT_SELECTION:
				exitGame();
				break;
			default:
				break;
			}
		}
	}

	private void exitGame() {
		gameInstance.exit();
	}

	private void showOptions() {
		LOGGER.info(ResourceManager.getString(OPTIONS_NOT_IMPLEMENTED_KEY));
	}

	private void startGame() {
		LOGGER.debug("starting..");
		ScreenManager.setScreen(gameInstance, Screen.GAME);
	}

	@Override
	public void handleMouseClick(final int mouseX, final int mouseY) {
		// not needed here

	}

}
