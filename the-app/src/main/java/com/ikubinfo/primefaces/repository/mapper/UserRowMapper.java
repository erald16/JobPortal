package com.ikubinfo.primefaces.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		
		user.setUserID(rs.getInt("user_ID"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFullname(rs.getString("full_name"));
		user.setAccessLvl(rs.getString("access_L"));
		user.setGender(rs.getString("gender"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setWebsite(rs.getString("website"));
		user.setPhoneNumber(rs.getInt("phone_n"));
		user.setActive(rs.getBoolean("activ"));
		user.setLastUpdate(rs.getDate("last_update"));
		user.setCreateDate(rs.getDate("create_date"));
		user.setResumeID(rs.getInt("resume_ID"));
		
		return user;
	}

}
