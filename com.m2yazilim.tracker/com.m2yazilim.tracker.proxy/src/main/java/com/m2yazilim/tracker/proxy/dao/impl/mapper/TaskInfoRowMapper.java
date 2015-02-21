package com.m2yazilim.tracker.proxy.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskInfoRowMapper implements RowMapper{

	  @Override
	  public Object mapRow(ResultSet rs, int line) throws SQLException {
	    TaskInfoResultSetExtractor extractor = new 	 TaskInfoResultSetExtractor();
	    return extractor.extractData(rs);
	  }
	  
}
