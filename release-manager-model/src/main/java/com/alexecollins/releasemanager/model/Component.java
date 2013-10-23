package com.alexecollins.releasemanager.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class Component  {
	private String id;
	@Indexed(unique = true)
	private String name;
	private Date created = new Date();
    private ArtifactRepository artifactRepository;
}
