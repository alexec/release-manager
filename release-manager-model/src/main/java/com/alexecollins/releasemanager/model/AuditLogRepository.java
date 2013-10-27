package com.alexecollins.releasemanager.model;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public interface AuditLogRepository extends MongoRepository<AuditLog,String> {
}
