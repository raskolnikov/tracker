package com.m2yazilim.tracker.proxy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.m2yazilim.tracker.dao.AbstractSyncDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerVotesDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerVotes;
import com.m2yazilim.tracker.dto.TrackerVotesDto;

@Repository
public class InquiryTrackerVoteDaoImpl extends AbstractSyncDAO {

	private static final long serialVersionUID = 281363029151032499L;

	public static void execute(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

		validate(inRowIdCn, trackerUser, returnHashMap);

		TrackerVotesDao trackerVotesDao = HibernateTrackerDaoFactory.getTrackerVotesDao();
		List<TrackerVotes> trackerVotesList = trackerVotesDao.findByCriteria(inRowIdCn);
		List<TrackerVotesDto> trackerVotesDtoList = new ArrayList<TrackerVotesDto>();
		copyTrackerDbObjectToDto(trackerVotesList, trackerVotesDtoList);
		returnHashMap.put(TrackerVotesDto.class.getSimpleName(), trackerVotesDtoList);

	}

	public static void validate(Criterion inRowIdCn, TrackerUsers trackerUser, HashMap<String, List<?>> returnHashMap)
			throws Exception {

	}

	private static void copyTrackerDbObjectToDto(List<TrackerVotes> trackerVotesList,
			List<TrackerVotesDto> trackerVotesDtoList) throws Exception {

		for (TrackerVotes trackerVotes : trackerVotesList) {
			TrackerVotesDto trackerVotesDto = new TrackerVotesDto();
			BeanUtilsBean.getInstance().copyProperties(trackerVotesDto, trackerVotes);
			trackerVotesDto.setTaskId(trackerVotes.getTask().getId());
			trackerVotesDto.setUserId(trackerVotes.getUser().getId());
			trackerVotesDto.setVoteId(trackerVotes.getId());
			trackerVotesDtoList.add(trackerVotesDto);
		}
	}

}
