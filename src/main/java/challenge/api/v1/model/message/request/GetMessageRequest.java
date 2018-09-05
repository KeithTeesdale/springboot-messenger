package challenge.api.v1.model.message.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

public class GetMessageRequest implements Serializable {

	private static final long serialVersionUID = 4899467439960469739L;
	
	@Positive
	private long recipient;
	
	@Positive
	private int start;
	
	@Null
	@Min(1)
	private int limit;
}
