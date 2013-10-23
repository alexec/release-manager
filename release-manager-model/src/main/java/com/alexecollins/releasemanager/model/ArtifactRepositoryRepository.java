package com.alexecollins.releasemanager.model;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author alex.collins
 */
public interface ArtifactRepositoryRepository extends MongoRepository<ArtifactRepository,String> {

    ArtifactRepository findByName(String name);
}
