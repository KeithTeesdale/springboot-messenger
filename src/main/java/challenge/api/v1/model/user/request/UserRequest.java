package challenge.api.v1.model.user.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest implements Serializable{
	
	private static final long serialVersionUID = 4674960029516050161L;
	
	@NotNull
    @JsonProperty("username")
	private String username;
	
	@NotNull
	@JsonProperty("password")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequest [username=" + username + ", password=" + password + "]";
	}
}
