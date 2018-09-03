package challenge.api.v1.dao.impl;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import challenge.api.v1.dao.BaseDao;

import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;

public class TokenDao extends BaseDao {
	public TokenDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	private final String CREATE_TOKEN_SQL = "INSERT into Token (uid, token) values (?, ?);";
	
	public String createToken (Long userId) {
		byte[] tokenBytes = userId.toString().concat(";").concat(UUID.randomUUID().toString()).getBytes();
		return Base64.encode(tokenBytes);
	}
	
	public String insertToken(Long userId) {
		String insertedToken = createToken(userId);
		jdbcTemplate.update(CREATE_TOKEN_SQL, new Object[] {userId, insertedToken});
		return insertedToken;
	}
}
