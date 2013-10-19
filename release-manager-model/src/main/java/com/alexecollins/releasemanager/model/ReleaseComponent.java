package com.alexecollins.releasemanager.model;

import lombok.Data;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class ReleaseComponent {
	private final Component component;
	private final String version;
}
