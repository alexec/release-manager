package com.alexecollins.releasemanager.model;

import lombok.Data;

import java.util.Date;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class AuditLog {
	private String id;
	private String username;
	private String message;
	private Date created = new Date();
}
