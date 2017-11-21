package leacy.admin.wsclient;

import java.util.List;

import leacy.modelo.CartaStock;

public class RequestGenerarExcelStock extends Request {
	private static final long serialVersionUID = -554764601734718682L;
	
	private List<CartaStock> lista;

	public List<CartaStock> getLista() {
		return lista;
	}

	public void setLista(List<CartaStock> lista) {
		this.lista = lista;
	}
}
