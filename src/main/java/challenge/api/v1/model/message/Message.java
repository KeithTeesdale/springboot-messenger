package challenge.api.v1.model.message;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Message implements Serializable{	
	private static final long serialVersionUID = 2030716741319531039L;

	@NotNull
	@Min(0)
	private BigInteger id;
	
	@NotNull
	private LocalDateTime timestamp;
	
	@NotNull
	@Min(0)
	private String sender;
	
	@NotNull
	@Min(0)
	private String recipient;
	
	@NotNull
	private Content content;

	@Override
	public String toString() {
		return "Message [id=" + id + ", timestamp=" + timestamp + ", sender=" + sender + ", recipient=" + recipient
				+ ", content=" + content.toString() + "]";
	}
}
