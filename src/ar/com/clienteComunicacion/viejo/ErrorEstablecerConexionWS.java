package ar.com.clienteComunicacion.viejo;

public class ErrorEstablecerConexionWS extends ErrorTransaccionException {

	private static final long serialVersionUID = 1743029915269366442L;

	public ErrorEstablecerConexionWS(Exception e) {
		super("ECC [010] - No se pudo efectuar la transacci�n. EI", e);
	}
}