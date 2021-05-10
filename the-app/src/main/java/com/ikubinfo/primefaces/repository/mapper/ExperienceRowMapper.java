package com.ikubinfo.primefaces.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.Experience;

public class ExperienceRowMapper implements RowMapper<Experience> {

	@Override
	public Experience mapRow(ResultSet rs, int rowNum) throws SQLException {
		Experience exp = new Experience();

		exp.setExperienceID(rs.getInt("experience_ID"));
		exp.setPosition(rs.getString("position"));
		exp.setDescription(rs.getString("description"));
		exp.setStartDate(rs.getDate("start_date"));
		exp.setEndDate(rs.getDate("end_date"));
		exp.setCreateDate(rs.getDate("create_date"));
		exp.setLastUpdate(rs.getDate("last_update"));

		return exp;
	}

}
