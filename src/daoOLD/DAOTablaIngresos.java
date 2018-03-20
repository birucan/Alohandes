package daoOLD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vosOLD.Ingreso;

public class DAOTablaIngresos {



	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOIngreso
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaIngresos() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los Ingresos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM IngresoS;
	 * @return Arraylist con los Ingresos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ingreso> darIngresos() throws SQLException, Exception {
		ArrayList<Ingreso> Ingresos = new ArrayList<Ingreso>();

		String sql = "SELECT * FROM Ingreso";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("BALANCE");
			Long a = rs.getLong("IDRESTAURANTE");

			Ingresos.add(new Ingreso(id, a));
		}
		return Ingresos;
	}


	/**
	 * Metodo que busca el/los Ingresos con el nombre que entra como parametro.
	 * @param name - Nombre de el/los Ingresos a buscar
	 * @return ArrayList con los Ingresos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ingreso> buscarIngresosPorName(String name) throws SQLException, Exception {
		ArrayList<Ingreso> Ingresos = new ArrayList<Ingreso>();

		String sql = "SELECT * FROM Ingreso WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("BALANCE");
			Long a = rs.getLong("IDRESTAURANTE");

			Ingresos.add(new Ingreso(id, a));
		}

		return Ingresos;
	}
	
	/**
	 * Metodo que busca el Ingreso con el id que entra como parametro.
	 * @param name - Id de el Ingreso a buscar
	 * @return Ingreso encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Ingreso buscarIngresoPorId(Long id) throws SQLException, Exception 
	{
		Ingreso Ingreso = null;

		String sql = "SELECT * FROM Ingreso WHERE IDRESTAURANTE =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("BALANCE");
			Long a = rs.getLong("IDRESTAURANTE");

			Ingreso = new Ingreso(id2, a);
		}

		return Ingreso;
	}
	
	public Ingreso cambiarIngresoPorId(Long id, Long niu) throws SQLException, Exception 
	{
		Ingreso Ingreso = null;

		String sql = "UPDATE BALANCE FROM Ingreso SET IDRESTAURANTE =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("BALANCE");
			Long a = rs.getLong("IDRESTAURANTE");

			Ingreso = new Ingreso(id2, a);
		}

		return Ingreso;
	}

	/**
	 * Metodo que agrega el Ingreso que entra como parametro a la base de datos.
	 * @param l - el Ingreso a agregar. Ingreso !=  null
	 * <b> post: </b> se ha agregado el Ingreso a la base de datos en la transaction actual. pendiente que el Ingreso master
	 * haga commit para que el Ingreso baje  a la base de datos.
	 * @param idRestaurante 
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Ingreso a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addIngreso(long l, long idRestaurante) throws SQLException, Exception {


		 String sql = "INSERT INTO Ingreso VALUES (";
		  sql += "BALANCE=" + l + ",";
     	  sql += " WHERE ID = " + idRestaurante;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
//	/**
//	 * Metodo que actualiza el Ingreso que entra como parametro en la base de datos.
//	 * @param Ingreso - el Ingreso a actualizar. Ingreso !=  null
//	 * <b> post: </b> se ha actualizado el Ingreso en la base de datos en la transaction actual. pendiente que el Ingreso master
//	 * haga commit para que los cambios bajen a la base de datos.
//	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Ingreso.
//	 * @throws Exception - Cualquier error que no corresponda a la base de datos
//	 */
//	public void updateIngreso(Ingreso Ingreso) throws SQLException, Exception {
//
//		String sql = "UPDATE Ingreso SET ";
//	sql += "NAME='" + Ingreso.getNombre() + "',";
//	sql += " WHERE ID = " + Ingreso.getId();
//
//
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//	}

	/**
	 * Metodo que elimina el Ingreso que entra como parametro en la base de datos.
	 * @param Ingreso - el Ingreso a borrar. Ingreso !=  null
	 * <b> post: </b> se ha borrado el Ingreso en la base de datos en la transaction actual. pendiente que el Ingreso master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Ingreso.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteIngreso(Ingreso Ingreso) throws SQLException, Exception {

		String sql = "DELETE FROM Ingreso";
		sql += " WHERE IDRESTAURANTE = " + Ingreso.getIdRestaurante();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
		
		

}