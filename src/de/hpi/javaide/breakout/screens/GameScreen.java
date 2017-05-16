package de.hpi.javaide.breakout.screens;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.Ball;
import de.hpi.javaide.breakout.elements.BallDepot;
import de.hpi.javaide.breakout.elements.CollisionLogic;
import de.hpi.javaide.breakout.elements.Paddle;
import de.hpi.javaide.breakout.elements.Wall;
import de.hpi.javaide.breakout.elements.ui.Info;
import de.hpi.javaide.breakout.elements.ui.Score;
import de.hpi.javaide.breakout.interfaces.Pauseable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.data.XML;

/**
 * The Screen can be in three states, either the StartScreen, the GameScreen, or
 * the EndScreen. The game logic takes care, which of those is the currently
 * active screen.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class GameScreen extends Screen implements Pauseable {

	private static final Logger LOGGER = Logger.getLogger(Game.class.getPackage().getName());

	/**
	 * This variable is needed for the Singleton pattern
	 */
	private static GameScreen instance;
	private boolean isPaused;

	/**
	 * As we are in the actual game now, we need all the elements that are part
	 * of the game. Such as the BallDepot (containing the Balls), the
	 * currentBall (the BallDepot dispenses the one Ball after the other to this
	 * variable), the Paddle, and the Wall (containing all the Bricks)
	 */
	private BallDepot ballDepot;
	private Ball currentBall;

	private Paddle paddle;
	private Wall wall;

	/**
	 * Plus some UIObjects to display the score and the timer
	 */
	private UIObject<String> score;
	// private UIObject timer;
	private Info pauseInfo;

	private GameScreen(final Game game) {
		super(game);
	}

	/**
	 * GameScreen implements a "Lazy Instantiation" of the Singleton Design
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
	public static GameScreen getInstance(final Game game) {
		if (instance == null) {
			instance = new GameScreen(game);
		}
		return instance;
	}

	/*
	 * Hint for the following error messages: rather consider to create the
	 * necessary constructors than to remove the arguments.
	 *
	 * (non-Javadoc)
	 *
	 * @see de.hpi.javaide.breakout.Initializable#init()
	 */
	@Override
	public void init() {
		ballDepot = new BallDepot(gameInstance);
		paddle = new Paddle(gameInstance);
		final XML config = gameInstance.loadXML("config.xml");
		final XML wallConfig = config.getChild("wallConfiguration");
		wall = new Wall(gameInstance, wallConfig);
		score = new Score(gameInstance);
		// timer = new Timer(gameInstance);
		pauseInfo = new Info(gameInstance, "Game is paused.");
		pauseInfo.setPosition(new Point2D.Float(GameConstants.SCREEN_X / 2f - pauseInfo.getFontWidth() / 2, (float) GameConstants.SCREEN_Y / 2));
		gameInstance.loop();
	}

	/**
	 * Update the GameScreen x times per second
	 */
	@Override
	public void update() {
		if (currentBall != null) {
			currentBall.move();
			CollisionLogic.checkCollision(gameInstance, currentBall, paddle, wall);
		}
		// timer.update(null);
	}

	/**
	 * Display the updated state of the Game Screen x times per second Just
	 * delegate to the display Methods of the Objects to be displayed.
	 */
	@Override
	public void display() {

		wall.display();
		ballDepot.display();
		if (currentBall != null) {
			currentBall.display();
		} else {
			// there is no more Ball in the game and the depot is empty.
			if (ballDepot.isEmpty()) {
				ScreenManager.setScreen(gameInstance, Screen.END);
			}
		}
		paddle.display();
		score.display();
		// timer.display();
	}

	/**
	 * Take care of keyboard input
	 */
	@Override
	public void handleKeyPressed(final int key) {
		if (key == KeyEvent.VK_ENTER) {
			spawnBall();
		}
	}

	private void spawnBall() {
		if (currentBall == null) {
			LOGGER.debug("Spawning new ball");
			currentBall = ballDepot.dispense();
		} else {
			LOGGER.info("Cannot spawn new ball, cause there is already an existing ball");
		}
	}

	/**
	 * Take care of Mouse input
	 */
	@Override
	public void handleMouseDragged() {
		paddle.move();
	}

	@Override
	public void handleMouseClick(final int mouseX, final int mouseY) {
		// do nothing
	}

	public static void destroyCurrentBall(final Game game) {
		LOGGER.debug("Destroying ball.");
		getInstance(game).currentBall = null;
	}

	@Override
	public void pause() {
		isPaused = true;
		pauseInfo.display();
		gameInstance.noLoop();

	}

	@Override
	public void unpause() {
		isPaused = false;
		gameInstance.loop();
	}

	@Override
	public boolean isPaused() {
		return isPaused;
	}
}
