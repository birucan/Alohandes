package vosOLD;
import org.codehaus.jackson.annotate.JsonProperty;

public class Producto {
	@JsonProperty(value="id")
	private long id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="descripcionES")
	private String descripcionES;
	
	@JsonProperty(value="descripcionEN")
	private String descripcionEN;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="idRest")
	private long idRest;

	@JsonProperty(value="precio")
	private long precio;



	public Producto(@JsonProperty(value="id")long id, @JsonProperty(value="nombre")String nombre, @JsonProperty(value="descripcionES")String descripcionES, @JsonProperty(value="descripcionEN")String descripcionEN, @JsonProperty(value="tipo")String tipo, @JsonProperty(value="idRest")long idRest,@JsonProperty(value="precio")
	 long aprecio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcionES = descripcionES;
		this.descripcionEN = descripcionEN;
		this.tipo = tipo;
		this.idRest = idRest;
		this.precio = aprecio;
		
	}

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

	public String getDescripcionES() {
		return descripcionES;
	}

	public void setDescripcionES(String descripcionES) {
		this.descripcionES = descripcionES;
	}

	public String getDescripcionEN() {
		return descripcionEN;
	}

	public void setDescripcionEN(String descripcionEN) {
		this.descripcionEN = descripcionEN;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getIdRest() {
		return idRest;
	}

	public void setIdRest(long idRest) {
		this.idRest = idRest;
	}
	public long getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcionES=" + descripcionES + ", descripcionEN="
				+ descripcionEN + ", tipo=" + tipo + ", idRest=" + idRest + ", precio=" + precio + "]";
	}
}
