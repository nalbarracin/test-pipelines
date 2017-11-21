package main.leacy.client.modelo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import leacy.modelo.Edicion;

public class EdicionVista {

	private String rutaIcono;
	
	private String nombreEdicion;
	private String acronimoMCI;
	private String unicode;
	
	public EdicionVista(Edicion edicion) {
		setNombreEdicion(edicion.getNombreEdicion());
		setAcronimoMCI(edicion.getAcronimoMCI());
		setUnicode(edicion.getUnicode());
	}
	
	public char getSymbol() throws UnsupportedEncodingException{
		if(this.getUnicode().contentEquals("")){
			return Character.MIN_VALUE;
		}
		String hex = this.getUnicode();
		return (char)Integer.parseInt(hex, 16);
	}
	
	public EdicionVista rutaIcono(String ruta){
		this.setRutaIcono(ruta);
		return this;
	}
	
	public EdicionVista() {
		this.setRutaIcono("");			
	}
	
	public String getRutaIcono() {
		return rutaIcono;
	}
	
	public void setRutaIcono(String rutaIcono) {
		this.rutaIcono = rutaIcono;
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
	
	public static List<EdicionVista> obtenerEdicionesVista(List<Edicion> listaEdiciones) {
		List<EdicionVista> lista = new ArrayList<EdicionVista>();

		for (Edicion edicion : listaEdiciones) {
			lista.add(new EdicionVista(edicion));
		}

		return lista;
	}

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}
	
	
}
