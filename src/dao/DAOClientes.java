package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import vos.Cliente;
import vos.Contrato;



/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOClientes {


	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOejemplo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOClientes() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}

	public void addCliente(Cliente cliente) throws Exception {
		 String sql = "INSERT INTO CLIENTES (NOMBRE, APELLIDO, VINCULO) VALUES ('";
		  sql += cliente.getNombre() +"', '";
		  sql += cliente.getApellido()+"', '";
		  sql += cliente.getVinculo()+"')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void addReserva(Contrato contrato, long idCliente, long idSitio, String servicios) throws Exception {
		
		 String[] serviciosplit = servicios.split(".");
		 
		 String sql = "INSERT INTO CONTRATOS (IDCONTRATO, IDCLIENTE, IDSITIO, ESTADO, FECHAIN, FECHAEN, COSTO, COSTOEXTRA, COSTOTOT, FECHAPEDIDO) VALUES (";
		  sql += contrato.getIdcontrato() + ",";
		  sql += idCliente +",";
		  sql += idSitio +", '";
		  sql += "reserva',"; 
		  sql += "TO_DATE('"+contrato.getFechain() +"', 'DD-MM-YYYY'),";
		  sql += "TO_DATE('"+contrato.getFechaen() +"', 'DD-MM-YYYY'),";
		  sql += contrato.getCosto() +", ";
		  sql += contrato.getCostoextra()+",";
		  sql += contrato.getCosto()+contrato.getCostoextra() +",";
		  
		  
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  LocalDateTime now = LocalDateTime.now();
		  
		  sql += "TO_DATE('"+dtf.format(now)+"', 'DD-MM-YYYY')) ";
		  System.out.println(sql);
		  
		  	
		 
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		
		
	}

	public void addSercon(Long idcontrato, String[] serviciosplit) throws Exception {
		String sql = "";
		System.out.println(serviciosplit.length);
		for (int a = 0; a < serviciosplit.length; a++) {
			sql = "";
			sql += "INSERT INTO SERCON (IDCONTRATO, IDSERVICIO) VALUES (" +idcontrato+", "+ serviciosplit[a]+") ";
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
	}

	public void cancelReserva(long idCliente, long idReserva) throws Exception {
		String sql = "UPDATE CONTRATOS SET ESTADO = 'cancelado' WHERE IDCLIENTE ="+idCliente+" AND IDCONTRATO = "+idReserva;
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
}
