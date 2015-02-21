package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerNotifications;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerNotificationsDao;
 
 
/**
 * DAO for table: TrackerNotifications.
 * @author autogenerated
 */
@Repository
public class TrackerNotificationsDaoImpl extends GenericHibernateDaoImpl<TrackerNotifications, Integer> implements TrackerNotificationsDao {
	
	/** Constructor method. */
		public TrackerNotificationsDaoImpl() {
			super(TrackerNotifications.class);
		}
	}

