package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerRegistrations;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerRegistrationsDao;
 
 
/**
 * DAO for table: TrackerRegistrations.
 * @author autogenerated
 */
@Repository
public class TrackerRegistrationsDaoImpl extends GenericHibernateDaoImpl<TrackerRegistrations, Integer> implements TrackerRegistrationsDao {
	
	/** Constructor method. */
		public TrackerRegistrationsDaoImpl() {
			super(TrackerRegistrations.class);
		}
	}

