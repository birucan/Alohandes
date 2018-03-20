package vosOLD;

import org.codehaus.jackson.annotate.JsonProperty;

public class PreferenciaCliente {
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="idCliente")
	private long idClient;

	public PreferenciaCliente(@JsonProperty(value="descripcion")String descripcion,@JsonProperty(value="idClient") long idClient) {
		super();
		this.descripcion = descripcion;
		this.idClient = idClient;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	@Override
	public String toString() {
		return "PreferenciaCliente [descripcion=" + descripcion + ", idClient=" + idClient + "]";
	}
	
	
}
