package de.hpi.javaide.breakout.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Basic Logging Implementation
 *
 * @author Naxos84
 *
 */
public class Log {

	private static final Logger l = Logger.getLogger(Log.class.getPackage().getName());

	private Log() {
		// prevent instantiation
	}

	public static void logError(final String message){
		if (l.isLoggable(Level.SEVERE)) {
			l.log(Level.SEVERE, message);
		}
	}

	public static void logWarning(final String message) {
		if (l.isLoggable(Level.WARNING)) {
			l.log(Level.WARNING, message);
		}
	}

	public static void logInfo(final String message) {
		if (l.isLoggable(Level.INFO)) {
			l.log(Level.INFO, message);
		}
	}

	public static void logDebug(final String message) {
		if (l.isLoggable(Level.FINE)) {
			l.log(Level.FINE, message);
		}
	}

	public static void logTrace(final String message) {
		if (l.isLoggable(Level.FINEST)) {
			l.log(Level.FINEST, message);
		}
	}

}
