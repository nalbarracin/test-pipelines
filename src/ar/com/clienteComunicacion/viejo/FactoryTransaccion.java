package ar.com.clienteComunicacion.viejo;

import java.util.Date;

public class FactoryTransaccion {

	public static Transaccion recuperarIntancia(String respuesta, Date fechaEnvio, Date fechaRespuesta) {
		
		Transaccion transaccion = new Transaccion();
		transaccion.setRespuesta(respuesta);
		transaccion.setFechaEnvio(fechaEnvio);
		transaccion.setFechaRespuesta(fechaRespuesta);
		
		return transaccion;
	}
}