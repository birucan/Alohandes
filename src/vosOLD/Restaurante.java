package vosOLD;

import org.codehaus.jackson.annotate.JsonProperty;

public class Restaurante {
	@JsonProperty(value="id")
	private long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="tipoComida")
	private String tipoComida;
	
	@JsonProperty(value="paginaWeb")
	private String paginaWeb;
	
	@JsonProperty(value="zona")
	private long idZona;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public Restaurante(@JsonProperty(value="id")long aId, @JsonProperty(value="nombre")String aNombre, @JsonProperty(value="descripcion")String aDescripcion,
			@JsonProperty(value="tipoComida")String aTipoComida, @JsonProperty(value="paginaWeb")String aPaginaWeb,@JsonProperty(value="zona")long aIdZona ){
		super();
		this.id=aId;
		this.nombre=aNombre;
		this.descripcion=aDescripcion;
		this.tipoComida= aTipoComida;
		this.paginaWeb = aPaginaWeb;
		this.idZona = aIdZona;
	}  
	
}
