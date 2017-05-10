package de.hpi.javaide.breakout.elements;

import java.util.ArrayList;
import java.util.Iterator;

import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Blueprint for the Wall
 * 
 * @author Ralf Teusner and Tom Staubitz
 *
 */
//TODO die Wall wird aus Bricks gebaut.
public class Wall implements Displayable, Iterable<Brick> {
	
	/**
	 * Datastructure to keep the Bricks
	 */
	private ArrayList<Brick> wall;


	@Override
	public Iterator<Brick> iterator() {
		return wall.iterator();
	}
	/**
	 * Build the wall by putting the single bricks into their position
	 * Hint: You might want to use one or two for-loops
	 * 
	 * @param game
	 * @param columns
	 * @param rows
	 */
	private void buildWall(Game game, int columns, int rows) {

	}
}
