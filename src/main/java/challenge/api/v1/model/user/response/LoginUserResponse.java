package challenge.api.v1.model.user.response;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class LoginUserResponse implements Serializable {

	private static final long serialVersionUID = 7693644831855777378L;

	@PositiveOrZero
	private long id;
	
	@NotNull
	@NotBlank
	private String token;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LoginUserResponse [id=" + id + ", token=" + token + "]";
	}
}
