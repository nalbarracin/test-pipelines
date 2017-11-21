package leacy.modelo;

import java.util.ArrayList;
import java.util.List;

public enum RarezaCarta {

	Common		("C", "Common", "ss-common"),
	Uncommon	("U", "Uncommon", "ss-uncommon"),
	Rare		("R", "Rare", "ss-rare"),
	Mythic		("M", "Mythic", "ss-mythic"),
	TimeShifted ("T", "TimeShifted", "ss-timeshifted");
	
	private String codigo;
	private String descripcion;
	private String estilo;
	

	RarezaCarta(String codigo, String descripcion, String estilo) {
		this.setDescripcion(descripcion);
		this.setCodigo(codigo);
		this.setEstilo(estilo);
	}

	public static RarezaCarta getRarezaCarta(String codigo) {
		for (RarezaCarta rareza : RarezaCarta.values()) {
			if (rareza.codigo.equalsIgnoreCase(codigo)) {
				return rareza;
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

	public static List<RarezaCarta> getAll() {
		RarezaCarta[] cs = values();
		ArrayList<RarezaCarta> rarezas = new ArrayList<RarezaCarta>();
		
		for(RarezaCarta rareza: cs){
			rarezas.add(rareza);
		}
		return rarezas;
	}
	
	@Override
	public String toString(){
		return descripcion;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
}
