package de.hpi.javaide.breakout.screens;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.ui.Button;
import de.hpi.javaide.breakout.elements.ui.Info;
import de.hpi.javaide.breakout.helper.PointHelper;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

/**
 * The Screen can be in three states, either the StartScreen, the GameScreen, or the EndScreen.
 * The game logic takes care, which of those is the currently active screen.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class StartScreen extends Screen {

	private static final Logger LOGGER = Logger.getLogger(StartScreen.class.getPackage().getName());

	/**
	 * This variable is needed for the Singleton pattern
	 */
	private static Screen instance;
	private UIObject infoBox;
	private Button button;

	private final List<String> menuEntries;
	private int menuSelection = 0;

	private StartScreen(final Game game){
		super(game);
		menuEntries = new ArrayList<>();
		menuEntries.add("Spiel starten");
		menuEntries.add("Optionen");
		menuEntries.add("HighScore");
		menuEntries.add("Spiel beenden");
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
		final String info = "Nutze die Pfeiltasten um im Menü zu navigieren.\nMit Enter bestätigst du deine Auswahl.";
		infoBox = new Info(gameInstance, info);
		infoBox.setPosition(new Point(10, 24));
		infoBox.display();

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void display() {
		LOGGER.info("Hit enter to start");
		infoBox.display();
		drawMenu();
	}

	private void drawMenu() {
		for (int i = 0; i < menuEntries.size(); i++) {
			final String menuEntry = menuEntries.get(i);
			if (i == menuSelection) {
				gameInstance.fill(0, 255, 0);
			} else {
				gameInstance.fill(255);
			}
			gameInstance.text(menuEntry, GameConstants.SCREEN_X / 2f, 200 + 30 * i);
		}
	}

	@Override
	public void handleKeyPressed(final int key) {
		switch (key) {
		case KeyEvent.VK_ENTER:
			LOGGER.debug("starting..");
			ScreenManager.setScreen(gameInstance, Screen.GAME);
			break;
		case KeyEvent.VK_ESCAPE:
			gameInstance.exit();
			break;
		case KeyEvent.VK_UP:
			LOGGER.debug("Button Up");
			decreaseMenuSelection();
			break;
		case KeyEvent.VK_DOWN:
			LOGGER.debug("Button Down");
			increaseMenuSelection();
			break;
		default: break;
		}
	}

	private void increaseMenuSelection() {
		menuSelection++;
		if (menuSelection >= menuEntries.size()) {
			menuSelection = 0;
		}
	}

	private void decreaseMenuSelection() {
		menuSelection--;
		if (menuSelection < 0) {
			menuSelection = menuEntries.size() - 1;
		}
	}

	@Override
	public void handleMouseClick(final int mouseX, final int mouseY) {
		if (PointHelper.isInside(new Point(mouseX, mouseY),
				new Rectangle(button.getPosition().x, button.getPosition().y, button.getWidth(), button.getHeight()))) {
			LOGGER.info("Button clicked");
		}

	}

}
