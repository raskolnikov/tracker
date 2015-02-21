package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAttachments;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAttachmentsDao;
 
 
/**
 * DAO for table: TrackerAttachments.
 * @author autogenerated
 */
@Repository
public class TrackerAttachmentsDaoImpl extends GenericHibernateDaoImpl<TrackerAttachments, Integer> implements TrackerAttachmentsDao {
	
	/** Constructor method. */
		public TrackerAttachmentsDaoImpl() {
			super(TrackerAttachments.class);
		}
	}
