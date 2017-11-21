package leacy.modelo;

import java.util.ArrayList;
import java.util.List;

public enum EstadoCarta {

	NM	("NM", "NM"),
	SP	("SP", "SP"),
	MP	("MP", "MP");
	
	
	private String codigo;
	private String descripcion;

	EstadoCarta(String codigo, String descripcion) {
		this.setDescripcion(descripcion);
		this.setCodigo(codigo);
	}

	public static EstadoCarta getEstadoCarta(String codigo) {
		for (EstadoCarta estado : EstadoCarta.values()) {
			if (estado.codigo.equalsIgnoreCase(codigo)) {
				return estado;
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

	public static List<EstadoCarta> getAll() {
		EstadoCarta[] cs = values();
		ArrayList<EstadoCarta> estados = new ArrayList<EstadoCarta>();
		
		for(EstadoCarta estado: cs){
			estados.add(estado);
		}
		return estados;
	}
	
	@Override
	public String toString(){
		return descripcion;
	}
	
}
