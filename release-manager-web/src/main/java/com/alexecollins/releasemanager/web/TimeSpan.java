package com.alexecollins.releasemanager.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public final class TimeSpan {

	private static final Pattern PATTERN = Pattern.compile("(?:([0-9]+) ?h[^ ]*)? ?(?:([0-9]+) ?m[^ ]*)?");

	private TimeSpan() {
	}

	public static long parse(final String text) {
		final Matcher m = PATTERN.matcher(text);
		if (!m.find()) {
			throw new IllegalArgumentException(text + " must match " + PATTERN);
		}

		if (m.group().isEmpty()) {
			throw new IllegalArgumentException(text + " is invalid time span, e.g. '2 hours' or '3h 24m'");
		}

		final int hours = m.group(1) == null ? 0 : Integer.parseInt(m.group(1));
		final int mins = m.group(2) == null  ? 0 : Integer.parseInt(m.group(2));

		return (hours * 60 + mins) * 60 *1000;
	}

	public static String format(long span) {
		final long hours = span / 1000 / 60 / 60;
		final long mins = span / 1000 / 60 % 60;

		if (hours > 0) {
			if (mins > 0) {
				return hours + "h " + mins + "m";
			} else {
				return hours + "h";
			}
		} else {
			return mins + "m";
		}
	}
}
