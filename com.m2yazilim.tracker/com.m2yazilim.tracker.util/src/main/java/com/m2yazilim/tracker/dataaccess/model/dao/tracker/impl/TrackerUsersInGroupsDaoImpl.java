package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsersInGroups;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerUsersInGroupsDao;
 
 
/**
 * DAO for table: TrackerUsersInGroups.
 * @author autogenerated
 */
@Repository
public class TrackerUsersInGroupsDaoImpl extends GenericHibernateDaoImpl<TrackerUsersInGroups, Integer> implements TrackerUsersInGroupsDao {
	
	/** Constructor method. */
		public TrackerUsersInGroupsDaoImpl() {
			super(TrackerUsersInGroups.class);
		}
	}

