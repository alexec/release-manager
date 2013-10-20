package com.alexecollins.releasemanager.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public interface WatchRepository extends MongoRepository<Watch,String> {

	List<Watch> findByUserId(String userId);

	List<Watch> findBySubject(String subject);
}
