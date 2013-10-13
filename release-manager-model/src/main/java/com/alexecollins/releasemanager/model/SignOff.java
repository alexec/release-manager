package com.alexecollins.releasemanager.model;

import lombok.Data;

import java.util.Date;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class SignOff {
	private SignOffStatus status = SignOffStatus.REQUESTED;
	private Date created = new Date();
	private Date modified = created;

	// TODO @PreUpdate
	public void updateModified() {
		modified = new Date();
	}

}
