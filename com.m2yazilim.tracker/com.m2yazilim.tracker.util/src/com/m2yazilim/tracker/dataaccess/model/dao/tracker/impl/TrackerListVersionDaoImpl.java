package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListVersion;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListVersionDao;
 
 
/**
 * DAO for table: TrackerListVersion.
 * @author autogenerated
 */
@Repository
public class TrackerListVersionDaoImpl extends GenericHibernateDaoImpl<TrackerListVersion, Integer> implements TrackerListVersionDao {
	
	/** Constructor method. */
		public TrackerListVersionDaoImpl() {
			super(TrackerListVersion.class);
		}
	}

