package com.m2yazilim.tracker.scheduler.dao.impl;

import java.util.Date;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.m2yazilim.tracker.dao.AbstractDAO;
import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerTableChangeLogDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTableChangeLog;
import com.m2yazilim.tracker.scheduler.messages.UpdateUserPushTableRequest;
import com.m2yazilim.tracker.scheduler.messages.UpdateUserPushTableResponse;
import com.m2yazilim.tracker.util.ApplicationContextProvider;

@Transactional
public class UpdateUserPushTableDaoImpl extends AbstractDAO<UpdateUserPushTableRequest, UpdateUserPushTableResponse> {

	@Override
	public void execute(final UpdateUserPushTableRequest request, UpdateUserPushTableResponse response)
			throws Exception {

		if (!validate(request, response)) {
			return;
		}
		WebApplicationContext wac = (WebApplicationContext) ApplicationContextProvider.getApplicationContext();
		HibernateTransactionManager manager = (HibernateTransactionManager) wac.getBean("transactionManager");
		TransactionTemplate transactionTemplate = new TransactionTemplate(manager);
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				TrackerTableChangeLogDao trackerTableChangeLogDao = HibernateTrackerDaoFactory.getTrackerTableChangeLogDao();
				TrackerTableChangeLog trackerTableChangeLog = trackerTableChangeLogDao.get(request.getCurrentPushLog().getId());
				trackerTableChangeLog.setIsSent(1);
				trackerTableChangeLog.setSentDate(new Date());
				trackerTableChangeLogDao.saveOrUpdate(trackerTableChangeLog);
			}
		});

	}

	@Override
	public boolean validate(UpdateUserPushTableRequest request, UpdateUserPushTableResponse response) {

		return true;

	}

}
