package com.alexecollins.releasemanager.model;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author alex.collins
 */
public interface ApproverRepository extends MongoRepository<Approver,String> {
}
