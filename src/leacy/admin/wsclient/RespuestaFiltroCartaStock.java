package leacy.admin.wsclient;

import java.util.List;

import leacy.modelo.CartaStock;

public class RespuestaFiltroCartaStock extends Respuesta{
	private static final long serialVersionUID = 1165093226996633069L;
	
	private List<CartaStock> lista;

	public List<CartaStock> getLista() {
		return lista;
	}

	public void setLista(List<CartaStock> lista) {
		this.lista = lista;
	}
}
