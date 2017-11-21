package ar.com.clienteComunicacion.viejo;

public class ErrorTransaccionException extends Exception {
	
	private static final long serialVersionUID = 2837896948570375357L;

	public ErrorTransaccionException(String m, Throwable t) {
		super(m, t);
	}
}