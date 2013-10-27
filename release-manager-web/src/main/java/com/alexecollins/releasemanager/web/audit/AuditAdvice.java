package com.alexecollins.releasemanager.web.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Aspect
public class AuditAdvice {

	@Autowired
	private AuditService auditService;

	@Around("@annotation(auditAnnotation) ")
	public Object audit(ProceedingJoinPoint point, Audit auditAnnotation) throws Throwable {

		boolean ok = false;
		try {
			Object o = point.proceed();
			ok = true;
			return o;
		} finally {
			if (ok)
				auditService.audit(MessageFormat.format(auditAnnotation.value(), point.getArgs()));
		}
	}
}