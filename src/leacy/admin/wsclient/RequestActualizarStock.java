package leacy.admin.wsclient;

import java.util.List;

import leacy.modelo.CartaStock;

public class RequestActualizarStock extends Request{
	private static final long serialVersionUID = 8686918649237832509L;

	private List<CartaStock> lista;

	public List<CartaStock> getLista() {
		return lista;
	}

	public void setLista(List<CartaStock> lista) {
		this.lista = lista;
	}
}
