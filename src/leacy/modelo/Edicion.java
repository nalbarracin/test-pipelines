package leacy.modelo;

import java.io.Serializable;

public class Edicion implements Serializable{
	
	private static final long serialVersionUID = -3477648011633359158L;
	
	private String nombreEdicion;
	private String acronimoMCI;
	private String unicode;
	
	public Edicion(){
		setNombreEdicion("");
		setAcronimoMCI("");
		setUnicode("");
	}
	
	public Edicion unicode(String unicode){
		setUnicode(unicode);
		return this;
	}
	
	public Edicion nombreEdicion (String nombreEdicion){
		setNombreEdicion(nombreEdicion);
		return this;
	}
	
	public Edicion acronimoMCI(String acronimoMCI){
		setAcronimoMCI(acronimoMCI);
		return this;
	}
	
	@Override
	public String toString(){
		return this.nombreEdicion;
	}
	
	public String getNombreEdicion() {
		return nombreEdicion;
	}
	public void setNombreEdicion(String nombreEdicion) {
		this.nombreEdicion = nombreEdicion;
	}
	public String getAcronimoMCI() {
		return acronimoMCI;
	}
	public void setAcronimoMCI(String acronimoMCI) {
		this.acronimoMCI = acronimoMCI;
	}

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}
}
