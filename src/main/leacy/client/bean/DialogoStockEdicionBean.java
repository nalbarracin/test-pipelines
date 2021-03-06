
package main.leacy.client.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import leacy.admin.wsclient.restService.AdministradorWS;
import leacy.exception.ExcepcionControlada;
import leacy.modelo.CartaStock;
import leacy.modelo.ColorCarta;
import leacy.modelo.Edicion;
import leacy.modelo.EstadoCarta;
import leacy.modelo.Filtro;
import leacy.modelo.IdiomaCarta;
import leacy.modelo.RarezaCarta;
import main.leacy.client.modelo.CartaStockVista;

@ManagedBean
@ViewScoped
public class DialogoStockEdicionBean implements Serializable {

	private static final long serialVersionUID = 4721193493557950214L;

	private List<CartaStockVista> cartas;
	private Edicion edicion;
	private String filtroNombre;
	private ColorCarta filtroColor;
	private List<ColorCarta> colores;
	private RarezaCarta filtroRareza;
	private List<RarezaCarta> rarezas;
	
	private Boolean estado_nm = false;
	private Boolean estado_sp = false;
	private Boolean estado_mp = false;	
	private Boolean idioma_ingles = false;
	private Boolean idioma_espaniol = false;
	private Boolean idioma_otro = false;
	private Boolean mostrar_sin_stock = false;

	private ArrayList<CartaStockVista> cartasFiltradas;

	public DialogoStockEdicionBean() {
		setColores(ColorCarta.getAll());
		setRarezas(RarezaCarta.getAll());
	
	}
	
	public void setearDefaults(boolean estadoNM, boolean estadoSP, boolean estadoMP, 
							   boolean idiomaEN, boolean idiomaES, boolean idiomaOT, 
							   boolean mostrarSinStock) {
		setEstado_nm(estadoNM);
		setEstado_sp(estadoSP);
		setEstado_mp(estadoMP);		
		setIdioma_ingles(idiomaEN);
		setIdioma_espaniol(idiomaES);
		setIdioma_otro(idiomaOT);		
		setMostrar_sin_stock(mostrarSinStock);
		
	}
	
	public String getTituloDialog() {
		if (getEdicion() == null) {
			return "Stock";
		}
		return "Stock - " + getEdicion().getNombreEdicion();
	}

	public String imagenEdicion(CartaStockVista carta) {
		return carta.getEdicion().getRutaIcono();
	}

	public void actualizarListado() throws ExcepcionControlada {
		if (this.edicion == null) {
			return;
		}
		if (this.cartas == null) {
			this.setCartas(obtenerCartasStock());
		}
		filtrarListado();
	}

