package com.ikubinfo.primefaces.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.JobContacts;

public class JobContactsRowMapper implements RowMapper<JobContacts> {

	@Override
	public JobContacts mapRow(ResultSet result, int rowNum) throws SQLException {
		JobContacts jobsc = new JobContacts();
		jobsc.setJobID(result.getInt("job_ID"));
		jobsc.setTitle(result.getString("title"));
		jobsc.setDescription(result.getString("description"));
		jobsc.setEmail(result.getString("email"));
		jobsc.setPhoneN(result.getInt("phone_n"));
		jobsc.setWebsite(result.getString("website"));
		jobsc.setActive(result.getBoolean("active"));
		jobsc.setCreateDate(result.getDate("create_date"));
		jobsc.setLastupDate(new Date(result.getTimestamp("last_update").getTime()));
		jobsc.setEmployeeID(result.getInt("employer_ID"));
		jobsc.setCategoryID(result.getInt("category_ID"));
		jobsc.setSkillID(result.getInt("skill_ID"));
		return jobsc;
	}

}
