package com.alexecollins.releasemanager.model;

import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class BeforeSaveValidator extends AbstractMongoEventListener {

	@Autowired
	private Validator validator;

	@Override
	public void onBeforeSave(Object source, DBObject dbo) {
		Set<ConstraintViolation<Object>> violations = validator.validate(source);

		if (violations.size() > 0) {
			throw new ConstraintViolationException(violations);
		}
	}
}
