package challenge.api.v1.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import challenge.api.v1.dao.BaseDao;
import challenge.api.v1.model.user.request.UserRequest;
import challenge.api.v1.model.user.response.LoginUserResponse;

public class UserTokenDao extends BaseDao {
	protected UserDao userDao;
	protected TokenDao tokenDao;
	
	public UserTokenDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		userDao = new UserDao(this.jdbcTemplate);
		tokenDao = new TokenDao(this.jdbcTemplate);
	}

	public LoginUserResponse createUserToken(UserRequest userRequest) {
		Long userId = userDao.getUserId(userRequest);
		String token = tokenDao.insertToken(userId);
		
		LoginUserResponse response = new LoginUserResponse();
		response.setId(userId);
		response.setToken(token);
		
		return response;
	}
}
