package leacy.modelo;

import java.io.Serializable;
import java.util.Date;

import leacy.exception.ExcepcionControlada;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Date login;

	public Usuario() {
		this.username = "";
		this.password = "";
	}

	public boolean isExpired() {

		int timeOut = 12000000;

		if (this.login.getTime() < new Date().getTime() - timeOut) {
			return true;
		} else {
			return false;
		}
	}

	public static Usuario validarUsuario(String username, String password) throws ExcepcionControlada {
		try {
			// TODO validar usuario
			return new Usuario();
		} catch (Exception e) {
			throw new ExcepcionControlada("Ocurrio un error al validar la contrasea", e);
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
