package ar.com.clienteComunicacion.viejo;

public class ErrorComunicacionWS extends ErrorTransaccionException {

	private static final long serialVersionUID = 7366213837082736900L;

	public ErrorComunicacionWS(Throwable e) {
		super("ECC [002] - Problemas al comunicarse con el WS", e);
	}
}