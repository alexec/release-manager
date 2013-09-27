package com.alexecollins.releasemanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity
public class Component implements Comparable<Component> {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Date created = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Component o) {
		return name.compareTo(o.name);
	}
}
