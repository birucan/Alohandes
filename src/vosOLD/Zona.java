package vosOLD;

import org.codehaus.jackson.annotate.JsonProperty;

public class Zona {
	@JsonProperty(value="id")
	private long id;
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	public Zona(@JsonProperty(value="id")long id, @JsonProperty(value="descripcion") String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
