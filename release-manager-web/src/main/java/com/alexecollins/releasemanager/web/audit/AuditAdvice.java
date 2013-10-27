package com.alexecollins.releasemanager.web.audit;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Aspect
public class AuditAdvice {

	@Autowired
	private AuditService auditService;

	@After("@annotation(auditAnnotation)")
	public void audit(Audit auditAnnotation) {
		auditService.audit(auditAnnotation.value());
	}
}