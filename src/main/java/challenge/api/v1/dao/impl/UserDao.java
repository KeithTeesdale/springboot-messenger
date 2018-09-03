package challenge.api.v1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import challenge.api.v1.dao.BaseDao;
import challenge.api.v1.model.user.request.UserRequest;
import challenge.api.v1.model.user.response.CreateUserResponse;

@Configuration
public class UserDao extends BaseDao{
	private final String CREATE_USER_SQL = "INSERT into User (username, password) values (?, ?);";
	private final String LOGIN_USER_SQL = "SELECT id from User where username=? and password=?";
	private final String CREATED_USER_ID = "id";
	
	public UserDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}
	
	public CreateUserResponse userCreate(UserRequest userRequest) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    		    new PreparedStatementCreator() {
    		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
    		            PreparedStatement ps = connection.prepareStatement(CREATE_USER_SQL, new String[]{CREATED_USER_ID});
    		            ps.setString(1, userRequest.getUsername());
    		            ps.setString(2, userRequest.getPassword());
    		            return ps;
    		        }
    		    },
    		    keyHolder);
    	
    	CreateUserResponse response = new CreateUserResponse();
    	response.setId(keyHolder.getKey().longValue());
    	
    	return response;
	}
	
	public Long getUserId(UserRequest userRequest) {
		return jdbcTemplate.queryForObject(LOGIN_USER_SQL, new String[]{userRequest.getUsername(),userRequest.getPassword()}, Long.class);
	}
}
