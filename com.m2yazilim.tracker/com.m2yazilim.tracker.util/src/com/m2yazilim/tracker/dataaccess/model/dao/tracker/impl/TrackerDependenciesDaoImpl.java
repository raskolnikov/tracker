package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerDependencies;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerDependenciesDao;
 
 
/**
 * DAO for table: TrackerDependencies.
 * @author autogenerated
 */
@Repository
public class TrackerDependenciesDaoImpl extends GenericHibernateDaoImpl<TrackerDependencies, Integer> implements TrackerDependenciesDao {
	
	/** Constructor method. */
		public TrackerDependenciesDaoImpl() {
			super(TrackerDependencies.class);
		}
	}

