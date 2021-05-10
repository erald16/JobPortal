package com.ikubinfo.primefaces.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.Resume;

public class ResumeRowMapper implements RowMapper<Resume> {
	
	@Override
	public Resume mapRow(ResultSet result, int rowNum) throws SQLException { 
		Resume resume = new Resume();
		resume.setResumeID(result.getInt("resume_id"));
		resume.setResumePath(result.getString("resume_path"));
		resume.setCreateDate(result.getDate("create_date"));
		resume.setLastUpdate(result.getDate("last_update"));
		return resume;
	}

}
