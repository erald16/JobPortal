package com.ikubinfo.primefaces.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ikubinfo.primefaces.model.Resume;
import com.ikubinfo.primefaces.repository.ResumeRepository;
import com.ikubinfo.primefaces.repository.mapper.ResumeRowMapper;

@Repository
class ResumeRepositoryImpl implements ResumeRepository{
	Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);
	
	private static final String QUERY = "Select * from public.category where 1=1 ";
	private static final String UPDATE_QUERY = "update resume set  Resume.path = :path,Resume.last_updated = :last_updated  where resume_id = :id";
	private static final String DELETE_QUERY = "Delete from resume where resume_id = :id ";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertResumeQuery;
	
	@Autowired
	public ResumeRepositoryImpl(DataSource datasource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		this.insertResumeQuery = new SimpleJdbcInsert(datasource).withTableName("resume")
				.usingGeneratedKeyColumns("resume_id");
	}
	
	@Override
	public List<Resume> getAll(String path){
		Map<String, Object> params = new HashMap<>();
		params.put("path", "%" + path + "%");
		
		String queryString = QUERY;
		
		if(!Objects.isNull(path) && !path.isEmpty()) {
			queryString = queryString.concat("and resume.path like :path");
		}
		
		return namedParameterJdbcTemplate.query(queryString, params, new ResumeRowMapper());
	}
	
	@Override
	public boolean save(Resume resume) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("resumeID", resume.getResumeID());
		namedParameters.addValue("resumePath", resume.getResumePath());
		namedParameters.addValue("createDate", resume.getCreateDate());
		namedParameters.addValue("lastUpdate", resume.getLastUpdate());
		
		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);

		return updatedCount > 0;
	}
	
	@Override
	public boolean create(Resume resume) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("resume_path", resume.getResumePath());
		parameters.put("create_date", resume.getCreateDate());
		parameters.put("last_update", resume.getLastUpdate());
		
		return insertResumeQuery.execute(parameters) > 0;
	}
	
	@Override
	public int insert(Resume resume) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("resume_path", resume.getResumePath());
		parameters.put("create_date", resume.getCreateDate());
		parameters.put("last_update", resume.getLastUpdate());
		
		return insertResumeQuery.executeAndReturnKey(parameters).intValue();
	}
	
	public void delete(Resume resume) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("resumeID", resume.getResumeID());
		
		this.namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
	}

}
