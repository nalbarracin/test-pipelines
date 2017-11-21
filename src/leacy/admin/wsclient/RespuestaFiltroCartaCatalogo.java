package leacy.admin.wsclient;

import java.util.List;

import leacy.modelo.CartaCatalogo;

public class RespuestaFiltroCartaCatalogo extends Respuesta{
	private static final long serialVersionUID = 1165093226996633069L;
	
	private List<CartaCatalogo> lista;

	public List<CartaCatalogo> getLista() {
		return lista;
	}

	public void setLista(List<CartaCatalogo> lista) {
		this.lista = lista;
	}
}
