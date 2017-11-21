package ar.com.clienteComunicacion.viejo;

public class ErrorTimeoutInvocacionWS extends ErrorTransaccionException {

	private static final long serialVersionUID = 3972404990862676074L;

	public ErrorTimeoutInvocacionWS(Throwable e) {
		super("ECC [004] - Se supero el tiempo de espera para la respuesta del servicio.", e);
	}
}