package com.alexecollins.releasemanager.model;


import com.mdimension.jchronic.Chronic;
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
	private String desc = "";
	/** user.id -> SignOff */
	private Map<String,SignOff> signOffs = new HashMap<>();
	private Set<Component> components = new HashSet<>();
	private Date created = new Date();
	/** Formatted date. */
	@Indexed
	private String when;
	private ReleaseStatus status = ReleaseStatus.REQUESTED;

	public void setWhen(String when) {
		if (when != null) {
			if (Chronic.parse(when) == null) {
				throw new IllegalArgumentException("invalid date " + when);
			}
		}
		this.when = when;
	}
}
