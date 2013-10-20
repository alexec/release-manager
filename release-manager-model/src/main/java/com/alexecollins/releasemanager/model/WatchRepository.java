package com.alexecollins.releasemanager.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public interface WatchRepository extends MongoRepository<Watch,String> {

	List<Watch> findByUser(String user);

	List<Watch> findBySubject(String subject);

	Watch findByUserAndSubject(String user, String subject);
}
