package de.hpi.javaide.breakout.starter;

import org.apache.log4j.PropertyConfigurator;

//TODO => Fehlermeldungen beseitigen
// 1. Lokale core.jar verlinken
// 2. Fehlende Methoden ergänzen. Tipp: eclipse macht hier das meiste für euch. Ihr braucht nahezu keinen Code zu schreiben.
// Der Vorteil hier: eclipse erstellt euch auch gleich einen Großteil eurer TODOs (Window->Show View->Tasks)

import processing.core.PApplet;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		PropertyConfigurator.configureAndWatch("log4j.properties", 60 * 1000);
		PApplet.main(new String[]{"--present", "de.hpi.javaide.breakout.starter.Game"});
	}

}
