package com.alexecollins.releasemanager.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity
public class SignOff {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Release release;
	@ManyToOne
	private User user;
	@Enumerated(EnumType.STRING)
	private SignOffStatus status;
	private Date created = new Date();
	private Date modified = created;

	@PreUpdate
	public void updateModified() {
		modified = new Date();
	}
}