	private void filtrarListado() {
		ArrayList<CartaStockVista> output = new ArrayList<CartaStockVista>();
		output.addAll(cartas);
		ArrayList<CartaStockVista> aux = new ArrayList<CartaStockVista>();
		aux.addAll(cartas);
		
		// filtro con o sin stock
		if (!this.getMostrar_sin_stock()) {
			for (CartaStockVista carta : aux) {
				if (carta.getStockEstandar() <= 0 && carta.getStockFoil() <= 0) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}

		// filtro por nombre en espaniol o ingles
		if (this.getFiltroNombre() != null && !this.getFiltroNombre().trim().contentEquals("")) {
			for (CartaStockVista carta : aux) {
				if ((!carta.getNombre().toUpperCase().contains(this.getFiltroNombre().trim().toUpperCase())) && !carta
						.getNombreEspaniol().toUpperCase().contains(this.getFiltroNombre().trim().toUpperCase())) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}
		// filtro por color
		if (this.getFiltroColor() != null) {
			for (CartaStockVista carta : aux) {
				if (!carta.getColor().equals(this.getFiltroColor())) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}
		// filtro por rareza
		if (this.getFiltroRareza() != null) {
			for (CartaStockVista carta : aux) {
				if (carta.getRareza() != null && !carta.getRareza().equals(this.getFiltroRareza())) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}
		// filtro por idioma
		if (!this.getIdioma_ingles()) {
			for (CartaStockVista carta : aux) {
				if (carta.getIdioma() != null && carta.getIdioma().equals(IdiomaCarta.Ingles)) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}
		if (!this.getIdioma_espaniol()) {
			for (CartaStockVista carta : aux) {
				if (carta.getIdioma() != null && carta.getIdioma().equals(IdiomaCarta.Espaniol)) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}
		if (!this.getIdioma_otro()) {
			for (CartaStockVista carta : aux) {
				if (carta.getIdioma() != null && carta.getIdioma().equals(IdiomaCarta.Otros)) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}
		
		// filtro por estado
		if (!this.getEstado_mp()) {
			for (CartaStockVista carta : aux) {
				if (carta.getEstado() != null && carta.getEstado().equals(EstadoCarta.MP)) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}
		if (!this.getEstado_sp()) {
			for (CartaStockVista carta : aux) {
				if (carta.getEstado() != null && carta.getEstado().equals(EstadoCarta.SP)) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}
		if (!this.getEstado_nm()) {
			for (CartaStockVista carta : aux) {
				if (carta.getEstado() != null && carta.getEstado().equals(EstadoCarta.NM)) {
					output.remove(carta);
				}
			}
			aux.clear();
			aux.addAll(output);
		}

		setCartasFiltradas(output);
	}

	public void aplicarCambiosStock() throws ExcepcionControlada {
		this.actualizarStock();
		this.cartas = null;
		this.actualizarListado();
	}

	public void guardarStock() throws ExcepcionControlada {
		this.actualizarStock();
		RequestContext.getCurrentInstance().execute("PF('dialogoStock').hide();");
	}

	private void actualizarStock() throws ExcepcionControlada {
		List<CartaStock> cartasModificadas = new ArrayList<CartaStock>();

		for (CartaStockVista cartaStockVista : cartas) {
			if (cartaStockVista.getEstandarAdicionales() != 0 || cartaStockVista.getFoilAdicionales() != 0) {
				cartasModificadas.add(cartaStockVista.obtenerCartaStock());
			}
		}
		AdministradorWS.actualizarStock(cartasModificadas);
	}

	private List<CartaStockVista> obtenerCartasStock() throws ExcepcionControlada {
		if (edicion == null) {
			return null;
		}
		Filtro filtro = new Filtro();
		filtro.setEdicion(edicion);
		return obtenerCartasStockVista(AdministradorWS.buscarStockPorEdicion(filtro));
	}

	private ArrayList<CartaStockVista> obtenerCartasStockVista(List<CartaStock> listado) {
		ArrayList<CartaStockVista> lista = new ArrayList<CartaStockVista>();
		for (CartaStock cartaStock : listado) {
			lista.add(new CartaStockVista(cartaStock));
		}
		return lista;
	}

	public List<CartaStockVista> getCartas() {
		return cartas;
	}

	public void setCartas(List<CartaStockVista> cartas) {
		this.cartas = cartas;
	}

	public Edicion getEdicion() {
		return edicion;
	}

	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
		this.cartas = null;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public ArrayList<CartaStockVista> getCartasFiltradas() {
		try {
			this.actualizarListado();
		} catch (ExcepcionControlada e) {
			e.printStackTrace();
		}
		return cartasFiltradas;
	}

	public void setCartasFiltradas(ArrayList<CartaStockVista> cartasFiltradas) {
		this.cartasFiltradas = cartasFiltradas;
	}

	public ColorCarta getFiltroColor() {
		return filtroColor;
	}

	public void setFiltroColor(ColorCarta filtroColor) {
		this.filtroColor = filtroColor;
	}

	public List<ColorCarta> getColores() {
		return colores;
	}

	public void setColores(List<ColorCarta> colores) {
		this.colores = colores;
	}

	public List<RarezaCarta> getRarezas() {
		return rarezas;
	}

	public void setRarezas(List<RarezaCarta> rarezas) {
		this.rarezas = rarezas;
	}

	public RarezaCarta getFiltroRareza() {
		return filtroRareza;
	}

	public void setFiltroRareza(RarezaCarta filtroRareza) {
		this.filtroRareza = filtroRareza;
	}

	public Boolean getEstado_nm() {
		return estado_nm;
	}

	public void setEstado_nm(Boolean estado_nm) {
		this.estado_nm = estado_nm;
	}

	public Boolean getEstado_sp() {
		return estado_sp;
	}

	public void setEstado_sp(Boolean estado_sp) {
		this.estado_sp = estado_sp;
	}

	public Boolean getEstado_mp() {
		return estado_mp;
	}

	public void setEstado_mp(Boolean estado_mp) {
		this.estado_mp = estado_mp;
	}

	public Boolean getIdioma_ingles() {
		return idioma_ingles;
	}

	public void setIdioma_ingles(Boolean idioma_ingles) {
		this.idioma_ingles = idioma_ingles;
	}

	public Boolean getIdioma_espaniol() {
		return idioma_espaniol;
	}

	public void setIdioma_espaniol(Boolean idioma_espaniol) {
		this.idioma_espaniol = idioma_espaniol;
	}

	public Boolean getIdioma_otro() {
		return idioma_otro;
	}

	public void setIdioma_otro(Boolean idioma_otro) {
		this.idioma_otro = idioma_otro;
	}

	public Boolean getMostrar_sin_stock() {
		return mostrar_sin_stock;
	}

	public void setMostrar_sin_stock(Boolean mostrar_sin_stock) {
		this.mostrar_sin_stock = mostrar_sin_stock;
	}

	
}
