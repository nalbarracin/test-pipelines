package main.leacy.client.exception.handler;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -4205665289619922583L;

	public static void addMessageError(String motivo, String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, motivo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void addMessageAlerta(String motivo, String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, motivo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void addMessageAviso(String motivo, String mensaje) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, motivo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
