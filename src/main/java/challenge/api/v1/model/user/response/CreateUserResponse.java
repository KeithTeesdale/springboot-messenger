package challenge.api.v1.model.user.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserResponse implements Serializable {
	
	private static final long serialVersionUID = -442303688099126136L;
	
	@JsonProperty("id")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CreateUserResponse [id=" + id + "]";
	}
}
