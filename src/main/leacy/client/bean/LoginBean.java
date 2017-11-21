package main.leacy.client.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import leacy.exception.ExcepcionControlada;
import leacy.modelo.Usuario;
import main.leacy.client.seguridad.Utilidades;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username, password;
	private Usuario usuario;
	
	public LoginBean() throws ExcepcionControlada {
		
	}
	
	public String login() throws ExcepcionControlada {
		if(username==null || username.isEmpty() || username.trim().contentEquals("")){
			throw new ExcepcionControlada("Debe ingresar un usuario", null);
		}
		if(password==null || password.isEmpty() || password.trim().contentEquals("")){
			throw new ExcepcionControlada("Debe ingresar una contraseña", null);
		}
		
    	//TODO ejecutar la validacion de usuario 
		//AdministradorUsuario admin = new AdministadorUsuario();
		
		//redirecciono a mi pantalla principal
        return "stock";
    }
	
	public void logout() throws Exception {
		HttpSession session = Utilidades.getSession();
		this.usuario = null;
		session.invalidate();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/login.xhtml");		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
