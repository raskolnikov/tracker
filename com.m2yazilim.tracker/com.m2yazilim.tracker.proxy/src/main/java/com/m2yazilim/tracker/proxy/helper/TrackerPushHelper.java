package com.m2yazilim.tracker.proxy.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.m2yazilim.tracker.dataaccess.factories.tracker.HibernateTrackerDaoFactory;
import com.m2yazilim.tracker.dataaccess.model.dao.tracker.TrackerAssignedDao;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssigned;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dataaccess.services.data.DataLayerTrackerImpl;
import com.m2yazilim.tracker.model.TrackerSyncTableModel;
import com.m2yazilim.tracker.proxy.dao.impl.InquiryAllPushUserDaoImpl;

public class TrackerPushHelper {

	public static HashMap<String, List<?>> getUserDataByTable(HashMap<String, ArrayList<String>> tableRowMap,
			String userId) throws Exception {

		HashMap<String, List<?>> returnHashMap = new HashMap<String, List<?>>();
		TrackerUsers user = DataLayerTrackerImpl.getInstance().getTrackerUsers(Integer.parseInt(userId));

		// Method parameters
		Class<?>[] executeMethodParams = new Class[3];

		executeMethodParams[0] = Criterion.class;
		executeMethodParams[1] = TrackerUsers.class;
		executeMethodParams[2] = HashMap.class;

		for (TrackerSyncTableModel trackerSyncTableModel : TrackerSyncTableListHelper.getTrackerSyncTableModelList()) {
			if (tableRowMap.containsKey(trackerSyncTableModel.getTableObjectName())) {

				List<Integer> tableRowIdList = stringListToInteger(tableRowMap,
						trackerSyncTableModel.getTableObjectName());
				Criterion inRowIdCn = Restrictions.in("id", tableRowIdList);

				Class<?> cls = Class.forName(trackerSyncTableModel.SERVICE_DAO_PACKAGE_NAME + "."
						+ trackerSyncTableModel.getInquiryDaoClassName());
				Object obj = cls.newInstance();

				// call the execute method
				Method method = cls.getDeclaredMethod("execute", executeMethodParams);
				method.invoke(obj, inRowIdCn, user, returnHashMap);

			}
		}
		return returnHashMap;
	}

	private static List<Integer> stringListToInteger(HashMap<String, ArrayList<String>> tableRowMap, String tableName) {
		List<String> tableRowIdListTmp = tableRowMap.get(tableName);
		List<Integer> tableRowIdList = new ArrayList<Integer>();
		for (String tableRowId : tableRowIdListTmp) {
			tableRowIdList.add(Integer.parseInt(tableRowId));
		}
		return tableRowIdList;
	}

	public static ArrayList<Integer> getAffectedUserList(String tableName, int rowId) throws Exception {

		ArrayList<Integer> affectedUserList = new ArrayList<Integer>();

		// Method parameters
		Class<?>[] executeMethodParams = new Class[2];

		executeMethodParams[0] = int.class;
		executeMethodParams[1] = ArrayList.class;
		boolean matchFound = false;
		for (TrackerSyncTableModel trackerSyncTableModel : TrackerSyncTableListHelper.getTrackerPushTableModelList()) {
			if (trackerSyncTableModel.getTableDbName().equals(tableName)) {
				matchFound = true;
				Class<?> cls = Class.forName(trackerSyncTableModel.SERVICE_DAO_PACKAGE_NAME + "."
						+ trackerSyncTableModel.getInquiryDaoClassName());
				Object obj = cls.newInstance();

				// call the execute method
				Method method = cls.getDeclaredMethod("execute", executeMethodParams);
				method.invoke(obj, rowId, affectedUserList);

			}
		}
		// Geneli etkileyen bir push, sync gerektirir
		if (!matchFound) {
			InquiryAllPushUserDaoImpl.execute(rowId, affectedUserList);
		}

		return affectedUserList;

	}

	public static void findAssignedUserList(ArrayList<Integer> affectedUserList, TrackerTasks trackerTask) {
		TrackerAssignedDao trackerAssignedDao = HibernateTrackerDaoFactory.getTrackerAssignedDao();
		Criterion taskCn = Restrictions.eq("task", trackerTask);
		List<TrackerAssigned> trackerAssignedList = trackerAssignedDao.findByCriteria(taskCn);
		for (TrackerAssigned trackerAssigned : trackerAssignedList) {
			affectedUserList.add(trackerAssigned.getUser().getId());
		}
	}

	public static String getPushServiceKey() throws Exception {
		String PATH = "/api.key";
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH);
		if (stream == null) {
			throw new IllegalStateException("Could not find file " + PATH + " on web resources)");
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		try {
			String key = reader.readLine();
			return key;
		} finally {
			reader.close();
		}
	}
}
