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

import com.ikubinfo.primefaces.model.Skills;
import com.ikubinfo.primefaces.repository.SkillsRepository;
import com.ikubinfo.primefaces.repository.mapper.SkillsRowMapper;

@Repository
class SkillsRepositoryImpl implements SkillsRepository{
	
	Logger logger = LoggerFactory.getLogger(SkillsRepositoryImpl.class);

	private static final String QUERY = "Select * from public.Skills";
	private static final String USER_SKILLS_QUERY = "Select public.skills.* from public.skills, public.skillRoute where 1=1 ";
	//private static final String INSERT_QUERY = "insert into skills(type, description) values(?,?)";
	private static final String UPDATE_QUERY = "update skills set stype = :type, description = :description where skill_id = :id";
	private static final String DELETE_SKILLS = "Delete from skills where skill_id = :id";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertSkillsQuery;
	private SimpleJdbcInsert insertSkillsRouteQuery;
	
	@Autowired
	public SkillsRepositoryImpl(DataSource datasource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		this.insertSkillsQuery = new SimpleJdbcInsert(datasource).withTableName("skills")
				.usingGeneratedKeyColumns("skill_id");
		this.insertSkillsRouteQuery = new SimpleJdbcInsert(datasource).withTableName("skills")
				.usingGeneratedKeyColumns("skill_id");
	}
	
	@Override
	public List<Skills> getAll(int userID){

		Map<String, Object> params = new HashMap<>();
		params.put("userID", userID);

		String queryString = QUERY;
		
		if (userID != -1) {
			queryString = queryString.concat(", public.userSkillRoute where Skills.skill_ID = userSkillRoute.skill_ID and userSkillRoute.user_ID = :userID and Skills.active = TRUE ");
		}
		
		return namedParameterJdbcTemplate.query(queryString, params, new SkillsRowMapper());

	}

	@Override 
	public boolean save(Skills skills) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("id", skills.getSkillID());
		namedParameters.addValue("type", skills.getType());
		namedParameters.addValue("description", skills.getDescription());

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
 
		return updatedCount > 0;
	}

	@Override
	public boolean create(Skills skills) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("stype", skills.getType());
		parameters.put("description", skills.getDescription());

		insertSkillsQuery.setGeneratedKeyNames("skill_ID");
		return insertSkillsQuery.execute(parameters) > 0;

	}
	
	public List<Skills> searchSkillsByType(String type) {
		Map<String, Object> params = new HashMap<>();
		params.put("type", "%"+ type +"%");

		String queryString = QUERY;
		
		if (!type.isEmpty() &&  !Objects.isNull(type)) {
			queryString = queryString.concat(" where public.Skills.type like :type");
		}
		
		return namedParameterJdbcTemplate.query(queryString, params, new SkillsRowMapper());
	}
	
	public boolean addSkillToUser(int skillID, int userID) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userID", userID);
		parameters.put("description", skillID);

		insertSkillsRouteQuery.setGeneratedKeyNames("skillRoute_ID");
		return insertSkillsRouteQuery.execute(parameters) > 0;
	}


	@Override
	public void delete(Skills skills) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("id", skills.getSkillID());

		this.namedParameterJdbcTemplate.update(DELETE_SKILLS, namedParameters);

	}

	@Override
	public List<Skills> getUserSkills(int userID) {
		Map<String, Object> params = new HashMap<>();
		params.put("userID", userID);

		String queryString = USER_SKILLS_QUERY;

		queryString = queryString.concat(" and where skillRoute.skill_ID = Skills.skill_ID and skillRoute.user_ID = :userID");

		return namedParameterJdbcTemplate.query(queryString, params, new SkillsRowMapper());
	}	

}
