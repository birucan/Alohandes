package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cliente {
	//Attributes
	@JsonProperty(value = "idcliente")
	private Long idcliente;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "apellido" )
	private String apellido;
	
	@JsonProperty(value = "vinculo" )
	private String vinculo;
	

	//contructor
	public Cliente(@JsonProperty(value = "idcliente") Long idcliente,
			@JsonProperty(value = "nombre") String nombre,
			@JsonProperty(value = "apellido")String apellido,
			@JsonProperty(value = "vinculo")String vinculo){
		super();
		this.idcliente = idcliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.vinculo = vinculo;
	}
	
	//getters and setters

	public Long getIdcliente() {
		return idcliente;
	}

	public void setId(Long idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	} 

	public String getVinculo() {
		return vinculo;
	}
}