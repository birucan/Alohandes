package vosOLD;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {
	//Attributes
	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "identificacion" )
	private String identificacion;
	
	@JsonProperty(value = "correo" )
	private String correo;
	
	@JsonProperty(value = "tipo")
	private String tipoUsuario;
	
	//contructor
	public Usuario(@JsonProperty(value = "id") Long id, @JsonProperty(value = "nombre") String nombre,@JsonProperty(value = "identificacion" ) String identificacion,@JsonProperty(value = "correo" ) String correo, @JsonProperty(value = "tipo")String tipoUsuario){
		super();
		this.id = id;
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.correo = correo;
		this.tipoUsuario = tipoUsuario;
	}
	
	//getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", identificacion=" + identificacion + ", correo=" + correo
				+ ", tipoUsuario=" + tipoUsuario + "]";
	}


}
