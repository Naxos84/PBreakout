package de.hpi.javaide.breakout.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
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
	static {
		final Handler h = new ConsoleHandler();
		h.setLevel(Level.FINEST);
		l.setLevel(Level.FINEST);
		l.addHandler(h);
	}

	private Log() {
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
