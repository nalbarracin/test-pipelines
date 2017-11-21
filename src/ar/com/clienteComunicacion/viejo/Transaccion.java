package ar.com.clienteComunicacion.viejo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaccion {

	private String respuesta;
	private Date fechaEnvio;
	private Date fechaRespuesta;
	
	@Override
	public String toString() {
		
		return "[Respuesta: "+this.getRespuesta()+"\n"
			 + "Inicio:\t"+ formatearFecha( this.getFechaEnvio() )+"\n"
			 + "Fin:\t"+ formatearFecha( this.getFechaEnvio() )+"\n"
			 + "Tiempo:\t"+ recuperarTiempo() +"]";
	}
	
	public String recuperarTiempo() {
		
		return recuperarTiempo((this.getFechaRespuesta().getTime() - this.getFechaEnvio().getTime()) / 1000);
	}
	
	private static String recuperarTiempo(long tiempo) {
		
		try {
			return (((int) tiempo / 60) + " min. " + ((int) tiempo % 60) + " seg."); 
		} catch (Exception e) {
			return "0";
		}
	}

	private static String formatearFecha(Date fecha) {
		return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(fecha);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
		result = prime * result + ((fechaRespuesta == null) ? 0 : fechaRespuesta.hashCode());
		result = prime * result + ((respuesta == null) ? 0 : respuesta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		if (fechaEnvio == null) {
			if (other.fechaEnvio != null)
				return false;
		} else if (!fechaEnvio.equals(other.fechaEnvio))
			return false;
		if (fechaRespuesta == null) {
			if (other.fechaRespuesta != null)
				return false;
		} else if (!fechaRespuesta.equals(other.fechaRespuesta))
			return false;
		if (respuesta == null) {
			if (other.respuesta != null)
				return false;
		} else if (!respuesta.equals(other.respuesta))
			return false;
		return true;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
}