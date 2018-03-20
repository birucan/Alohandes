package vosOLD;

import org.codehaus.jackson.annotate.JsonProperty;

public class Menu {
	@JsonProperty(value="id")
	private long id;
	
	@JsonProperty(value="idRest")
	private long restaurante;
	
	@JsonProperty(value="idEntrada")
	private long entrada;
	
	@JsonProperty(value="idFuerte")
	private long fuerte;
	
	@JsonProperty(value="idPostre")
	private long postre;
	
	@JsonProperty(value="idBebida")
	private long bebida;
	
	@JsonProperty(value="idAcom")
	private long acom;
	
	@JsonProperty(value="precio")
	private long precio;

	public Menu(@JsonProperty(value="id")long id, @JsonProperty(value="idRest")long restaurante, @JsonProperty(value="idEntrada")long entrada, @JsonProperty(value="idFuerte")long fuerte, @JsonProperty(value="idPostre")long postre, @JsonProperty(value="idBebida")long bebida, @JsonProperty(value="idAcom")long acom,
			@JsonProperty(value="precio")long precio) {
		super();
		this.id = id;
		this.restaurante = restaurante;
		this.entrada = entrada;
		this.fuerte = fuerte;
		this.postre = postre;
		this.bebida = bebida;
		this.acom = acom;
		this.precio = precio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(long restaurante) {
		this.restaurante = restaurante;
	}

	public long getEntrada() {
		return entrada;
	}

	public void setEntrada(long entrada) {
		this.entrada = entrada;
	}

	public long getFuerte() {
		return fuerte;
	}

	public void setFuerte(long fuerte) {
		this.fuerte = fuerte;
	}

	public long getPostre() {
		return postre;
	}

	public void setPostre(long postre) {
		this.postre = postre;
	}

	public long getBebida() {
		return bebida;
	}

	public void setBebida(long bebida) {
		this.bebida = bebida;
	}

	public long getAcom() {
		return acom;
	}

	public void setAcom(long acom) {
		this.acom = acom;
	}

	public long getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}
	
	
}
