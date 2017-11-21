package leacy.admin.wsclient.restService;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.clienteComunicacion.ClienteRest;
import leacy.admin.wsclient.EstadoMensaje;
import leacy.admin.wsclient.RequestActualizarStock;
import leacy.admin.wsclient.RequestGenerarExcelStock;
import leacy.admin.wsclient.RespuestaActualizarCartaStock;
import leacy.admin.wsclient.RespuestaActualizarStock;
import leacy.admin.wsclient.RespuestaFiltroCartaCatalogo;
import leacy.admin.wsclient.RespuestaFiltroCartaStock;
import leacy.admin.wsclient.RespuestaGenerarExcelStock;
import leacy.admin.wsclient.RespuestaGetEdiciones;
import leacy.exception.ExcepcionControlada;
import leacy.modelo.CartaCatalogo;
import leacy.modelo.CartaStock;
import leacy.modelo.Edicion;
import leacy.modelo.Filtro;

public class AdministradorWS {

	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

	public static List<CartaCatalogo> buscarCartaPorFiltro(Filtro filtro) throws ExcepcionControlada {
		ClienteRest cliente;

		String json = gson.toJson(filtro, Filtro.class);
		RespuestaFiltroCartaCatalogo response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.post(PropertiesWS.urlBuscarCartaPorFiltro, new HashMap<String, String>(), json);
			
			response = gson.fromJson(output, RespuestaFiltroCartaCatalogo.class);
			
			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}
			
			return response.getLista();

		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
	
	public static List<CartaStock> buscarStockPorEdicion(Filtro filtro) throws ExcepcionControlada {
		ClienteRest cliente;

		String json = gson.toJson(filtro, Filtro.class);
		RespuestaFiltroCartaStock response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.post(PropertiesWS.urlBuscarStockPorEdicion, new HashMap<String, String>(), json);
			response = gson.fromJson(output, RespuestaFiltroCartaStock.class);

			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}
			
			return response.getLista();

		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
			
	public static List<CartaStock> cargarCartasStockConStock(CartaCatalogo carta) throws ExcepcionControlada {
		ClienteRest cliente;

		String json = gson.toJson(carta, CartaCatalogo.class);
		RespuestaFiltroCartaStock response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.post(PropertiesWS.urlCargarCartasStockConStockPorCarta, new HashMap<String, String>(), json);
			response = gson.fromJson(output, RespuestaFiltroCartaStock.class);

			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}

			return response.getLista();
			
		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
	
	public static List<CartaStock> cargarCartasStockConStock(Edicion edicion) throws ExcepcionControlada {
		ClienteRest cliente;

		String json = gson.toJson(edicion, Edicion.class);
		RespuestaFiltroCartaStock response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.post(PropertiesWS.urlCargarCartasStockConStockPorEdicion, new HashMap<String, String>(), json);
			response = gson.fromJson(output, RespuestaFiltroCartaStock.class);

			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}

			return response.getLista();
			
		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
	
	public static List<CartaStock> buscarStockPorCartaCatalogo(CartaCatalogo carta) throws ExcepcionControlada {
		ClienteRest cliente;

		String json = gson.toJson(carta, CartaCatalogo.class);
		RespuestaFiltroCartaStock response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.post(PropertiesWS.urlBuscarStockPorCartaCatalogo, new HashMap<String, String>(), json);
			response = gson.fromJson(output, RespuestaFiltroCartaStock.class);

			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}

			return response.getLista();
			
		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
	
	public static List<Edicion> getEdiciones() throws ExcepcionControlada {
		ClienteRest cliente;

		RespuestaGetEdiciones response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.get(PropertiesWS.urlGetEdiciones, new HashMap<String, String>(), new HashMap<String, String>());
			response = gson.fromJson(output, RespuestaGetEdiciones.class);

			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}

			return response.getLista();
			
		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}

	public static List<CartaCatalogo> cargarCatalogoConStock(Filtro filtro) throws ExcepcionControlada {
		ClienteRest cliente;
	
		String json = gson.toJson(filtro, Filtro.class);
		RespuestaFiltroCartaCatalogo response;
	
		try {
			cliente = ClienteRest.getInstanceTrustAll();
	
			String output = cliente.post(PropertiesWS.urlCargarCatalogoConStock, new HashMap<String, String>(), json);
			
			response = gson.fromJson(output, RespuestaFiltroCartaCatalogo.class);
			
			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}
			
			return response.getLista();
	
		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
	
	public static List<CartaCatalogo> cargarCatalogoConStockConVacios(Filtro filtro) throws ExcepcionControlada {
		ClienteRest cliente;
	
		String json = gson.toJson(filtro, Filtro.class);
		RespuestaFiltroCartaCatalogo response;
	
		try {
			cliente = ClienteRest.getInstanceTrustAll();
	  
			String output = cliente.post(PropertiesWS.urlCargarCatalogoConStockConVacios, new HashMap<String, String>(), json);
			
			response = gson.fromJson(output, RespuestaFiltroCartaCatalogo.class);
			
			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}
			
			return response.getLista();
	
		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}

	public static void actualizarCartaStock(CartaStock carta) throws ExcepcionControlada {
		ClienteRest cliente;

		String json = gson.toJson(carta, CartaStock.class);
		RespuestaActualizarCartaStock response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.post(PropertiesWS.urlActualizarCartaStock, new HashMap<String, String>(), json);
			response = gson.fromJson(output, RespuestaActualizarCartaStock.class);

			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}

		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
	
	public static File generarExcelStock() throws ExcepcionControlada{
		ClienteRest cliente;
		RequestGenerarExcelStock request = new RequestGenerarExcelStock();
		
		String json = gson.toJson(request, RequestGenerarExcelStock.class);
		RespuestaGenerarExcelStock response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.post(PropertiesWS.urlGenerarExcelStock, new HashMap<String, String>(), json);
			response = gson.fromJson(output, RespuestaGenerarExcelStock.class);

			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}
			
			return response.getFile();

		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
	
	public static void actualizarStock(List<CartaStock> listaStock) throws ExcepcionControlada {
		ClienteRest cliente;
		RequestActualizarStock request = new RequestActualizarStock();
		request.setLista(listaStock);
		
		String json = gson.toJson(request, RequestActualizarStock.class);
		RespuestaActualizarStock response;

		try {
			cliente = ClienteRest.getInstanceTrustAll();

			String output = cliente.post(PropertiesWS.urlActualizarStock, new HashMap<String, String>(), json);
			response = gson.fromJson(output, RespuestaActualizarStock.class);

			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}

		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		
		
	}

	public static List<Edicion> getEdicionesConStock() throws ExcepcionControlada {
		ClienteRest cliente;
	
		RespuestaGetEdiciones response;
	
		try {
			cliente = ClienteRest.getInstanceTrustAll();
	
			String output = cliente.get(PropertiesWS.urlGetEdicionesConStock, new HashMap<String, String>(), new HashMap<String, String>());
			response = gson.fromJson(output, RespuestaGetEdiciones.class);
	
			if (response.getEstado().equals(EstadoMensaje.error)) {
				throw new ExcepcionControlada(response.getMensajeError(), null);
			}
	
			return response.getLista();
			
		} catch (Exception e) {
			throw new ExcepcionControlada("Error de Comunicacion con el WebServicePoblador", e);
		}
	}

}
