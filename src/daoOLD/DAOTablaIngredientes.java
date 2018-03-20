package daoOLD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vosOLD.Ingrediente;

public class DAOTablaIngredientes {


	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOIngrediente
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaIngredientes() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los Ingredientes de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM IngredienteS;
	 * @return Arraylist con los Ingredientes de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ingrediente> darIngredientes() throws SQLException, Exception {
		ArrayList<Ingrediente> Ingredientes = new ArrayList<Ingrediente>();

		String sql = "SELECT * FROM Ingrediente";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			String a = rs.getString("DESCRIPCIONES");
			String b = rs.getString("DESCRIPCIONEN");
			Long c = rs.getLong("IDPROD");
			Ingredientes.add(new Ingrediente(name, a, b, c));
		}
		return Ingredientes;
	}


	/**
	 * Metodo que busca el/los Ingredientes con el nombre que entra como parametro.
	 * @param name - Nombre de el/los Ingredientes a buscar
	 * @return ArrayList con los Ingredientes encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ingrediente> buscarIngredientesPorName(String name) throws SQLException, Exception {
		ArrayList<Ingrediente> Ingredientes = new ArrayList<Ingrediente>();

		String sql = "SELECT * FROM Ingrediente WHERE NAME ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name2 = rs.getString("NOMBRE");
			String a = rs.getString("DESCRIPCIONES");
			String b = rs.getString("DESCRIPCIONEN");
			Long c = rs.getLong("IDPROD");
			Ingredientes.add(new Ingrediente(name, a, b, c));
		}

		return Ingredientes;
	}
	
//	/**
//	 * Metodo que busca el Ingrediente con el id que entra como parametro.
//	 * @param name - Id de el Ingrediente a buscar
//	 * @return Ingrediente encontrado
//	 * @throws SQLException - Cualquier error que la base de datos arroje.
//	 * @throws Exception - Cualquier error que no corresponda a la base de datos
//	 */
//	public Ingrediente buscarIngredientePorId(Long id) throws SQLException, Exception 
//	{
//		Ingrediente Ingrediente = null;
//
//		String sql = "SELECT * FROM Ingrediente WHERE ID =" + id;
//
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		ResultSet rs = prepStmt.executeQuery();
//
//		if(rs.next()) {
//			String name = rs.getString("NAME");
//			Long id2 = rs.getLong("ID");
//			String a = rs.getString("IDENTIFICACION");
//			String b = rs.getString("CORREO");
//			String c = rs.getString("TIPO");
//			Ingrediente = new Ingrediente(id2, name, a, b, c);
//		}
//
//		return Ingrediente;
//	}

	/**
	 * Metodo que agrega el Ingrediente que entra como parametro a la base de datos.
	 * @param Ingrediente - el Ingrediente a agregar. Ingrediente !=  null
	 * <b> post: </b> se ha agregado el Ingrediente a la base de datos en la transaction actual. pendiente que el Ingrediente master
	 * haga commit para que el Ingrediente baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Ingrediente a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addIngrediente(Ingrediente Ingrediente) throws SQLException, Exception {


		 String sql = "INSERT INTO Ingrediente (nombre, descripcionES, descripcionEN, idProd) VALUES ('";
		  sql += Ingrediente.getNombre() + "', '";
		  sql += Ingrediente.getDescripcionES() +"', '";
		  sql += Ingrediente.getDescripcionEN()+"', ";
		  sql += Ingrediente.getIdProd()+") ";
		


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	

}
