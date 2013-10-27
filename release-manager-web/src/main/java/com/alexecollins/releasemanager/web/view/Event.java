package com.alexecollins.releasemanager.web.view;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class Event implements Comparable<Event> {
	private final String id;
	private final String title;
	private final String url;
	@JsonProperty("class")
	private final String clazz = "event-info";
	private final long start;
	private final long end;

	@Override
	public int compareTo(Event o) {
		return start < o.start ? -1 : 1;
	}
}
