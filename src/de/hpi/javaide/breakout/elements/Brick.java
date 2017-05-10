package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;

//TODO wichtige Attribute: Größe, Position, Abstand der Bricks untereinander
//     Irgendwie muss ich herausbekommen ob der Stein noch existiert oder nicht.
public class Brick extends Rectangular {

	public Brick(final Game game, final Point position, final Dimension dimension) {
		super(game, position, dimension);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

}
