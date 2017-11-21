package leacy.exception;

public class ExcepcionControlada extends Exception {

	private static final long serialVersionUID = 7513293790954080424L;

	public ExcepcionControlada(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}
}