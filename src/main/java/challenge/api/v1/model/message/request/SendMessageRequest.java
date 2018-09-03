package challenge.api.v1.model.message.request;

import java.io.Serializable;
import java.math.BigInteger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import challenge.api.v1.model.message.Content;

public class SendMessageRequest implements Serializable {

	private static final long serialVersionUID = -1337541403001985817L;
	
	@Positive
	private BigInteger sender;
	
	@Positive
	private BigInteger recipient;
	
	@NotNull
	private Content content;

	public BigInteger getSender() {
		return sender;
	}

	public void setSender(BigInteger sender) {
		this.sender = sender;
	}

	public BigInteger getRecipient() {
		return recipient;
	}

	public void setRecipient(BigInteger recipient) {
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
