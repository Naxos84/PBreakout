package de.hpi.javaide.breakout.elements.ui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import de.hpi.javaide.breakout.basics.Color;
import de.hpi.javaide.breakout.basics.GameFont;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.helper.ResourceManager;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PFont;

public class Menu extends UIObject<List<String>> {

	private static final Color SELECTED = new Color(0, 255, 0);
	private static final Color UNSELECTED = new Color(255, 255, 255);

	private List<String> keys;
	private int menuSelection;
	private final PFont font = GameFont.getFont16();

	public Menu(final Game game) {
		super(game);
		keys = new ArrayList<>();
	}

	@Override
	public void display() {
		for (int i = 0; i < keys.size(); i++) {
			if (i == menuSelection) {
				game.fill(SELECTED.getRed(), SELECTED.getGreen(), SELECTED.getBlue());
			} else {
				game.fill(UNSELECTED.getRed(), UNSELECTED.getGreen(), UNSELECTED.getBlue());
			}
			final String text = ResourceManager.getString(keys.get(i));
			game.text(text, GameConstants.SCREEN_X / 2f - GameFont.getTextWidth(text, font), 200 + i * GameFont.getTextHeight(text, font));
			game.fill(UNSELECTED.getRed(), UNSELECTED.getGreen(), UNSELECTED.getBlue());
		}
	}

	@Override
	public void update(final List<String> input) {
		keys = input;

	}

	public void onKeyPress(final int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:
			decreaseMenuSelection();
			break;
		case KeyEvent.VK_DOWN:
			increaseMenuSelection();
			break;
		default:
			break;
		}
	}

	public int getMenuSelection() {
		return menuSelection;
	}

	private void increaseMenuSelection() {
		menuSelection++;
		if (menuSelection >= keys.size()) {
			menuSelection = 0;
		}
	}

	private void decreaseMenuSelection() {
		menuSelection--;
		if (menuSelection < 0) {
			menuSelection = keys.size() - 1;
		}
	}

}
