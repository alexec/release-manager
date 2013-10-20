package com.alexecollins.releasemanager.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Index;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class Watch {

	private String id;
	private String user;
	private String subject;
}
