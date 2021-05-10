package com.ikubinfo.primefaces.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.Skills;

public class SkillsRowMapper implements RowMapper<Skills> {

	@Override
	public Skills mapRow(ResultSet result, int rowNum) throws SQLException {
		Skills skills = new Skills();
		skills.setSkillID(result.getInt("skill_ID"));
		skills.setType(result.getString("stype"));
		skills.setDescription(result.getString("description"));
		skills.setCreateDate(new Date(result.getTimestamp("create_date").getTime()));
		skills.setLastUpdated(new Date(result.getTimestamp("last_update").getTime()));
		return skills;
	}
 
} 