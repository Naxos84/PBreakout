package de.hpi.javaide.breakout.starter;

import java.awt.event.KeyEvent;
import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.GameFont;
import de.hpi.javaide.breakout.interfaces.Pauseable;
import de.hpi.javaide.breakout.screens.Screen;
import de.hpi.javaide.breakout.screens.ScreenManager;
import processing.core.PApplet;
import processing.event.MouseEvent;

@SuppressWarnings("serial")
public class Game extends PApplet {

	private static final Logger LOGGER = Logger.getLogger(Game.class.getPackage().getName());
	private static int score;

	// Setup the game
	@Override
	public void setup() {
		size(GameConstants.SCREEN_X, GameConstants.SCREEN_Y);
		background(0);
		frameRate(30);
		GameFont.init(this);
		ScreenManager.setScreen(this, Screen.START);
	}

	// Update and draw everything in the game
	@Override
	public void draw() {
		background(GameConstants.BACKGROUNDFILL.getRed(), GameConstants.BACKGROUNDFILL.getGreen(), GameConstants.BACKGROUNDFILL.getBlue());
		ScreenManager.getCurrentScreen().display();
		ScreenManager.getCurrentScreen().update();
	}

	@Override
	public void mouseDragged() {
		ScreenManager.getCurrentScreen().handleMouseDragged();
	}

	@Override
	public void mouseClicked(final MouseEvent event) {
		if (mouseButton == 37) {
			LOGGER.trace("Left Mouse Button clicked");
			ScreenManager.getCurrentScreen().handleMouseClick(mouseX, mouseY);
		}
	}

	@Override
	public void keyPressed() {
		final Screen screen = ScreenManager.getCurrentScreen();
		screen.handleKeyPressed(keyCode);
		if (keyCode == KeyEvent.VK_ESCAPE) {
			if (screen instanceof Pauseable) {
				final Pauseable pauseable = (Pauseable) screen;
				if (pauseable.isPaused()) {
					pauseable.unpause();
				} else {
					pauseable.pause();
				}
			}
			trapEscKey();
		} else if (keyCode == KeyEvent.VK_Q) {
			// pressing "Q" quits the game
			exit();
		}

	}

	private void trapEscKey() {
		// trap ESC so it doesn't quit the game
		keyCode = 0;
		key = '\0';
	}

	public static void addToScore(final int score) {
		Game.score += score;
	}

	public static int getScore() {
		return Game.score;
	}

	public java.util.List<HighScore> readHighScore() {
		final java.util.List<HighScore> result = new java.util.ArrayList<>();
		final String[] highScores = loadStrings("highScore.txt");
		for (final String highScoreEntry : highScores) {
			final String[] splitEntry = highScoreEntry.split("=");
			if (splitEntry.length != 2) {
				//Eintrag ist nicht valide
			} else {
				final String name = splitEntry[0];
				final String score = splitEntry[1];
				result.add(new HighScore(name, score));
			}
		}
		return result;
	}

	public void writeHighScore(final java.util.List<HighScore> highScores) {
		final String[] hScores = new String[highScores.size()]; //Erstellt ein Array welches genug Platz hat
		for (int i = 0; i < highScores.size(); i++) {
			final String name = highScores.get(i).name;
			final String score = highScores.get(i).score;
			hScores[i] = name + "=" + score; //baut den Eintrag zusammen
		}
		saveStrings("highScore.txt", hScores);
	}

	public class HighScore {

		//Diese beiden Variablen sind public, damit ich keine getter/setter schreiben muss
		public String	name;
		public String	score;

		public HighScore(final String name, final String score) {
			this.name = name;
			this.score = score;
		}
	}

}
