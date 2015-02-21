package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerHistory;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerHistoryDao;
 
 
/**
 * DAO for table: TrackerHistory.
 * @author autogenerated
 */
@Repository
public class TrackerHistoryDaoImpl extends GenericHibernateDaoImpl<TrackerHistory, Integer> implements TrackerHistoryDao {
	
	/** Constructor method. */
		public TrackerHistoryDaoImpl() {
			super(TrackerHistory.class);
		}
	}
