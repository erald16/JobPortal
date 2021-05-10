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

import com.ikubinfo.primefaces.model.Categories;
import com.ikubinfo.primefaces.model.request.CategoryListRequest;
import com.ikubinfo.primefaces.repository.CategoryRepository;
import com.ikubinfo.primefaces.repository.mapper.CategoryRowMapper;

@Repository
class CategoryRepositoryImpl implements CategoryRepository {

	Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

	private static final String QUERY = "Select * from categories where 1=1";
	private static final String UPDATE_QUERY = "update categories set  name = :name, description = :description, last_update = :last_update, active= :active where category_ID = :id";
	private static final String DELETE_CATEGORY = "Delete from categories where category_ID = :id";
	private static final String COUNT_CATEGORY = "Select count(category_id) from public.categories where 1=1";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertJobQuery;

	@Autowired
	public CategoryRepositoryImpl(DataSource datasource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		this.insertJobQuery = new SimpleJdbcInsert(datasource).withTableName("category")
				.usingGeneratedKeyColumns("job_ID");
	}

	@Override
	public List<Categories> getAll(CategoryListRequest request) {
		logger.info("Filtering category for request {}", request);
		Map<String, Object> params = new HashMap<>();
		if (request != null && !Objects.isNull(request.getName())) {
			params.put("name", "%" + request.getName().toLowerCase() + "%");
		}
	

		String queryString = QUERY;

		if (request != null && !Objects.isNull(request.getName()) && !request.getName().isEmpty()) {
			queryString = queryString.concat(" and  LOWER(categories.title) like  :name ");
		}

		if (request != null) {
			params.put("row_count", request.getPageSize());
			params.put("row_to_skip", request.getFirst());
			queryString = queryString.concat(" LIMIT :row_count OFFSET :row_to_skip");	
		}
		
		logger.info("SQL ->  {} ", queryString);
		List<Categories> response = namedParameterJdbcTemplate.query(queryString, params, new CategoryRowMapper());
		logger.info("Results taken from the query {}", response.size());
		return response;
		
	}

	@Override
	public boolean save(Categories category) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("category_ID", category.getCategoryID());
		namedParameters.addValue("name", category.getName());
		namedParameters.addValue("description", category.getDescription());
		namedParameters.addValue("active", category.isActive());

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);

		return updatedCount > 0;
	}

	@Override
	public boolean create(Categories category) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", category.getName());
		parameters.put("description", category.getDescription());
		parameters.put("create_date", category.getCreateDate());
		parameters.put("last_update", category.getLastUpdate());
		parameters.put("active", category.isActive());
		return insertJobQuery.execute(parameters) > 0;
	}

	@Override
	public void delete(Categories category) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("id", category.getCategoryID());

		this.namedParameterJdbcTemplate.update(DELETE_CATEGORY, namedParameters);

	}
	
	@Override
	public int getCategoryCount(CategoryListRequest request) {

		logger.info("Counting categories for request {}", request);

		Map<String, Object> params = new HashMap<>();
		if (!Objects.isNull(request.getName())) {
			params.put("name", "%" + request.getName().toLowerCase() + "%");
		}
		String queryString = COUNT_CATEGORY;

		if (!Objects.isNull(request.getName()) && !request.getName().isEmpty()) {
			queryString = queryString.concat(" and  LOWER(categories.name) like  :name ");
		}

		logger.info("SQL ->  {} ", queryString);
		return namedParameterJdbcTemplate.queryForObject(queryString, params, Integer.class);
	}
}
