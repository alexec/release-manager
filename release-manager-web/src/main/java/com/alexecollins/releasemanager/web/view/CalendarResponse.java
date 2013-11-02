package com.alexecollins.releasemanager.web.view;

import lombok.Data;

import java.util.List;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class CalendarResponse {
	private final int success = 1;
	private final List<Event> result;
}
