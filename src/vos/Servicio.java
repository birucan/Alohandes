package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Servicio {
	
	@JsonProperty(value = "idservicio")
	private Long idservicio;
	
	@JsonProperty(value = "descripcion")
	private String descripcion;
	
	public Servicio(@JsonProperty(value = "idsitio") Long idservicio,
	@JsonProperty(value = "descripcion") String descripcion){
		this.idservicio = idservicio;
		this.descripcion = descripcion;
	}

	public Long getIdservicio() {
		return idservicio;
	}

	public void setIdservicio(Long idservicio) {
		this.idservicio = idservicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
