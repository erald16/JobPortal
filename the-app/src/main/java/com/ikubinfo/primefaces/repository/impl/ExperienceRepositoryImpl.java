package com.ikubinfo.primefaces.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ikubinfo.primefaces.model.Experience;
import com.ikubinfo.primefaces.repository.ExperienceRepository;
import com.ikubinfo.primefaces.repository.mapper.ExperienceRowMapper;

@Repository
public class ExperienceRepositoryImpl implements ExperienceRepository {

	Logger logger = LoggerFactory.getLogger(ExperienceRepositoryImpl.class);

	private static final String QUERY = "Select * from public.Experience";

	private static final String UPDATE_QUERY = "update Experience set  position = :position, description = :description,"
			+ " start_date = :startDate, end_date = :endDate"
			+ " where experience_ID = :experienceID";

	private static final String DELETE_CATEGORY = "update Experience set active = FALSE where experience.experience_ID = :id";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertExperienceQuery;

	@Autowired
	public ExperienceRepositoryImpl(DataSource datasource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		this.insertExperienceQuery = new SimpleJdbcInsert(datasource).withTableName("Experience")
				.usingGeneratedKeyColumns("experience_ID");
	}

	@Override
	public List<Experience> getAll(int userID) {
		Map<String, Object> params = new HashMap<>();
		params.put("userID", userID);

		String queryString = QUERY;
		
		if (userID != -1) {
			queryString = queryString.concat(", public.userExperienceRoute where Experience.experience_ID = userExperienceRoute.experience_ID and userExperienceRoute.user_ID = :userID and Experience.active = TRUE ");
		}

		return namedParameterJdbcTemplate.query(queryString, params, new ExperienceRowMapper());
	}

	@Override
	public boolean save(Experience experience) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("experienceID", experience.getExperienceID());
		namedParameters.addValue("position", experience.getPosition());
		namedParameters.addValue("description", experience.getDescription());
		namedParameters.addValue("startDate", experience.getStartDate());
		namedParameters.addValue("endDate", experience.getEndDate());

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);

		return updatedCount > 0;
	}

	@Override
	public boolean create(Experience experience) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("experienceID", experience.getExperienceID());
		parameters.put("position", experience.getPosition());
		parameters.put("description", experience.getDescription());
		parameters.put("active", 1);
		parameters.put("start_date", experience.getStartDate());
		parameters.put("end_date", experience.getEndDate());

		return insertExperienceQuery.execute(parameters) > 0;
	}

	@Override
	public boolean delete(Experience experience) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("id", experience.getExperienceID());

		int count = this.namedParameterJdbcTemplate.update(DELETE_CATEGORY, namedParameters);
		
		return count > 0;
	}

}
