/*
 *Objeto con objetivo de describir la tabla de relacion entre los servicios que
 *tiene un evento 
 */

package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sereven {
	@JsonProperty(value = "idevento")
	private Long idevento;
	
	@JsonProperty(value = "idservicio")
	private Long idservicio;
	
	public Sereven(@JsonProperty(value = "idevento") Long idevento,
				   @JsonProperty(value = "idservicio") Long idservicio){
		this.idevento = idevento;
		this.idservicio = idservicio;
	}

	public Long getIdevento() {
		return idevento;
	}

	public void setIdevento(Long idevento) {
		this.idevento = idevento;
	}

	public Long getIdservicio() {
		return idservicio;
	}

	public void setIdservicio(Long idservicio) {
		this.idservicio = idservicio;
	}
	
	
}
