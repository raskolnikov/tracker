package com.m2yazilim.tracker.proxy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.m2yazilim.tracker.model.TaskInfo;

public class TaskInfoResultSetExtractor implements ResultSetExtractor{

	
	  @Override
	  public Object extractData(ResultSet rs) throws SQLException {
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setDateOpened(rs.getString("date_opened"));
		taskInfo.setDueDate(rs.getString("due_date"));
		taskInfo.setItemStatus(rs.getString("item_status"));
		taskInfo.setProjectId(rs.getString("project_id"));
		taskInfo.setProjectTitle(rs.getString("project_title"));
		taskInfo.setResolutionReason(rs.getString("resolution_reason"));
		taskInfo.setStatusName(rs.getString("status_name"));
		taskInfo.setTaskId(rs.getString("task_id"));
		taskInfo.setUserId(rs.getString("user_id"));
		
	    return taskInfo;
	  }
	  
}
