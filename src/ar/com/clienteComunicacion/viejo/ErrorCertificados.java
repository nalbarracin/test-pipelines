package ar.com.clienteComunicacion.viejo;

public class ErrorCertificados extends ErrorTransaccionException {

	private static final long serialVersionUID = -4850249907179499159L;

	public ErrorCertificados(Throwable e) {
		super("ECC [001] - No se pudo recuperar los certificados correspondientes", e);
	}
}