package main.leacy.client.modelo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import leacy.modelo.CartaStock;
import leacy.modelo.ColorCarta;
import leacy.modelo.Edicion;
import leacy.modelo.EstadoCarta;
import leacy.modelo.IdiomaCarta;
import leacy.modelo.RarezaCarta;

public class CartaStockVista implements Serializable{

	private static final long serialVersionUID = -756160201626246379L;

	private Integer idStock;
	private Integer idCatalogo;
	private String nombre;
	private String nombreEspaniol;
	private RarezaCarta rareza;
	private String coste;
	private String mana;
	private ColorCarta color;
	private int stockEstandar;
	private int stockFoil;
	private Integer estandarAdicionales;
	private Integer foilAdicionales;
	private EdicionVista edicion;
	private String numero;
	private EstadoCarta estado;
	private IdiomaCarta idioma;
		
	public CartaStockVista(CartaStock cartaStock) {
		setIdStock(cartaStock.getIdStock());
		setIdCatalogo(cartaStock.getIdCatalogo());
		setNombre(cartaStock.getNombre());
		setNombreEspaniol(cartaStock.getNombreEspaniol());
		setRareza(cartaStock.getRareza());
		setCoste(cartaStock.getCoste());
		setNumero(cartaStock.getNumero());
		setColor(cartaStock.getColor());
		setMana(cartaStock.getMana());
		setEdicion(new EdicionVista(cartaStock.getEdicion()));
		setStockEstandar(cartaStock.getStockEstandar());
		setStockFoil(cartaStock.getStockFoil());
		setEstado(cartaStock.getEstado());
		setIdioma(cartaStock.getIdioma());
		setEstandarAdicionales(0);
		setFoilAdicionales(0);
	}
	
	public CartaStock obtenerCartaStock(){
		CartaStock carta = new CartaStock();
		carta.setIdStock(getIdStock());
		carta.setIdCatalogo(getIdCatalogo());
		carta.setNombre(getNombre());
		carta.setNombreEspaniol(getNombreEspaniol());
		carta.setCoste(getCoste());
		carta.setColor(getColor());
		carta.setMana(getMana());
		carta.setNumero(getNumero());
		carta.setEdicion(new Edicion().acronimoMCI(edicion.getAcronimoMCI()).nombreEdicion(edicion.getNombreEdicion()));
		carta.setRareza(getRareza());
		carta.setEstado(getEstado());
		carta.setIdioma(getIdioma());
		carta.setStockEstandar(getStockEstandar()+ getEstandarAdicionales());
		carta.setStockFoil(getStockFoil()+ getFoilAdicionales());
		return carta;
	}
	
	public Integer getEstandarAdicionales() {
		return estandarAdicionales;
	}
	public void setEstandarAdicionales(Integer estandarAdicionales) {
		this.estandarAdicionales = estandarAdicionales;
	}
	public Integer getFoilAdicionales() {
		return foilAdicionales;
	}
	public void setFoilAdicionales(Integer foilAdicionales) {
		this.foilAdicionales = foilAdicionales;
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

	public EdicionVista getEdicion() {
		return edicion;
	}

	public void setEdicion(EdicionVista edicion) {
		this.edicion = edicion;
	}
	
	public char getSymbol() throws UnsupportedEncodingException{
		return this.getEdicion().getSymbol();		
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
		return rareza;
	}

	public void setRareza(RarezaCarta rareza) {
		this.rareza = rareza;
	}

	public String getUrlCarta() {
		// soi/29.jpg
		String val = "https://magiccards.info/scans/";
		val = val + this.getIdioma().getClaveUrl() + "/";
		val = val + this.getEdicion().getAcronimoMCI().toLowerCase() + "/";
		val = val + this.getNumero() + ".jpg";
		return val;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getUrlStarcityGames() {		
		String base = "http://sales.starcitygames.com/search.php?substring=";
		return base+this.getNombre().replace(" ", "+");
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
