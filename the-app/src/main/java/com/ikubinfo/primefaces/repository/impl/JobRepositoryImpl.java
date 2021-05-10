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

import com.ikubinfo.primefaces.model.Job;
import com.ikubinfo.primefaces.model.JobContacts;
import com.ikubinfo.primefaces.model.request.JobListRequest;
import com.ikubinfo.primefaces.repository.JobRepository;
import com.ikubinfo.primefaces.repository.mapper.JobContactsRowMapper;
import com.ikubinfo.primefaces.repository.mapper.JobRowMapper;

@Repository
class JobRepositoryImpl implements JobRepository {

	Logger logger = LoggerFactory.getLogger(JobRepositoryImpl.class);

	private static final String QUERY = "Select * from job where 1=1";
	private static final String UPDATE_QUERY = "update job set  title = :title, description = :description, last_update = :last_update, active= :active, employer_ID = :employer_ID, categroy_ID = :category_ID, skill_ID =:skill_ID where job_ID = :id";
	private static final String DELETE_JOB = "Delete from job where job_ID = :id";
	//private static final String FILTER_JOB = "Select * from job j inner join categories c on j.category_id=c.category_id inner join skills s on s.skill_id=j.skill_id WHERE active=TRUE";
	private static final String FILTER_JOB = "SELECT * from job inner join categories on job.category_id = categories.category_id inner join jobcategoryroute on jobcategoryroute.job_id = job.job_id where 1=1";
	
	private static final String GET_CONTACT = "select job.*, users.email as email, users.website as website,  users.phone_n as phone_number from job, users where users.user_id = :user_id";
	private static final String COUNT_JOBS = "Select count(job_id) from public.job where 1=1";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertJobQuery;

	@Autowired
	public JobRepositoryImpl(DataSource datasource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		this.insertJobQuery = new SimpleJdbcInsert(datasource).withTableName("job").usingGeneratedKeyColumns("job_ID");
	}

	@Override
	public List<Job> getAll(JobListRequest request) {
		logger.info("Filtering job for request {}", request);
		Map<String, Object> params = new HashMap<>();
		if (request != null && !Objects.isNull(request.getTitle())) {
			params.put("name", "%" + request.getTitle().toLowerCase() + "%");
		}
		params.put("row_count", request.getPageSize());
		params.put("row_to_skip", request.getFirst());

		String queryString = QUERY;

		if (request != null && !Objects.isNull(request.getTitle()) && !request.getTitle().isEmpty()) {
			queryString = queryString.concat(" and  LOWER(job.title) like  :name ");
		}

		queryString = queryString.concat(" LIMIT :row_count OFFSET :row_to_skip");

		logger.info("SQL ->  {} ", queryString);
		List<Job> response = namedParameterJdbcTemplate.query(queryString, params, new JobRowMapper());
		logger.info("Results taken from the query {}", response.size());
		return response;
	}

	@Override
	public boolean create(Job job) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("title", job.getTitle());
		parameters.put("description", job.getDescription());
		parameters.put("employer_ID", job.getEmployeeID());
		parameters.put("category_ID", job.getCategoryID());
		parameters.put("skill_ID", job.getSkillID());
		parameters.put("create_date", job.getCreateDate());
		parameters.put("last_update", job.getLastupDate());
		parameters.put("active", job.isActive());
		return insertJobQuery.execute(parameters) > 0;

	}

	@Override
	public boolean save(Job job) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("job_ID", job.getJobID());
		namedParameters.addValue("title", job.getTitle());
		namedParameters.addValue("description", job.getDescription());
		namedParameters.addValue("active", job.isActive());
		namedParameters.addValue("skill_ID", job.getSkillID());
		namedParameters.addValue("employee_ID", job.getEmployeeID());
		namedParameters.addValue("category_ID", job.getCategoryID());

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);

		return updatedCount > 0;
	}

	@Override
	public void delete(Job job) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("id", job.getJobID());

		this.namedParameterJdbcTemplate.update(DELETE_JOB, namedParameters);

	}

	@Override
	public List<Job> filterJob(String category) {

		Map<String, Object> params = new HashMap<>();

		params.put("cname", "%" + category + "%");

		String queryString = FILTER_JOB;

		if (!Objects.isNull(category) && !category.isEmpty()) {
			queryString = queryString.concat(" and categories.name like  :cname ");
		}

		List<Job> result = namedParameterJdbcTemplate.query(queryString, params, new JobRowMapper());
		logger.info("Returned results for job filtering {}", result.size());
		return result;

	}

	@Override
	public List<JobContacts> jobContact(int user_ID) {
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", user_ID);

		String queryString = GET_CONTACT;

		return namedParameterJdbcTemplate.query(queryString, params, new JobContactsRowMapper());
	}
	
	@Override
	public int getJobCount(JobListRequest request) {

		logger.info("Counting jobs for request {}", request);

		Map<String, Object> params = new HashMap<>();
		if (!Objects.isNull(request.getTitle())) {
			params.put("name", "%" + request.getTitle().toLowerCase() + "%");
		}
		String queryString = COUNT_JOBS;

		if (!Objects.isNull(request.getTitle()) && !request.getTitle().isEmpty()) {
			queryString = queryString.concat(" and  LOWER(job.title) like  :name ");
		}

		logger.info("SQL ->  {} ", queryString);
		return namedParameterJdbcTemplate.queryForObject(queryString, params, Integer.class);
	}
}
