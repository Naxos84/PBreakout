package de.hpi.javaide.breakout.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {

	private ResourceManager() {

	}

	/**
	 * Returns a translated string using the default locale.
	 *
	 * @param key
	 *            the resource key of which to return the translated string
	 * @return a translated string
	 */
	public static String getString(final String key) {
		return getString(key, null);
	}

	/**
	 * Returns a translated string using the given locale
	 * 
	 * @param key
	 *            the resource key of which to return the translated string
	 * @param locale
	 *            the locale to use translation
	 * @return a translated string
	 */
	public static String getString(final String key, final Locale locale) {
		return ResourceBundle.getBundle("Texts", locale == null ? Locale.getDefault() : locale).getString(key);
	}
}
