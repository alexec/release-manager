package com.alexecollins.releasemanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity(name = "usr")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private String email;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (!email.equals(user.email)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}
}
