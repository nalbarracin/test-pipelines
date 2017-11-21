
package main.leacy.client.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import leacy.admin.wsclient.restService.AdministradorWS;
import leacy.exception.ExcepcionControlada;
import leacy.modelo.Edicion;
import main.leacy.client.modelo.CartaStockVista;
import main.leacy.client.modelo.EdicionVista;

@ManagedBean
@ViewScoped
public class StockEdicionBean implements Serializable {

	private static final long serialVersionUID = 4721193493557950214L;

	@ManagedProperty(value = "#{dialogoStockEdicionBean}")
	private DialogoStockEdicionBean dialogoStockEdicionBean;
	
	private List<EdicionVista> ediciones;
	private List<EdicionVista> todasLasEdiciones;
	private List<CartaStockVista> cartas;
	private String nombreEdicion;

	
	public StockEdicionBean() {
		try {
			actualizarEdiciones();
		} catch (ExcepcionControlada e) {
			e.printStackTrace();
		}
	}
	
	public void abrirCatalogo(EdicionVista edicionVista) throws ExcepcionControlada{
		Edicion edicion = new Edicion()
				.acronimoMCI(edicionVista.getAcronimoMCI())
				.nombreEdicion(edicionVista.getNombreEdicion())
				.unicode(edicionVista.getUnicode());
		this.getDialogoStockEdicionBean().setearDefaults(true, true, true, true, true, true, true);
		this.getDialogoStockEdicionBean().setEdicion(edicion);
		this.getDialogoStockEdicionBean().actualizarListado();
		RequestContext.getCurrentInstance().execute("PF('dialogoStock').show();");
	}
	
	public List<CartaStockVista> getCartas() {
		return cartas;
	}

	public void setCartas(List<CartaStockVista> cartas) {
		this.cartas = cartas;
	}

	public List<EdicionVista> getEdiciones() {
		
		try {
			this.actualizarEdiciones();
		} catch (ExcepcionControlada e) {
			e.printStackTrace();
		}
		return ediciones;
	}

	private void actualizarEdiciones() throws ExcepcionControlada {
		List<EdicionVista> todas = obtenerEdiciones();
		if( this.getNombreEdicion()==null || this.getNombreEdicion().contentEquals("")){
			ediciones = todas;
			return;
		}
		List<EdicionVista> aux = new ArrayList<EdicionVista>();
		aux.addAll(todas);
		
		for(EdicionVista unaEdicion : todas){
			if( !unaEdicion.getNombreEdicion().toUpperCase().contains(
					this.getNombreEdicion().toUpperCase())){
				aux.remove(unaEdicion);
			}
		}
		ediciones = aux;		
	
		Collections.sort(ediciones, new Comparator<EdicionVista>() {

			@Override
			public int compare(EdicionVista o1, EdicionVista o2) {
				return o1.getNombreEdicion().compareTo(o2.getNombreEdicion());
			}
		});
		
	}

	private List<EdicionVista> obtenerEdiciones() throws ExcepcionControlada {
		if(getTodasLasEdiciones() == null){
			
			List<Edicion> listaEdiciones = AdministradorWS.getEdicionesConStock();
			
			setTodasLasEdiciones(EdicionVista.obtenerEdicionesVista(listaEdiciones));
			
		}	
		return getTodasLasEdiciones();
	}

	public void setEdiciones(List<EdicionVista> ediciones) {
		this.ediciones = ediciones;
	}
	
	public List<EdicionVista> getTodasLasEdiciones() {
		return todasLasEdiciones;
	}

	public void setTodasLasEdiciones(List<EdicionVista> todasLasEdiciones) {
		this.todasLasEdiciones = todasLasEdiciones;
	}

	public DialogoStockEdicionBean getDialogoStockEdicionBean() {
		return dialogoStockEdicionBean;
	}

	public void setDialogoStockEdicionBean(DialogoStockEdicionBean dialogoStockEdicionBean) {
		this.dialogoStockEdicionBean = dialogoStockEdicionBean;
	}

	public String getNombreEdicion() {
		return nombreEdicion;
	}

	public void setNombreEdicion(String nombreEdicion) {
		this.nombreEdicion = nombreEdicion;
	}
	

}
