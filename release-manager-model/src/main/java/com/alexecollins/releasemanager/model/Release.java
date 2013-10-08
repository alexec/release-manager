package com.alexecollins.releasemanager.model;


import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.*;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class Release {
	private String id;
	@Indexed(unique = true)
	private String name;
	private Map<User,SignOff> signOffs = new HashMap<>();
	private Set<Component> components = new HashSet<>();
	private Date created = new Date();
}
