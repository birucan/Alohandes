package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sitio {
	//Attributes
	@JsonProperty(value = "idsitio")
	private Long idsitio;
	
	@JsonProperty(value = "idoperador")
	private Long idoperador;
	
	@JsonProperty(value = "tipo" )
	private String tipo;
	
	@JsonProperty(value = "descripcion" )
	private String descripcion;
	

	//contructor
	public Sitio(
			@JsonProperty(value = "idsitio") Long idsitio,
			@JsonProperty(value = "idoperador") Long idoperador,
			@JsonProperty(value = "tipo")String tipo,
			@JsonProperty(value = "descripcion")String descripcion){
				super();
				this.descripcion = descripcion;
				this.idoperador = idoperador;
				this.tipo = tipo;
				this.descripcion = descripcion;
	}


	public Long getIdsitio() {
		return idsitio;
	}


	public void setIdsitio(Long idsitio) {
		this.idsitio = idsitio;
	}


	public Long getIdoperador() {
		return idoperador;
	}


	public void setIdoperador(Long idoperador) {
		this.idoperador = idoperador;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
}