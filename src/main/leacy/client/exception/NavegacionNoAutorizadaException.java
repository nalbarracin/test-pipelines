package main.leacy.client.exception;

public class NavegacionNoAutorizadaException extends RuntimeException {

	private static final long serialVersionUID = -4014924664959396243L;

	public NavegacionNoAutorizadaException() {
		super("Navegacion no autorizada");
	}
}
