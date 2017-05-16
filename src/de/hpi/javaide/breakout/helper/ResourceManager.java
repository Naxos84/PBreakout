package de.hpi.javaide.breakout.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {

	private ResourceManager() {

	}

	public static String getString(final String key) {
		return getString(key, null);
	}

	public static String getString(final String key, final Locale locale) {
		return ResourceBundle.getBundle("Texts", locale == null ? Locale.getDefault() : locale).getString(key);
	}
}
