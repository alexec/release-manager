package com.alexecollins.releasemanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity
public class Release {
	@Id @GeneratedValue
	Long id;
	@Column(length = 64, nullable = false)
	String name;
}
