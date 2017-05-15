package de.hpi.javaide.breakout.screens;

import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.Ball;
import de.hpi.javaide.breakout.elements.BallDepot;
import de.hpi.javaide.breakout.elements.CollisionLogic;
import de.hpi.javaide.breakout.elements.Paddle;
import de.hpi.javaide.breakout.elements.Wall;
import de.hpi.javaide.breakout.elements.ui.Score;
import de.hpi.javaide.breakout.elements.ui.Timer;
import de.hpi.javaide.breakout.starter.Game;

/**
 * The Screen can be in three states, either the StartScreen, the GameScreen, or
 * the EndScreen. The game logic takes care, which of those is the currently
 * active screen.
 *
 * @author Ralf Teusner and Tom Staubitz
 *
 */
public class GameScreen extends Screen {

	private static final Logger LOGGER = Logger.getLogger(Game.class.getPackage().getName());

	/**
	 * This variable is needed for the Singleton pattern
	 */
	private static GameScreen instance;

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
	private UIObject score;
	private UIObject timer;

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
		wall = new Wall(gameInstance, 6, 5);
		score = new Score(gameInstance);
		timer = new Timer(gameInstance);
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
		timer.update(null);
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
		timer.display();
	}

	/**
	 * Take care of keyboard input
	 */
	@Override
	public void handleKeyPressed(final int key) {
		switch (key) {
		case KeyEvent.VK_ENTER:
			spawnBall();
			break;
		case KeyEvent.VK_LEFT:
			movePaddleLeft();
			break;
		case KeyEvent.VK_RIGHT:
			movePaddleRight();
			break;
		default:
			break;
		}
	}

	private void movePaddleLeft() {
		paddle.moveRight();
	}

	private void movePaddleRight() {
		paddle.moveRight();

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
	public void increaseScore(final int amount) {
		// cheap trick to convert an int to a String
		// (Hint: the update() Method expects an input argument of type String)
		score.update(String.valueOf(amount));
	}

	@Override
	public void handleMouseClick(final int mouseX, final int mouseY) {
		// do nothing
	}

	public static void destroyCurrentBall(final Game game) {
		LOGGER.debug("Destroying ball.");
		getInstance(game).currentBall = null;
	}
}
