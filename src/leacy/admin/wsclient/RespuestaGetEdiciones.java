package leacy.admin.wsclient;

import java.util.List;

import leacy.modelo.Edicion;

public class RespuestaGetEdiciones extends Respuesta{
	
	private static final long serialVersionUID = 6192641566403498168L;
	
	private List<Edicion> lista;

	public List<Edicion> getLista() {
		return lista;
	}

	public void setLista(List<Edicion> lista) {
		this.lista = lista;
	}
}
