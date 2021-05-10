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

import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.model.request.UserListRequest;
import com.ikubinfo.primefaces.repository.UserRepository;
import com.ikubinfo.primefaces.repository.mapper.UserRowMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

	Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	private static final String QUERY = "Select * from public.Users where 1=1 ";

	private static final String UPDATE_QUERY = "update public.Users set  username = :username , password = :password,"
			+ "full_name = :fullname, access_L = :access_L, gender = :gender, address = :address,"
			+ "phone_n = :phone_n, activ = :activ"
			+ " where user_ID = :user_ID";

	private static final String DELETE_USER = "Delete from Users where user_ID = :id ";

	private static final String VALIDATING_QUERY = "select * from users where username = :username and password = :password";

	private static final String USERNAME_TAKE_VALIDATOR_QUERY = "select * from users where username = :username";

	private static final String COUNT_USERS = "Select count(user_id) from public.users where 1=1";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert insertUserQuery;

	@Autowired
	public UserRepositoryImpl(DataSource datasource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		this.insertUserQuery = new SimpleJdbcInsert(datasource).withTableName("Users")
				.usingGeneratedKeyColumns("user_ID");
	}

	@Override
	public List<User> getAll(UserListRequest request) {
		logger.info("Filtering user for request {}", request);
		Map<String, Object> params = new HashMap<>();
		if (request != null && !Objects.isNull(request.getFullname())) {
			params.put("name", "%" + request.getFullname().toLowerCase() + "%");
		}
		params.put("row_count", request.getPageSize());
		params.put("row_to_skip", request.getFirst());

		String queryString = QUERY;

		if (request != null && !Objects.isNull(request.getFullname()) && !request.getFullname().isEmpty()) {
			queryString = queryString.concat(" and  LOWER(users.full_name) like  :name ");
		}

		queryString = queryString.concat(" LIMIT :row_count OFFSET :row_to_skip");

		logger.info("SQL ->  {} ", queryString);
		List<User> response = namedParameterJdbcTemplate.query(queryString, params, new UserRowMapper());
		logger.info("Results taken from the query {}", response.size());
		return response;
	}

	@Override
	public boolean save(User user) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("username", user.getUsername());
		namedParameters.addValue("password", user.getPassword());
		namedParameters.addValue("fullname", user.getFullname());
		namedParameters.addValue("access_L", user.getAccessLvl());
		namedParameters.addValue("gender", user.getGender());
		namedParameters.addValue("address", user.getAddress());
		namedParameters.addValue("phone_n", user.getPhoneNumber());
		namedParameters.addValue("activ", user.isActive());
		namedParameters.addValue("create_date", user.getCreateDate());
		namedParameters.addValue("last_update", user.getLastUpdate());
		namedParameters.addValue("resume_id", user.getResumeID());
		namedParameters.addValue("user_ID", user.getUserID());

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);

		return updatedCount > 0;
	}

	@Override
	public boolean create(User user) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("username", user.getUsername());
		parameters.put("password", user.getPassword());
		parameters.put("full_name", user.getFullname());
		parameters.put("access_L", user.getAccessLvl());
		parameters.put("gender", user.getGender());
		parameters.put("address", user.getAddress());
		parameters.put("activ", true);
		return insertUserQuery.execute(parameters) > 0;
	}

	@Override
	public void delete(User user) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("id", user.getUserID());

		this.namedParameterJdbcTemplate.update(DELETE_USER, namedParameters);
	}

	public boolean isUsernameTaken(String username) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);

		String queryString = USERNAME_TAKE_VALIDATOR_QUERY;

		List<User> list = namedParameterJdbcTemplate.query(queryString, params, new UserRowMapper());

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public User validate(String username, String password) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);

		String queryString = VALIDATING_QUERY;

		List<User> list = namedParameterJdbcTemplate.query(queryString, params, new UserRowMapper());

		if (list.isEmpty() || list.size() != 1) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public int getUserCount(UserListRequest request) {

		logger.info("Counting users for request {}", request);

		Map<String, Object> params = new HashMap<>();
		if (!Objects.isNull(request.getFullname())) {
			params.put("name", "%" + request.getFullname().toLowerCase() + "%");
		}
		String queryString = COUNT_USERS;

		if (!Objects.isNull(request.getFullname()) && !request.getFullname().isEmpty()) {
			queryString = queryString.concat(" and  LOWER(users.full_name) like  :name ");
		}

		logger.info("SQL ->  {} ", queryString);
		return namedParameterJdbcTemplate.queryForObject(queryString, params, Integer.class);
	}

}
