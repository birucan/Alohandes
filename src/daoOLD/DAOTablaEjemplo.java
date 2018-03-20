
package daoOLD;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vosOLD.*;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOTablaEjemplo {


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
	public DAOTablaEjemplo() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los ejemplos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM ejemploS;
	 * @return Arraylist con los ejemplos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<ejemplo> darejemplos() throws SQLException, Exception {
		ArrayList<ejemplo> ejemplos = new ArrayList<ejemplo>();

		String sql = "SELECT * FROM ejemplo";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NAME");
			Long id = rs.getLong("ID");
			ejemplos.add(new ejemplo(id, name));
		}
		return ejemplos;
	}



	
	/**
	 * Metodo que busca el ejemplo con el id que entra como parametro.
	 * @param name - Id de el ejemplo a buscar
	 * @return ejemplo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ejemplo buscarejemploPorId(Long id) throws SQLException, Exception 
	{
		ejemplo ejemplo = null;

		String sql = "SELECT * FROM ejemplo WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name = rs.getString("NAME");
			Long id2 = rs.getLong("ID");
			Integer duration = rs.getInt("DURATION");
			ejemplo = new ejemplo(id2, name);
		}

		return ejemplo;
	}

	/**
	 * Metodo que agrega el ejemplo que entra como parametro a la base de datos.
	 * @param ejemplo - el ejemplo a agregar. ejemplo !=  null
	 * <b> post: </b> se ha agregado el ejemplo a la base de datos en la transaction actual. pendiente que el ejemplo master
	 * haga commit para que el ejemplo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el ejemplo a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addejemplo(ejemplo ejemplo) throws SQLException, Exception {

		String sql = "INSERT INTO ejemplo VALUES (";
		sql += ejemplo.getId() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el ejemplo que entra como parametro en la base de datos.
	 * @param ejemplo - el ejemplo a actualizar. ejemplo !=  null
	 * <b> post: </b> se ha actualizado el ejemplo en la base de datos en la transaction actual. pendiente que el ejemplo master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el ejemplo.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateejemplo(ejemplo ejemplo) throws SQLException, Exception {

		String sql = "UPDATE ejemplo SET ";
		sql += "NAME='" + ejemplo.getId() + "')";


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el ejemplo que entra como parametro en la base de datos.
	 * @param ejemplo - el ejemplo a borrar. ejemplo !=  null
	 * <b> post: </b> se ha borrado el ejemplo en la base de datos en la transaction actual. pendiente que el ejemplo master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el ejemplo.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	*/
	public void deleteejemplo(ejemplo ejemplo) throws SQLException, Exception {

		String sql = "DELETE FROM ejemplo";
		sql += " WHERE ID = " + ejemplo.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

}
