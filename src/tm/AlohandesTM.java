package tm;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import dao.DAOClientes;
import dao.DAOOperadores;
import vos.Cliente;
import vos.Contrato;
import vos.Operador;
import vos.Sitio;




/**
 * Transaction Manager de la aplicacion (TM)
 * Fachada en patron singleton de la aplicacion
 * @author Monitores 2017-20
 */
public class AlohandesTM {


	/**
	 * Atributo estatico que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * conexion a la base de datos
	 */
	private Connection conn;


	/**
	 * Metodo constructor de la clase rotondaAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logica de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto rotondaAndesTM, se inicializa el path absoluto del archivo de conexion y se
	 * inicializa los atributos que se usan par la conexion a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public AlohandesTM(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/**
	 * Metodo que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexion a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que  retorna la conexion a la base de datos
	 * @return Connection - la conexion a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}
	//RF3
	public void addCliente(Cliente cliente) throws Exception {

		DAOClientes daoC = new DAOClientes();
		try 
		{

			this.conn = darConexion();
			daoC.setConn(conn);
			daoC.addCliente(cliente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoC.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}


		}

	}
	//RF1
	public void addOperador(Operador operador) throws Exception {
		DAOOperadores daoO = new DAOOperadores();
		try 
		{

			this.conn = darConexion();
			daoO.setConn(conn);
			daoO.addOperador(operador);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoO.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}
	}
	//RF2
	public void addSitio(Sitio sitio, long idoperador) throws Exception {
		DAOOperadores daoO = new DAOOperadores();
		try 
		{

			this.conn = darConexion();
			daoO.setConn(conn);
			daoO.addSitio(sitio, idoperador);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoO.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}
	}
	//RF4
	public void addReserva(Contrato contrato, long idCliente, long idSitio, String servicios) throws Exception {
		DAOClientes daoC = new DAOClientes();
		try 
		{

			this.conn = darConexion();
			daoC.setConn(conn);
			daoC.addReserva(contrato, idCliente, idSitio, servicios);
			System.out.println(servicios);
			daoC.addSercon(contrato.getIdcontrato(), servicios.split(","));
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoC.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}

	}

	public void cancelReserva(long idCliente, long idReserva) throws Exception {
		DAOClientes daoC = new DAOClientes();
		try 
		{

			this.conn = darConexion();
			daoC.setConn(conn);
			daoC.cancelReserva(idCliente, idReserva);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoC.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}

	}

	public void removeSitio(long idoperador, long idsitio) throws Exception {
		DAOOperadores daoO = new DAOOperadores();
		try 
		{

			this.conn = darConexion();
			daoO.setConn(conn);
			daoO.removeSitio(idoperador,idsitio);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoO.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}
	}

	public double getGanancias(long idoperador, int ano) throws Exception {
		DAOOperadores daoO = new DAOOperadores();
		double returner=0;
		try 
		{

			this.conn = darConexion();
			daoO.setConn(conn);
			returner = daoO.getGanancias(idoperador,ano);


		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoO.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();

			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}
		return returner;
	}

	public List<Long> top20() throws Exception {
		DAOOperadores daoO = new DAOOperadores();
		List<Long> returner=null;
		try 
		{

			this.conn = darConexion();
			daoO.setConn(conn);
			returner = daoO.top20();


		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoO.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();

			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}
		return returner;
	}

	public String addReservaColectiva(long idEvento, String servicios, Contrato contrato) throws Exception {
		String response = "No es posible la reserva";
		DAOClientes daoC = new DAOClientes();
		try 
		{

			this.conn = darConexion();
			daoC.setConn(conn);
			if(daoC.VerificarEvento(idEvento, servicios.split(","), contrato, servicios)){
				//daoC.addSereven(idEvento, servicios.split(","));

				response = "La reserva fue realizada exitosamente";
			}


			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoC.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}
		return response;

	}

	public void cancelarEvento(long idEvento) throws Exception {
		DAOClientes daoC = new DAOClientes();
		try 
		{

			this.conn = darConexion();
			daoC.setConn(conn);
			daoC.cancelEvento(idEvento);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoC.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}

		}

	}
	
	public void deshabilitarSitio(long idoperador, long idsitio) throws Exception {
		DAOOperadores daoO = new DAOOperadores();
		try 
		{
			this.conn = darConexion();
			daoO.setConn(conn);
			daoO.deshabilitarSitio(idoperador,idsitio);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoO.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		
	  }
		
	}
	
	public void habilidarSitio(long idoperador, long idsitio) throws Exception {
		DAOOperadores daoO = new DAOOperadores();
		try 
		{
			this.conn = darConexion();
			daoO.setConn(conn);
			daoO.habilitarSitio(idoperador,idsitio);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoO.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		
	  }
		
	}
}