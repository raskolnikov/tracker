package com.m2yazilim.tracker.dataaccess.model.dao.tracker.impl;

import com.felees.hbnpojogen.persistence.impl.GenericHibernateDaoImpl;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerVotes;
import org.springframework.stereotype.Repository;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerVotesDao;
 
 
/**
 * DAO for table: TrackerVotes.
 * @author autogenerated
 */
@Repository
public class TrackerVotesDaoImpl extends GenericHibernateDaoImpl<TrackerVotes, Integer> implements TrackerVotesDao {
	
	/** Constructor method. */
		public TrackerVotesDaoImpl() {
			super(TrackerVotes.class);
		}
	}

