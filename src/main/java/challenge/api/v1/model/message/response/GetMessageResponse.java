package challenge.api.v1.model.message.response;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.PositiveOrZero;

import challenge.api.v1.model.message.Message;

public class GetMessageResponse implements Serializable {

	private static final long serialVersionUID = 5681587567435026519L;
	
	@PositiveOrZero.List(value = { @PositiveOrZero })
	List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "GetMessageResponse [messages=" + messages + "]";
	}
	
	
}
