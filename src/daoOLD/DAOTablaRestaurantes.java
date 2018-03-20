package daoOLD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vosOLD.Restaurante;

public class DAOTablaRestaurantes {

	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAORestaurante
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaRestaurantes() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los Restaurantes de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM RestauranteS;
	 * @return Arraylist con los Restaurantes de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Restaurante> darRestaurantes() throws SQLException, Exception {
		ArrayList<Restaurante> Restaurantes = new ArrayList<Restaurante>();

		String sql = "SELECT * FROM Restaurante";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			String a = rs.getString("DESCRIPCION");
			String b = rs.getString("TIPOCOMIDA");
			String c = rs.getString("PAGINAWEB");
			Long idz = rs.getLong("ZONA");
			Restaurantes.add(new Restaurante(id, name, a, b, c, idz));
		}
		return Restaurantes;
	}


	/**
	 * Metodo que busca el/los Restaurantes con el nombre que entra como parametro.
	 * @param name - Nombre de el/los Restaurantes a buscar
	 * @return ArrayList con los Restaurantes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Restaurante> buscarRestaurantesPorName(String name) throws SQLException, Exception {
		ArrayList<Restaurante> Restaurantes = new ArrayList<Restaurante>();

		String sql = "SELECT * FROM Restaurante WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			//String name2 = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			String a = rs.getString("DESCRIPCION");
			String b = rs.getString("TIPOCOMIDA");
			String c = rs.getString("PAGINAWEB");
			Long idz = rs.getLong("ZONA");
			Restaurantes.add(new Restaurante(id, name, a, b, c, idz));
		}

		return Restaurantes;
	}
	
	/**
	 * Metodo que busca el Restaurante con el id que entra como parametro.
	 * @param name - Id de el Restaurante a buscar
	 * @return Restaurante encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Restaurante buscarRestaurantePorId(Long id) throws SQLException, Exception 
	{
		Restaurante Restaurante = null;

		String sql = "SELECT * FROM Restaurante WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id2 = rs.getLong("ID");
			String a = rs.getString("DESCRIPCION");
			String b = rs.getString("TIPOCOMIDA");
			String c = rs.getString("PAGINAWEB");
			Long idz = rs.getLong("ZONA");
			Restaurante = new Restaurante(id2, name, a, b, c, idz);
		}

		return Restaurante;
	}

	/**
	 * Metodo que agrega el Restaurante que entra como parametro a la base de datos.
	 * @param Restaurante - el Restaurante a agregar. Restaurante !=  null
	 * <b> post: </b> se ha agregado el Restaurante a la base de datos en la transaction actual. pendiente que el Restaurante master
	 * haga commit para que el Restaurante baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Restaurante a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addRestaurante(Restaurante Restaurante) throws SQLException, Exception {


		 String sql = "INSERT INTO Restaurante VALUES (";
		  sql += Restaurante.getId() + ", '";
		  sql += Restaurante.getNombre() +"', '";
		  sql += Restaurante.getDescripcion()+"', '";
		  sql += Restaurante.getTipoComida()+"', '";
		  sql += Restaurante.getPaginaWeb()+"')";
		


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Restaurante que entra como parametro en la base de datos.
	 * @param Restaurante - el Restaurante a actualizar. Restaurante !=  null
	 * <b> post: </b> se ha actualizado el Restaurante en la base de datos en la transaction actual. pendiente que el Restaurante master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Restaurante.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateRestaurante(Restaurante Restaurante) throws SQLException, Exception {

		String sql = "UPDATE Restaurante SET ";
		sql += "NAME='" + Restaurante.getNombre() + "',";
		sql += " WHERE ID = " + Restaurante.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Restaurante que entra como parametro en la base de datos.
	 * @param Restaurante - el Restaurante a borrar. Restaurante !=  null
	 * <b> post: </b> se ha borrado el Restaurante en la base de datos en la transaction actual. pendiente que el Restaurante master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Restaurante.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteRestaurante(Restaurante Restaurante) throws SQLException, Exception {

		String sql = "DELETE FROM Restaurante";
		sql += " WHERE ID = " + Restaurante.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
		
	
}
