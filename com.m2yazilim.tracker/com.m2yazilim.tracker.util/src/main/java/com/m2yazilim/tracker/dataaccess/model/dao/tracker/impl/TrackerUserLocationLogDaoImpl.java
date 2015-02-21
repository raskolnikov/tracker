package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUserLocationLog;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUserLocationLogDao;
 
 
/**
 * DAO for table: TrackerUserLocationLog.
 * @author autogenerated
 */
@Repository
public class TrackerUserLocationLogDaoImpl extends GenericHibernateDaoImpl<TrackerUserLocationLog, Integer> implements TrackerUserLocationLogDao {
	
	/** Constructor method. */
		public TrackerUserLocationLogDaoImpl() {
			super(TrackerUserLocationLog.class);
		}
	}
