package com.alexecollins.releasemanager.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity
@Table(name = "sign_off")
public class SignOff {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@NotNull
	private Release release;
	@NotNull
	@ManyToOne @JoinColumn(name = "usr_id")
	private User user;
	@Enumerated(EnumType.STRING)
	@NotNull
	private SignOffStatus status = SignOffStatus.REQUESTED;
	@NotNull
	private Date created = new Date();
	@NotNull
	private Date modified = created;

	@PreUpdate
	public void updateModified() {
		modified = new Date();
	}

	public Integer getId() {
		return id;
	}

	public Release getRelease() {
		return release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SignOffStatus getStatus() {
		return status;
	}

	public void setStatus(SignOffStatus status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SignOff signOff = (SignOff) o;

		if (!release.equals(signOff.release)) return false;
		if (!user.equals(signOff.user)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = release.hashCode();
		result = 31 * result + user.hashCode();
		return result;
	}
}
