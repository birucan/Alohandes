package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sersit {
	@JsonProperty(value = "idsitio")
	private Long idsitio;
	
	@JsonProperty(value = "idservicio")
	private Long idservicio;
	
	public Sersit(@JsonProperty(value = "idsitio") Long idsitio,
	@JsonProperty(value = "idservicio") Long idservicio){
		this.idsitio = idsitio;
		this.idservicio = idservicio;
	}

	public Long getIdsitio() {
		return idsitio;
	}

	public void setIdsitio(Long idsitio) {
		this.idsitio = idsitio;
	}

	public Long getIdservicio() {
		return idservicio;
	}

	public void setIdservicio(Long idservicio) {
		this.idservicio = idservicio;
	}	
}
