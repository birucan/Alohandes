nopackage vos;

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
	
	@JsonProperty(value = "idevento" )
	private Long idevento;
	

	//contructor
	public Cliente(@JsonProperty(value = "idcliente") Long idcliente,
				   @JsonProperty(value = "nombre") String nombre,
				   @JsonProperty(value = "apellido")String apellido,
				   @JsonProperty(value = "vinculo")String vinculo,
				   @JsonProperty(value = "idevento")Long idevento){
		super();
		this.idcliente = idcliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.vinculo = vinculo;
		this.idevento = idevento;
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

	public Long getIdevento() {
		return idevento;
	}

	public void setIdevento(Long idevento) {
		this.idevento = idevento;
	}
}