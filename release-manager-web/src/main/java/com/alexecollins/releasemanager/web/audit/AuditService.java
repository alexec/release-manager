package com.alexecollins.releasemanager.web.audit;

import com.alexecollins.releasemanager.model.AuditLog;
import com.alexecollins.releasemanager.model.AuditLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Slf4j
public class AuditService {
	@Autowired
	AuditLogRepository repo;

	public void audit(String message) {

		String userName = getCurrentUser();

		AuditService.log.info("Audit: {} - {}", userName, message);

		final AuditLog log = new AuditLog();
		log.setUsername(userName);
		log.setMessage(message);
		repo.save(log);

	}

	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
