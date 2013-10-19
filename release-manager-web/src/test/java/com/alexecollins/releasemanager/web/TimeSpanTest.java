package com.alexecollins.releasemanager.web;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class TimeSpanTest {

	@Test
	public void hoursOnly() throws Exception {

		assertEquals(2l * 60 * 60 * 1000, TimeSpan.parse("2 hours"));
		assertEquals(14l * 60 * 60 * 1000, TimeSpan.parse("14 h"));

	}

	@Test
	public void minsOnly() throws Exception {

		assertEquals(3l * 60 * 1000, TimeSpan.parse("3 mins"));
		assertEquals(7l * 60 * 1000, TimeSpan.parse("7 mins"));
	}

	@Test
	public void mixture() throws Exception {

		assertEquals(((2l * 60 + 11) * 60) * 1000, TimeSpan.parse("2 hours 11mins"));
		assertEquals(((3l * 60 + 17) * 60) * 1000, TimeSpan.parse("3hrs 17 mi"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void neither() throws Exception {
		TimeSpan.parse("aoeuaoe");
	}

	@Test
	public void formatHours() throws Exception {
		assertEquals("2h", TimeSpan.format(2 * 60 * 60 * 1000));
	}

	@Test
	public void formatMins() throws Exception {
		assertEquals("3m", TimeSpan.format(3 * 60 * 1000));
	}

	@Test
	public void formatHoursAndMins() throws Exception {
		assertEquals("2h 3m", TimeSpan.format((2 * 60  + 3)* 60 * 1000));
	}
}
