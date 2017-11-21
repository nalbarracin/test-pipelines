package leacy.admin.wsclient;

public enum EstadoMensaje {
	ok("OK"), error("ERROR");

	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	private EstadoMensaje(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return getEstado();
	}
}
