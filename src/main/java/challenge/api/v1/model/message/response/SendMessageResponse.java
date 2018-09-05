package challenge.api.v1.model.message.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class SendMessageResponse implements Serializable {

	private static final long serialVersionUID = -2332611040793714500L;
	
	@Positive
	private long id;
	
	@NotNull
	private LocalDateTime timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "SendMessageResponse [id=" + id + ", timestamp=" + timestamp + "]";
	}
}
