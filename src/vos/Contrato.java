package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Contrato {
	//Attributo
	@JsonProperty(value = "idcontrato")
	private Long idcontrato;
	
	@JsonProperty(value = "idcliente")
	private Long idcliente;
	
	@JsonProperty(value = "idsitio")
	private Long idsitio;
	
	@JsonProperty(value = "estado")
	private String estado;
	
	@JsonProperty(value = "fechain")
	private String fechain;
	
	@JsonProperty(value = "fechaen")
	private String fechaen;
	
	@JsonProperty(value = "costo")
	private Double costo;
	
	@JsonProperty(value = "costoextra")
	private Double costoextra;
	
	@JsonProperty(value = "costotot")
	private Double costotot;
	
	@JsonProperty(value = "fechapedido")
	private String fechapedido;
	
	public Contrato(@JsonProperty(value = "idcontrato")Long idcontrato,
	@JsonProperty(value = "idcliente") Long idcliente,	
	@JsonProperty(value = "idsitio") Long idsitio,	
	@JsonProperty(value = "estado") String estado,
	@JsonProperty(value = "fechain") String fechain,
	@JsonProperty(value = "fechaen") String fechaen,
	@JsonProperty(value = "costo") Double costo,
	@JsonProperty(value = "costoextra") Double costoextra,
	@JsonProperty(value = "costotot") Double costotot,
	@JsonProperty(value = "fechapedido") String fechapedido){
		
		this.idcontrato = idcontrato;
		this.idcliente = idcliente;
		this.idsitio = idsitio;
		this.estado = estado;
		this.fechain = fechain;
		this.fechaen = fechaen;
		this.costo = costo;
		this.costoextra =costoextra;
		this.costotot = costotot;
		this.fechapedido = fechapedido;
	}

	public Long getIdcontrato() {
		return idcontrato;
	}

	public void setIdcontrato(Long idcontrato) {
		this.idcontrato = idcontrato;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public Long getIdsitio() {
		return idsitio;
	}

	public void setIdsitio(Long idsitio) {
		this.idsitio = idsitio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechain() {
		return fechain;
	}

	public void setFechain(String fechain) {
		this.fechain = fechain;
	}

	public String getFechaen() {
		return fechaen;
	}

	public void setFechaen(String fechaen) {
		this.fechaen = fechaen;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getCostoextra() {
		return costoextra;
	}

	public void setCostoextra(Double costoextra) {
		this.costoextra = costoextra;
	}

	public Double getCostotot() {
		return costotot;
	}

	public void setCostotot(Double costotot) {
		this.costotot = costotot;
	}

	public String getFechapedido() {
		return fechapedido;
	}

	public void setFechapedido(String fechapedido) {
		this.fechapedido = fechapedido;
	}
}
