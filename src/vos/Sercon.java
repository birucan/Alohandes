package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sercon {
	@JsonProperty(value = "idcontrato")
	private Long idcontrato;
	
	@JsonProperty(value = "idservicio")
	private Long idservicio;
	
	public Sercon(@JsonProperty(value = "idcontrato") Long idcontrato,
	@JsonProperty(value = "idservicio") Long idservicio){
		this.idcontrato = idcontrato;
		this.idservicio = idservicio;
	}

	public Long getIdcontrato() {
		return idcontrato;
	}

	public void setIdcontrato(Long idcontrato) {
		this.idcontrato = idcontrato;
	}

	public Long getIdservicio() {
		return idservicio;
	}

	public void setIdservicio(Long idservicio) {
		this.idservicio = idservicio;
	}	
}
