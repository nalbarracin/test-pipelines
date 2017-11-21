package leacy.modelo;

import java.io.Serializable;

public class CartaCatalogo implements Serializable, Cloneable, Comparable<CartaCatalogo>{
	
	private static final long serialVersionUID = -5730185392905043467L;
	private String nombre;
	private String nombreEspaniol;
	private String costo;
	private String mana;
	private ColorCarta color;
	private String tipo;
	private String stockEstandar;
	private String stockFoil;

	public CartaCatalogo() {
		setNombre("");
		setNombreEspaniol("");
		setCosto("");
		setTipo("");
	};

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int compareTo(CartaCatalogo o) {
		if(o.getNombre()==null){
			return -1;
		}
		return o.getNombre().compareTo(this.getNombre());
	}

	public String getNombreEspaniol() {
		return nombreEspaniol;
	}

	public void setNombreEspaniol(String nombreEspaniol) {
		this.nombreEspaniol = nombreEspaniol;
	}

	public String getMana() {
		return mana;
	}

	public void setMana(String mana) {
		this.mana = mana;
	}

	public ColorCarta getColor() {
		return color;
	}

	public void setColor(ColorCarta color) {
		this.color = color;
	}

	public String getStockEstandar() {
		return stockEstandar;
	}

	public void setStockEstandar(String stockEstandar) {
		this.stockEstandar = stockEstandar;
	}

	public String getStockFoil() {
		return stockFoil;
	}

	public void setStockFoil(String stockFoil) {
		this.stockFoil = stockFoil;
	}

}
