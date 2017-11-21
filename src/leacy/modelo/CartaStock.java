package leacy.modelo;

import java.io.Serializable;

public class CartaStock implements Serializable, Cloneable, Comparable<CartaStock>{
	
	private Integer idStock;
	private Integer idCatalogo;
	private String nombre;
	private String nombreEspaniol;
	private String coste;
	private String mana;
	private ColorCarta color;
	private Edicion edicion;
	private RarezaCarta rareza;
	private int stockEstandar;
	private int stockFoil;
	private String numero;
	private EstadoCarta estado;
	private IdiomaCarta idioma;

	private static final long serialVersionUID = 3672884266799680419L;

	
	public CartaStock(){
		setIdStock(0);
		setNombre("");
		setNombreEspaniol("");
		setCoste("");
		setStockEstandar(0);
		setStockFoil(0);
		setNumero("");
		setRareza(RarezaCarta.Common);
		setMana("");
	}
	
	public CartaStock(CartaCatalogo cartaCatalogo){
	}

	public int getStockEstandar() {
		return stockEstandar;
	}

	public void setStockEstandar(int stockEstandar) {
		this.stockEstandar = stockEstandar;
	}

	public int getStockFoil() {
		return stockFoil;
	}

	public void setStockFoil(int stockFoil) {
		this.stockFoil = stockFoil;
	}

	public Integer getIdStock() {
		return idStock;
	}

	public void setIdStock(Integer idStock) {
		this.idStock = idStock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int compareTo(CartaStock o) {
		if(this.getIdStock()==null){
			return -1;
		}
		return this.getIdStock().compareTo(((CartaStock)o).getIdStock());		
	}

	public Edicion getEdicion() {
		return edicion;
	}

	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}

	public String getCoste() {
		return coste;
	}

	public void setCoste(String coste) {
		this.coste = coste;
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

	public String getNombreEspaniol() {
		return nombreEspaniol;
	}

	public void setNombreEspaniol(String nombreEspaniol) {
		this.nombreEspaniol = nombreEspaniol;
	}

	public RarezaCarta getRareza() {
		if(this.getEdicion().getAcronimoMCI().contentEquals("TSTS")){
			return RarezaCarta.TimeShifted;
		}
		return rareza;
	}

	public void setRareza(RarezaCarta rareza) {
		this.rareza = rareza;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public EstadoCarta getEstado() {
		return estado;
	}

	public void setEstado(EstadoCarta estado) {
		this.estado = estado;
	}

	public IdiomaCarta getIdioma() {
		return idioma;
	}

	public void setIdioma(IdiomaCarta idioma) {
		this.idioma = idioma;
	}		
}
