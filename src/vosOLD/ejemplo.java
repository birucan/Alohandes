package vosOLD;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa un rotonda
 * @author t.kavanagh
 */
public class ejemplo {

	//// Atributos

	/**
	 * Id de la rotonda
	 */
	@JsonProperty(value="id")
	private Long id;


	/**
	 * Metodo constructor de la clase rotonda
	 * <b>post: </b> Crea el rotonda con los valores que entran como parametro
	 * @param id - Id del rootnda.
	 * @param name - Nombre del rotonda. name != null
	 	 */
	public ejemplo(@JsonProperty(value="id")Long id, @JsonProperty(value="name")String name) {
		super();
		this.id = id;

	}



	/**
	 * Metodo getter del atributo id
	 * @return id del rotonda
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo setter del atributo id <b>post: </b> El id del rotonda ha sido
	 * cambiado con el valor que entra como parametro
	 * @param id - Id del rotonda
	 */
	public void setId(Long id) {
		this.id = id;
	}
}


