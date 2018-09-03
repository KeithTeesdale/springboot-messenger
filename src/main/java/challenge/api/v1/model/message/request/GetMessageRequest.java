package challenge.api.v1.model.message.request;

import java.io.Serializable;
import java.math.BigInteger;

import javax.validation.constraints.PositiveOrZero;

public class GetMessageRequest implements Serializable {

	private static final long serialVersionUID = 4899467439960469739L;
	
	@PositiveOrZero
	private BigInteger recipient;
	
	@PositiveOrZero
	private int start;
	
	@PositiveOrZero
	private int limit;
}
