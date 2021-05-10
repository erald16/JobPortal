package com.ikubinfo.primefaces.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.Categories;

public class CategoryRowMapper implements RowMapper<Categories> {

	@Override
	public Categories mapRow(ResultSet result, int rowNum) throws SQLException {
		Categories category = new Categories();
		category.setCategoryID(result.getInt("category_ID"));
		category.setName(result.getString("name"));
		category.setDescription(result.getString("description"));
		category.setActive(result.getBoolean("active"));
		category.setCreateDate(result.getDate("create_date"));
		category.setLastUpdate(new Date(result.getTimestamp("last_update").getTime()));
		return category;
	}
}
