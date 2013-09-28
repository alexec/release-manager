package com.alexecollins.releasemanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity
public class Plan {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String value;
	@NotNull
	private Date created = new Date();

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
