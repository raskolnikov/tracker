package com.m2yazilim.tracker.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SelectQueryLogUtil {
	public static List<String> getSelectResultLog(List<Map<String, Object>> resultList) {
		List<String> logStrList = new ArrayList<String>();
		if (resultList == null || resultList.size() == 0) {
			logStrList.add("[No rows returned]");
			return logStrList;
		} else {
			logStrList.add("[" + resultList.size() + " rows returned]");
		}

		for (int i = 0; resultList != null && i < resultList.size(); i++) {
			if (resultList.get(i) != null) {
				Map<String, Object> currMap = resultList.get(i);
				Set<String> currKeySet = currMap.keySet();
				String logStr = "[Row:" + (i + 1) + " Columns: {";

				int len = currKeySet.size();
				int j = 0;

				for (String currKey : currKeySet) {
					logStr = logStr + currKey + ":" + currMap.get(currKey);
					if (j + 1 < len) {
						logStr = logStr + "|";
					} else {
						logStr = logStr + "}";
					}
					j++;
				}
				logStr = logStr + "]";
				logStrList.add(logStr);
			}
		}

		return logStrList;
	}

	public static List<String> getSelectResultLog(Map<String, Object> result) {
		List<String> logStrList = new ArrayList<String>();
		if (result == null) {
			logStrList.add("[No rows returned]");
			return logStrList;
		} else {
			logStrList.add("[1 rows returned]");
		}

		Set<String> currKeySet = result.keySet();
		String logStr = "[Row:1 Columns: {";

		int len = currKeySet.size();
		int j = 0;

		for (String currKey : currKeySet) {
			logStr = logStr + currKey + ":" + result.get(currKey);
			if (j + 1 < len) {
				logStr = logStr + "|";
			} else {
				logStr = logStr + "}";
			}
			j++;
		}
		logStr = logStr + "]";
		logStrList.add(logStr);

		return logStrList;
	}

}
