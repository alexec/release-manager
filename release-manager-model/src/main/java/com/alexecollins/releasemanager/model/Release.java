package com.alexecollins.releasemanager.model;


import com.mdimension.jchronic.Chronic;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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
	private String when;
	@Indexed
	@Setter(AccessLevel.PRIVATE)
	private Long begin;
	private ReleaseStatus status = ReleaseStatus.REQUESTED;

	public void setWhen(String when) {
		if (when != null && Chronic.parse(when) == null) {
			throw new IllegalArgumentException("invalid date " + when);
		}
		this.when = when;
		if (when != null) {
			begin = Chronic.parse(when).getBegin();
		} else {
			begin = null;
		}
	}
}
