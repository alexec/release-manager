package com.alexecollins.releasemanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity(name = "usr")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String email;
}
