package de.hpi.javaide.breakout.starter;


import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.logging.Log;
import de.hpi.javaide.breakout.screens.Screen;
import de.hpi.javaide.breakout.screens.ScreenManager;
import processing.core.PApplet;
import processing.event.MouseEvent;

@SuppressWarnings("serial")
public class Game extends PApplet {



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
			Log.logTrace("Left Mouse Button clicked");
			ScreenManager.getCurrentScreen().handleMouseClick(mouseX, mouseY);
		}
	}

	// Interact with the keyboard
	@Override
	public void keyPressed() {
		switch (key) {
		case RETURN:
		case ENTER:
			ScreenManager.getCurrentScreen().handleKeyPressed(Screen.KEY_ENTER);
			break;
		default:
			Log.logDebug("key pressed:" + key + "/");
			break;
		}
	}

	@Override
	public void keyReleased() {

	}

	public void increaseScore(final int i) {
		ScreenManager.getCurrentScreen().increaseScore(i);
	}
}
