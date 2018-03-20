package daoOLD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vosOLD.Zona;

public class DAOTablaZonas {



	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOZona
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaZonas() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los Zonas de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM ZonaS;
	 * @return Arraylist con los Zonas de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Zona> darZonas() throws SQLException, Exception {
		ArrayList<Zona> Zonas = new ArrayList<Zona>();

		String sql = "SELECT * FROM ZONA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String a = rs.getString("DESCRIPCION");

			Zonas.add(new Zona(id, a));
		}
		return Zonas;
	}
	
	public ArrayList<Zona> darZonaO(String order) throws Exception{

			ArrayList<Zona> Zonas = new ArrayList<Zona>();

			String sql = "SELECT * FROM Zona ORDER BY "+ order;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("ID");
				String a = rs.getString("DESCRIPCION");

				Zonas.add(new Zona(id, a));
			}
			return Zonas;
	}


	/**
	 * Metodo que busca el/los Zonas con el nombre que entra como parametro.
	 * @param name - Nombre de el/los Zonas a buscar
	 * @return ArrayList con los Zonas encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Zona> buscarZonasPorName(String name) throws SQLException, Exception {
		ArrayList<Zona> Zonas = new ArrayList<Zona>();

		String sql = "SELECT * FROM Zona WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String a = rs.getString("DESCRIPCION");

			Zonas.add(new Zona(id, a));
		}

		return Zonas;
	}
	
	/**
	 * Metodo que busca el Zona con el id que entra como parametro.
	 * @param name - Id de el Zona a buscar
	 * @return Zona encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Zona buscarZonaPorId(Long id) throws SQLException, Exception 
	{
		Zona Zona = null;

		String sql = "SELECT * FROM Zona WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("ID");
			String a = rs.getString("DESCRIPCION");

			Zona = new Zona(id2, a);
		}

		return Zona;
	}
	

	/**
	 * Metodo que agrega el Zona que entra como parametro a la base de datos.
	 * @param Zona - el Zona a agregar. Zona !=  null
	 * <b> post: </b> se ha agregado el Zona a la base de datos en la transaction actual. pendiente que el Zona master
	 * haga commit para que el Zona baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Zona a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addZona(Zona Zona) throws SQLException, Exception {


		 String sql = "INSERT INTO Zona VALUES (";
		  sql += Zona.getId() + ", '";
		  sql += Zona.getDescripcion() +"')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
//	/**
//	 * Metodo que actualiza el Zona que entra como parametro en la base de datos.
//	 * @param Zona - el Zona a actualizar. Zona !=  null
//	 * <b> post: </b> se ha actualizado el Zona en la base de datos en la transaction actual. pendiente que el Zona master
//	 * haga commit para que los cambios bajen a la base de datos.
//	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Zona.
//	 * @throws Exception - Cualquier error que no corresponda a la base de datos
//	 */
//	public void updateZona(Zona Zona) throws SQLException, Exception {
//
//		String sql = "UPDATE Zona SET ";
//		sql += "NAME='" + Zona.getNombre() + "',";
//		sql += " WHERE ID = " + Zona.getId();
//
//
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//	}

	/**
	 * Metodo que elimina el Zona que entra como parametro en la base de datos.
	 * @param Zona - el Zona a borrar. Zona !=  null
	 * <b> post: </b> se ha borrado el Zona en la base de datos en la transaction actual. pendiente que el Zona master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Zona.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteZona(Zona Zona) throws SQLException, Exception {

		String sql = "DELETE FROM Zona";
		sql += " WHERE ID = " + Zona.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
		
		

}