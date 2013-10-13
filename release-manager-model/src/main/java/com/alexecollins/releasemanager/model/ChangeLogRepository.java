package com.alexecollins.releasemanager.model;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public interface ChangeLogRepository extends MongoRepository<ChangeLog,String> {
	ChangeLog findByName(String name);
}
