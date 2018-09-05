package challenge.api.v1.dao.impl;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import challenge.api.v1.dao.BaseDao;

import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;

public class TokenDao extends BaseDao {
	private final String CREATE_TOKEN_SQL = "INSERT into Token (uid, token) values (?, ?);";
	private final String IS_VALID_TOKEN_SQL = "select (expiration > datetime('now')) as isValidToken from Token where token=?;";
	
	public TokenDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}
	
	public String createToken (Long userId) {
		byte[] tokenBytes = userId.toString().concat(";").concat(UUID.randomUUID().toString()).getBytes();
		return Base64.encode(tokenBytes);
	}
	
	public String insertToken(Long userId) {
		String insertedToken = createToken(userId);
		jdbcTemplate.update(CREATE_TOKEN_SQL, new Object[] {userId, insertedToken});
		return insertedToken;
	}
	
	public boolean isValidToken(String token) {
		Boolean isValidToken = jdbcTemplate.queryForObject(IS_VALID_TOKEN_SQL, new Object[]{token}, Boolean.class);
		
		if(isValidToken != null && isValidToken.booleanValue())
			return isValidToken;
		
		return false;
	}
}
