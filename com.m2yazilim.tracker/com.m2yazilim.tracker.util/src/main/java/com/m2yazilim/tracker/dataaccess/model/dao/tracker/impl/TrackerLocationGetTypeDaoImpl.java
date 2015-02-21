package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerLocationGetType;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerLocationGetTypeDao;
 
 
/**
 * DAO for table: TrackerLocationGetType.
 * @author autogenerated
 */
@Repository
public class TrackerLocationGetTypeDaoImpl extends GenericHibernateDaoImpl<TrackerLocationGetType, Integer> implements TrackerLocationGetTypeDao {
	
	/** Constructor method. */
		public TrackerLocationGetTypeDaoImpl() {
			super(TrackerLocationGetType.class);
		}
	}
