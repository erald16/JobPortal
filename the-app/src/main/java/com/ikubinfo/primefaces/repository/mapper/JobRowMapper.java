package com.ikubinfo.primefaces.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.Job;

public class JobRowMapper implements RowMapper<Job> {

	@Override
	public Job mapRow(ResultSet result, int rowNum) throws SQLException {
		Job jobs = new Job();
		jobs.setJobID(result.getInt("job_ID"));
		jobs.setTitle(result.getString("title"));
		jobs.setDescription(result.getString("description"));
		jobs.setActive(result.getBoolean("active"));
		jobs.setCreateDate(result.getDate("create_date"));
		jobs.setLastupDate(new Date(result.getTimestamp("last_update").getTime()));
		jobs.setEmployeeID(result.getInt("employer_ID"));
		jobs.setCategoryID(result.getInt("category_ID"));
		return jobs;
	}

}
