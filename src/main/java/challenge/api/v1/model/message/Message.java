package challenge.api.v1.model.message;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Message implements Serializable{	
	private static final long serialVersionUID = 2030716741319531039L;

	@NotNull
	@Min(0)
	private long id;
	
	@NotNull
	private LocalDateTime timestamp;
	
	@NotNull
	@Min(0)
	private long sender;
	
	@NotNull
	@Min(0)
	private long recipient;
	
	@NotNull
	private Content content;

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

	public long getSender() {
		return sender;
	}

	public void setSender(long sender) {
		this.sender = sender;
	}

	public long getRecipient() {
		return recipient;
	}

	public void setRecipient(long recipient) {
		this.recipient = recipient;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", timestamp=" + timestamp + ", sender=" + sender + ", recipient=" + recipient
				+ ", content=" + content.toString() + "]";
	}
}
