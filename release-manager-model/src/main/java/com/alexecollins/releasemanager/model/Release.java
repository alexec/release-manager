package com.alexecollins.releasemanager.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Entity
public class Release {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length = 64, nullable = false, unique = true)
	private String name;
	@ManyToMany
	@OrderBy
	@JoinTable(name = "release_component", joinColumns = { @JoinColumn(name = "release_id") })
	private Set<Component> components = new HashSet<>();
	@Column(nullable = false)
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

	public boolean addComponent(Component component) {
		return components.add(component);
	}

	public Set<Component> getComponents() {
		return components;
	}
}
