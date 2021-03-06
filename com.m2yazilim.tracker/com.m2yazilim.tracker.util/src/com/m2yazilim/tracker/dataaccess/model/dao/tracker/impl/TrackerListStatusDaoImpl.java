package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListStatus;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerListStatusDao;
 
 
/**
 * DAO for table: TrackerListStatus.
 * @author autogenerated
 */
@Repository
public class TrackerListStatusDaoImpl extends GenericHibernateDaoImpl<TrackerListStatus, Integer> implements TrackerListStatusDao {
	
	/** Constructor method. */
		public TrackerListStatusDaoImpl() {
			super(TrackerListStatus.class);
		}
	}

