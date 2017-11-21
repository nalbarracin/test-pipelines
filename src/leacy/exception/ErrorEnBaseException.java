package leacy.exception;

public class ErrorEnBaseException extends ExcepcionControlada{

	private static final long serialVersionUID = 5569033824409949266L;

	public ErrorEnBaseException(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}
}
