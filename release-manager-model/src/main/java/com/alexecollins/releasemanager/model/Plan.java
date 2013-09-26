package com.alexecollins.releasemanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity
public class Plan {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 64)
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
