package de.hpi.javaide.breakout.helper;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class TestResourceManager {

	private static final String TEST_KEY = "KEY_TEST";

	@Test
	public void testGetStringString() {
		System.out.println("NO LOCALE: " + ResourceManager.getString(TEST_KEY));
		System.out.println("GERMANY: " + ResourceManager.getString(TEST_KEY, Locale.GERMANY));
		System.out.println("US: " + ResourceManager.getString(TEST_KEY, Locale.US));
		System.out.println("UK: " + ResourceManager.getString(TEST_KEY, Locale.UK));
		System.out.println("FALLBACK: " + ResourceManager.getString(TEST_KEY, Locale.ITALY));
	}

	@Test
	public void testGetStringStringLocale() {
		assertEquals("de_TestKey", ResourceManager.getString(TEST_KEY, Locale.GERMANY));
		assertEquals("en_TestKey", ResourceManager.getString(TEST_KEY, Locale.US));
	}

}
