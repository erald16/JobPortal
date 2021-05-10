package com.ikubinfo.primefaces.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.Application;

public class ApplicationRowMapper implements RowMapper<Application> {

	@Override
	public Application mapRow(ResultSet result, int rowNum) throws SQLException {
		Application application = new Application();
		application.setApplicationID(result.getInt("application_id"));
		application.setActive(result.getBoolean("active"));
		application.setStatus(result.getString("status"));
		application.setCreateDate(result.getDate("create_date"));
		application.setLastUpdated(result.getDate("last_updated"));
		application.setUserID(result.getInt("user_id"));
		application.setJobID(result.getInt("job_id"));
		return application;

	}

}