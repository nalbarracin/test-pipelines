package ar.com.clienteComunicacion.viejo;

public class ErrorConectividadWS extends ErrorTransaccionException {

	private static final long serialVersionUID = -8989567437195342079L;

	public ErrorConectividadWS(Throwable e) {
		super("ECC [003] - No se pudo conectar con el WS", e);
	}
}