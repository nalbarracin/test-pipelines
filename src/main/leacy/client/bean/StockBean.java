package main.leacy.client.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import leacy.admin.wsclient.restService.AdministradorWS;
import leacy.exception.ExcepcionControlada;
import leacy.modelo.CartaCatalogo;
import leacy.modelo.Filtro;

@ManagedBean
@ViewScoped
public class StockBean implements Serializable {
	
	private static final long serialVersionUID = -4861868327519089419L;

	@ManagedProperty(value = "#{dialogoStockBean}")
	private DialogoStockBean dialogoStockBean;
		
	private String filtroNombre;
	private String filtroEdicion;
	private CartaCatalogo cartaSelected;
	private List<CartaCatalogo> cartasFiltradas;
	
	private Boolean cargarVacios = false;

	private StreamedContent file;
	
	public StockBean() throws ExcepcionControlada {
		this.setCartasFiltradas(new ArrayList<CartaCatalogo>());
	}
	
	public void actualizarCatalogo() throws ExcepcionControlada {
		cartasFiltradas = obtenerCartas();
	}
	
	public void generarExcel() throws ExcepcionControlada, FileNotFoundException {
		
		File archivo = AdministradorWS.generarExcelStock();
				
		file = new DefaultStreamedContent(new FileInputStream(archivo), "application/xls" , archivo.getName());
		
		RequestContext.getCurrentInstance().execute("PF('dialogoArchivo').show();");		
	}
	
	public void verCartasStock() throws ExcepcionControlada{
		if(cartaSelected==null){
			throw new ExcepcionControlada("Debe seleccionar una carta", null);
		}
		this.getDialogoStockBean().setearDefaults(true, true, true, true, true, true, true);
		this.getDialogoStockBean().setCartaCatalogo(cartaSelected);
		this.getDialogoStockBean().setCartas(null);
		this.getDialogoStockBean().actualizarListado();
		RequestContext.getCurrentInstance().execute("PF('dialogoStock').show();");
	}
	
	private List<CartaCatalogo> obtenerCartas() throws ExcepcionControlada {
		List<CartaCatalogo> output = new ArrayList<CartaCatalogo>();
		if((filtroNombre == null || filtroNombre.trim().contentEquals("")) 
				&& (filtroEdicion == null || filtroEdicion.trim().contentEquals(""))){
			return output;
		}
		Filtro filtro = new Filtro();
		filtro.setNombre(filtroNombre);
		
		if (this.getCargarVacios()) {
			output.addAll(AdministradorWS.cargarCatalogoConStockConVacios(filtro));
		} else {
			output.addAll(AdministradorWS.cargarCatalogoConStock(filtro));
		}
		return output;
	}
	
	public CartaCatalogo getCartaSelected() {
		return cartaSelected;
	}

	public void setCartaSelected(CartaCatalogo cartaSelected) {
		this.cartaSelected = cartaSelected;
	}

	public List<CartaCatalogo> getCartasFiltradas() {
		try {
			this.actualizarCatalogo();
		} catch (ExcepcionControlada e) {
			e.printStackTrace();
		}
		return cartasFiltradas;
	}

	public void setCartasFiltradas(List<CartaCatalogo> cartasFiltradas) {
		this.cartasFiltradas = cartasFiltradas;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getFiltroEdicion() {
		return filtroEdicion;
	}

	public void setFiltroEdicion(String filtroEdicion) {
		this.filtroEdicion = filtroEdicion;
	}



	public DialogoStockBean getDialogoStockBean() {
		return dialogoStockBean;
	}

	public void setDialogoStockBean(DialogoStockBean dialogoStockBean) {
		this.dialogoStockBean = dialogoStockBean;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public Boolean getCargarVacios() {
		return cargarVacios;
	}

	public void setCargarVacios(Boolean cargarVacios) {
		this.cargarVacios = cargarVacios;
	}
}
