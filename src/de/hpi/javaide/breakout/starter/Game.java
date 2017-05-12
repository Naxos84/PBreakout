package de.hpi.javaide.breakout.starter;


import org.apache.log4j.Logger;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.screens.Screen;
import de.hpi.javaide.breakout.screens.ScreenManager;
import processing.core.PApplet;
import processing.event.MouseEvent;

@SuppressWarnings("serial")
public class Game extends PApplet {

	private static final Logger LOGGER = Logger.getLogger(Game.class.getPackage().getName());

	// Setup the game
	@Override
	public void setup() {
		size(GameConstants.SCREEN_X, GameConstants.SCREEN_Y);
		background(0);
		frameRate(30);
		Font.init(this);
		ScreenManager.setScreen(this, Screen.START);
	}

	// Update and draw everything in the game
	@Override
	public void draw() {
		background(0);
		ScreenManager.getCurrentScreen().update();
		ScreenManager.getCurrentScreen().display();
	}

	// Interact with the mouse
	@Override
	public void mouseMoved() {

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

	// Interact with the keyboard
	@Override
	public void keyPressed() {
		ScreenManager.getCurrentScreen().handleKeyPressed(keyCode);
		LOGGER.debug("key pressed:" + keyCode + "/");
	}

	@Override
	public void keyReleased() {

	}

	public void increaseScore(final int i) {
		ScreenManager.getCurrentScreen().increaseScore(i);
	}

}
