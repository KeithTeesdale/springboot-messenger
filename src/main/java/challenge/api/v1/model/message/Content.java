package challenge.api.v1.model.message;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Content implements Serializable {

	private static final long serialVersionUID = -7525257945560043691L;

	@NotNull
	@NotBlank
	private String text;
	
	@NotNull
	@NotBlank
	private Type type;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Content [text=" + text + ", type=" + type + "]";
	}
}
