package leacy.modelo;

import java.io.Serializable;

public class Filtro implements Serializable{

	private static final long serialVersionUID = -7614810329034794578L;

	private String nombre;
	private Edicion edicion;
	
	public Filtro(){
		setNombre("");
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Edicion getEdicion() {
		return edicion;
	}

	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}
	
}
