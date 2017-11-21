package main.leacy.client.segurizable;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import main.leacy.client.bean.LoginBean;

@ManagedBean
@SessionScoped
public class Autorizador implements Serializable {
	private static final long serialVersionUID = 1738909587760422416L;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public Autorizador() {

	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public boolean autoriza(String permiso) {

		return login.getUsuario() != null;
	}
}
