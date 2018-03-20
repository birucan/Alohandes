package daoOLD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vosOLD.Menu;

public class DAOTablaMenus {



	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOMenu
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaMenus() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los Menus de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM MenuS;
	 * @return Arraylist con los Menus de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Menu> darMenus() throws SQLException, Exception {
		ArrayList<Menu> Menus = new ArrayList<Menu>();

		String sql = "SELECT * FROM Menu";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {

			Long id = rs.getLong("ID");
			Long idr = rs.getLong("IDREST");
			Long ide = rs.getLong("IDENTRADA");
			Long idf = rs.getLong("IDFUERTE");
			Long idp = rs.getLong("IDPOSTRE");
			Long idb = rs.getLong("IDBEBIDA");
			Long ida = rs.getLong("IDACOM");
			Long p = rs.getLong("PRECIO");
			
			Menus.add(new Menu(id, idr, ide, idf, idp, idb, ida, p));
		}
		return Menus;
	}


	
	/**
	 * Metodo que busca el Menu con el id que entra como parametro.
	 * @param name - Id de el Menu a buscar
	 * @return Menu encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Menu buscarMenuPorId(Long id) throws SQLException, Exception 
	{
		Menu Menu = null;

		String sql = "SELECT * FROM Menu WHERE ID =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Long id2 = rs.getLong("ID");
			Long idr = rs.getLong("IDREST");
			Long ide = rs.getLong("IDENTRADA");
			Long idf = rs.getLong("IDFUERTE");
			Long idp = rs.getLong("IDPOSTRE");
			Long idb = rs.getLong("IDBEBIDA");
			Long ida = rs.getLong("IDACOM");
			Long p = rs.getLong("PRECIO");
			Menu = new Menu(id2, idr, ide, idf, idp, idb, ida, p);
		}

		return Menu;
	}

	/**
	 * Metodo que agrega el Menu que entra como parametro a la base de datos.
	 * @param Menu - el Menu a agregar. Menu !=  null
	 * <b> post: </b> se ha agregado el Menu a la base de datos en la transaction actual. pendiente que el Menu master
	 * haga commit para que el Menu baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Menu a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addMenu(Menu Menu) throws SQLException, Exception {


		 String sql = "INSERT INTO Menu VALUES (";
		  sql += Menu.getId() + ", ";
		  sql += Menu.getRestaurante() +",";
		  sql += Menu.getEntrada()+", ";
		  sql += Menu.getFuerte()+", ";
		  sql += Menu.getPostre()+", ";
		  sql += Menu.getBebida()+", ";
		  sql += Menu.getAcom()+", ";
		  sql += Menu.getPrecio()+")";
		


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	

	/**
	 * Metodo que elimina el Menu que entra como parametro en la base de datos.
	 * @param Menu - el Menu a borrar. Menu !=  null
	 * <b> post: </b> se ha borrado el Menu en la base de datos en la transaction actual. pendiente que el Menu master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Menu.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteMenu(Menu Menu) throws SQLException, Exception {

		String sql = "DELETE FROM Menu";
		sql += " WHERE ID = " + Menu.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
		
		
}
