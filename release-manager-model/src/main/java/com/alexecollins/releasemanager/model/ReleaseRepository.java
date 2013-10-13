package com.alexecollins.releasemanager.model;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public interface ReleaseRepository extends MongoRepository<Release,String> {
}
