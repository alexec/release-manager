package com.alexecollins.releasemanager.web.view;

import com.alexecollins.releasemanager.model.Component;
import lombok.Data;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class ReleaseComponentView {
	private final Component component;
	private final String version;
}
