package leacy.modelo;

import java.util.ArrayList;
import java.util.List;

public enum ColorCarta {

	Black	("B", "Black"),
	White	("W", "White"),
	Green	("G", "Green"),
	Red		("R", "Red"),
	Blue	("U", "Blue"),
	Artifact("Art", "Artifact"),
	Land	("Lnd", "Land"),
	Gold	("Gld", "Gold");
	
	
	private String codigo;
	private String descripcion;

	ColorCarta(String codigo, String descripcion) {
		this.setDescripcion(descripcion);
		this.setCodigo(codigo);
	}

	public static ColorCarta getColorCarta(String codigo) {
		for (ColorCarta color : ColorCarta.values()) {
			if (color.codigo.equalsIgnoreCase(codigo)) {
				return color;
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

	public static List<ColorCarta> getAll() {
		ColorCarta[] cs = values();
		ArrayList<ColorCarta> colores = new ArrayList<ColorCarta>();
		
		for(ColorCarta color: cs){
			colores.add(color);
		}
		return colores;
	}
	
	@Override
	public String toString(){
		return descripcion;
	}
	
}
