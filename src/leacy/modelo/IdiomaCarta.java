package leacy.modelo;

import java.util.ArrayList;
import java.util.List;

public enum IdiomaCarta {

	Espaniol	("es", "en", "Español"),
	Ingles		("en", "en", "Ingles"),
	Otros		("ot", "en", "Otros");
	
	private String codigo;
	private String claveUrl;
	private String descripcion;
	

	IdiomaCarta(String codigo, String claveUrl, String descripcion) {
		this.setDescripcion(descripcion);
		this.setCodigo(codigo);
		this.setClaveUrl(claveUrl);
	}

	public static IdiomaCarta getIdiomaCarta(String codigo) {
		for (IdiomaCarta idioma : IdiomaCarta.values()) {
			if (idioma.codigo.equalsIgnoreCase(codigo)) {
				return idioma;
			}
		}
		return null;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static List<IdiomaCarta> getAll() {
		IdiomaCarta[] cs = values();
		ArrayList<IdiomaCarta> idiomas = new ArrayList<IdiomaCarta>();
		
		for(IdiomaCarta idioma: cs){
			idiomas.add(idioma);
		}
		return idiomas;
	}
	
	@Override
	public String toString(){
		return descripcion;
	}

	public String getClaveUrl() {
		return claveUrl;
	}

	public void setClaveUrl(String claveUrl) {
		this.claveUrl = claveUrl;
	}


	
}
