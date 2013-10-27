package com.alexecollins.releasemanager.web.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Slf4j
public class AuditService {
	public void audit(String screenName) {

		String userName = getCurrentUser();

		log.info("Audit: {} - {}", userName, screenName);

	}

	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
