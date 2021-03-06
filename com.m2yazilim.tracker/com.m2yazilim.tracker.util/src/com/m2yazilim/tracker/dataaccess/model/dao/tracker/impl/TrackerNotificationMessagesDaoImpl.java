package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerNotificationMessages;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerNotificationMessagesDao;
 
 
/**
 * DAO for table: TrackerNotificationMessages.
 * @author autogenerated
 */
@Repository
public class TrackerNotificationMessagesDaoImpl extends GenericHibernateDaoImpl<TrackerNotificationMessages, Integer> implements TrackerNotificationMessagesDao {
	
	/** Constructor method. */
		public TrackerNotificationMessagesDaoImpl() {
			super(TrackerNotificationMessages.class);
		}
	}

