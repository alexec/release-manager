package com.alexecollins.releasemanager.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Data
public class User {
	private String id;
	@Indexed(unique = true)
	private String email;
}
