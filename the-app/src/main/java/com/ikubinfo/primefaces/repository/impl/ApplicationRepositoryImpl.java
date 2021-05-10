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

import com.ikubinfo.primefaces.model.Application;
import com.ikubinfo.primefaces.repository.ApplicationRepository;
import com.ikubinfo.primefaces.repository.mapper.ApplicationRowMapper;

@Repository
class ApplicationRepositoryImpl implements ApplicationRepository{

	Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

	private static final String QUERY = "Select * from public.application where application.active = TRUE ";
	private static final String UPDATE_QUERY = "update application set  application.application_id = :application_id , application.active = :active," 
												+ "application.status = :status, application.last_updated = :last_updated, application.user_id = :user_id,"
												+"application.job_id= :job_id where application.application_id = :application_id";
	
	private static final String DELETE_QUERY= "Delete from application where application_id = :id ";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertApplicationQuery;
	
	@Autowired
	public ApplicationRepositoryImpl(DataSource datasource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		this.insertApplicationQuery = new SimpleJdbcInsert(datasource).withTableName("application")
				.usingGeneratedKeyColumns("application_id");
	}
	
	@Override
	public List<Application> getAll(int userID){
		Map<String, Object> params = new HashMap<>();
		params.put("userID", userID);
		String queryString = QUERY;

		queryString = queryString.concat(" and  application.user_id = :userID ");

		return namedParameterJdbcTemplate.query(queryString, params, new ApplicationRowMapper());
	}
	
	@Override
	public boolean save(Application application) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("application_id", application.getApplicationID());
		namedParameters.addValue("active", application.isActive());
		namedParameters.addValue("status", application.getStatus());
		namedParameters.addValue("create_date", application.getCreateDate());
		namedParameters.addValue("last_updated", application.getLastUpdated());
		namedParameters.addValue("user_id", application.getUserID());
		namedParameters.addValue("job_id", application.getJobID());
		
		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);

		return updatedCount > 0;
	}
	
	
	public boolean create(Application application) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("application_id", application.getApplicationID());
		parameters.put("active", application.isActive());
		parameters.put("status", application.getStatus());
		parameters.put("create_date", application.getCreateDate());
		parameters.put("last_updated", application.getLastUpdated());
		parameters.put("user_id", application.getUserID());
		parameters.put("job_id", application.getJobID());
		
		
		return insertApplicationQuery.execute(parameters) > 0;
	}
	
	public void delete(Application application) {
		

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("id", application.getApplicationID());

		this.namedParameterJdbcTemplate.query(DELETE_QUERY, namedParameters, new ApplicationRowMapper());	
	}

}
