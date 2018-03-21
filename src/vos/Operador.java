package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Operador {
	@JsonProperty(value = "idoperador")
	private Long idoperador;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "tipo")
	private String tipo;
	
	public Operador(
	@JsonProperty(value = "idoperador")Long idoperador,
	@JsonProperty(value = "nombre") String nombre,
	@JsonProperty(value = "tipo") String tipo){
		this.idoperador = idoperador;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public Long getIdoperador() {
		return idoperador;
	}

	public void setIdoperador(Long idoperador) {
		this.idoperador = idoperador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
