package challenge.api.v1.model.message.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import challenge.api.v1.model.message.Content;

public class SendMessageRequest implements Serializable {

	private static final long serialVersionUID = -1337541403001985817L;
	
	@Positive
	private long sender;
	
	@Positive
	private long recipient;
	
	@NotNull
	private Content content;

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
		return "SendMessageRequest [sender=" + sender + ", recipient=" + recipient + ", content=" + content + "]";
	}
	
	
}
