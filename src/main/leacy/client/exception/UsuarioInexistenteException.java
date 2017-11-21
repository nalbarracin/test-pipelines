package main.leacy.client.exception;

import leacy.exception.ExcepcionControlada;

public class UsuarioInexistenteException extends ExcepcionControlada {

	private static final long serialVersionUID = 336825771765498159L;

	public UsuarioInexistenteException() {
		super("El usuario ingresado no existe en el sistema", null);
	}

}
