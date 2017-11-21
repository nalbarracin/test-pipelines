package leacy.admin.wsclient;

import java.io.Serializable;

public abstract class Respuesta  implements Serializable{

	private static final long serialVersionUID = 8337920760522891966L;

	private EstadoMensaje estado;
	private String mensajeError;
	
	public EstadoMensaje getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoMensaje estado) {
		this.estado = estado;
	}
	
	public String getMensajeError() {
		return mensajeError;
	}
	
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}	
}