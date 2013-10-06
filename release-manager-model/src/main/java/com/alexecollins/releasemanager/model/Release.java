package com.alexecollins.releasemanager.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
	@NotNull
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy
	@JoinTable(name = "release_component", joinColumns = @JoinColumn(name = "release_id"), inverseJoinColumns = @JoinColumn(name = "component_id"))
	@NotNull
	private Set<Component> components = new HashSet<>();
	@NotNull
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

	public void removeComponent(Component component) {
		components.remove(component);
	}

	public Set<Component> getComponents() {
		return components;
	}

	public Date getCreated() {
		return created;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Release release = (Release) o;

		if (!name.equals(release.name)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
