package daoOLD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vosOLD.PreferenciaCliente;

public class DAOTablaPreferenciaClientes {



	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOPreferenciaCliente
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaPreferenciaClientes() {
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
	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los PreferenciaClientes de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM PreferenciaClienteS;
	 * @return Arraylist con los PreferenciaClientes de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<PreferenciaCliente> darPreferenciaClientes() throws SQLException, Exception {
		ArrayList<PreferenciaCliente> PreferenciaClientes = new ArrayList<PreferenciaCliente>();

		String sql = "SELECT * FROM PreferenciaCliente";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String des = rs.getString("DESCRIPCION");
			Long a = rs.getLong("IDCLIENTE");

			PreferenciaClientes.add(new PreferenciaCliente(des, a));
		}
		return PreferenciaClientes;
	}


	/**
	 * Metodo que busca el/los PreferenciaClientes con el nombre que entra como parametro.
	 * @param name - Nombre de el/los PreferenciaClientes a buscar
	 * @return ArrayList con los PreferenciaClientes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<PreferenciaCliente> buscarPreferenciaClientesPorName(String name) throws SQLException, Exception {
		ArrayList<PreferenciaCliente> PreferenciaClientes = new ArrayList<PreferenciaCliente>();

		String sql = "SELECT * FROM PreferenciaCliente WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String des = rs.getString("DESCRIPCION");
			Long a = rs.getLong("IDCLIENTE");

			PreferenciaClientes.add(new PreferenciaCliente(des, a));
		}

		return PreferenciaClientes;
	}
	
	/**
	 * Metodo que busca el PreferenciaCliente con el id que entra como parametro.
	 * @param name - Id de el PreferenciaCliente a buscar
	 * @return PreferenciaCliente encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public PreferenciaCliente buscarPreferenciaClientePorId(Long id) throws SQLException, Exception 
	{
		PreferenciaCliente PreferenciaCliente = null;

		String sql = "SELECT * FROM PreferenciaCliente WHERE IDCLIENTE =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("IDCLIENTE");
			String a = rs.getString("DESCRIPCION");

			PreferenciaCliente = new PreferenciaCliente(a, id2);
		}

		return PreferenciaCliente;
	}

	/**
	 * Metodo que agrega el PreferenciaCliente que entra como parametro a la base de datos.
	 * @param PreferenciaCliente - el PreferenciaCliente a agregar. PreferenciaCliente !=  null
	 * <b> post: </b> se ha agregado el PreferenciaCliente a la base de datos en la transaction actual. pendiente que el PreferenciaCliente master
	 * haga commit para que el PreferenciaCliente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el PreferenciaCliente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addPreferenciaCliente(PreferenciaCliente PreferenciaCliente) throws SQLException, Exception {


		 String sql = "INSERT INTO PreferenciaCliente VALUES ('";
		  sql += PreferenciaCliente.getDescripcion() + "', ";
		  sql += PreferenciaCliente.getIdClient() +")";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
//	/**
//	 * Metodo que actualiza el PreferenciaCliente que entra como parametro en la base de datos.
//	 * @param PreferenciaCliente - el PreferenciaCliente a actualizar. PreferenciaCliente !=  null
//	 * <b> post: </b> se ha actualizado el PreferenciaCliente en la base de datos en la transaction actual. pendiente que el PreferenciaCliente master
//	 * haga commit para que los cambios bajen a la base de datos.
//	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el PreferenciaCliente.
//	 * @throws Exception - Cualquier error que no corresponda a la base de datos
//	 */
//	public void updatePreferenciaCliente(PreferenciaCliente PreferenciaCliente) throws SQLException, Exception {
//
//		String sql = "UPDATE PreferenciaCliente SET ";
//		sql += "NAME='" + PreferenciaCliente.getNombre() + "',";
//		sql += " WHERE ID = " + PreferenciaCliente.getId();
//
//
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//	}

	/**
	 * Metodo que elimina el PreferenciaCliente que entra como parametro en la base de datos.
	 * @param PreferenciaCliente - el PreferenciaCliente a borrar. PreferenciaCliente !=  null
	 * <b> post: </b> se ha borrado el PreferenciaCliente en la base de datos en la transaction actual. pendiente que el PreferenciaCliente master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el PreferenciaCliente.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deletePreferenciaCliente(PreferenciaCliente PreferenciaCliente) throws SQLException, Exception {

		String sql = "DELETE FROM PreferenciaCliente";
		sql += " WHERE IDCLIENTE = " + PreferenciaCliente.getIdClient();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
		
		

}