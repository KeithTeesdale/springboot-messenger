package challenge.api.v1.model.message.response;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class SendMessageResponse implements Serializable {

	private static final long serialVersionUID = -2332611040793714500L;
	
	@PositiveOrZero
	private BigInteger id;
	
	@NotNull
	private LocalDateTime timestamp;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
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
