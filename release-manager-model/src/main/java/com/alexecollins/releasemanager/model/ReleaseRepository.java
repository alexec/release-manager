package com.alexecollins.releasemanager.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public interface ReleaseRepository extends MongoRepository<Release,String> {

	Release findByName(String name);

    List<Release> findByStatus(ReleaseStatus status, Sort sort);
}
