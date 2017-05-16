package de.hpi.javaide.breakout.starter;

import org.apache.log4j.PropertyConfigurator;

import processing.core.PApplet;

public class Main {

	private Main() {

	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		PropertyConfigurator.configureAndWatch("log4j.properties", 60 * 1000L);
		PApplet.main(new String[] { "--present", "de.hpi.javaide.breakout.starter.Game" });
	}

}
