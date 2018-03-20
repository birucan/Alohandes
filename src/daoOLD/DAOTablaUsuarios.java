package daoOLD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vosOLD.Usuario;

public class DAOTablaUsuarios {



	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOUsuario
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaUsuarios() {
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
	 * Metodo que, usando la conexión a la base de datos, saca todos los Usuarios de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM UsuarioS;
	 * @return Arraylist con los Usuarios de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Usuario> darUsuarios() throws SQLException, Exception {
		ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM Usuario";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			String a = rs.getString("IDENTIFICACION");
			String b = rs.getString("CORREO");
			String c = rs.getString("TIPO");
			Usuarios.add(new Usuario(id, name, a, b, c));
		}
		return Usuarios;
	}


	/**
	 * Metodo que busca el/los Usuarios con el nombre que entra como parametro.
	 * @param name - Nombre de el/los Usuarios a buscar
	 * @return ArrayList con los Usuarios encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Usuario> buscarUsuariosPorName(String name) throws SQLException, Exception {
		ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM Usuario WHERE NOMBRE ='" + name + "'";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name2 = rs.getString("NOMBRE");
			Long id = rs.getLong("ID");
			String a = rs.getString("IDENTIFICACION");
			String b = rs.getString("CORREO");
			String c = rs.getString("TIPO");
			Usuarios.add(new Usuario(id, name, a, b, c));
		}

		return Usuarios;
	}
	
	/**
	 * Metodo que busca el Usuario con el id que entra como parametro.
	 * @param name - Id de el Usuario a buscar
	 * @return Usuario encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Usuario buscarUsuarioPorId(Long id) throws SQLException, Exception 
	{
		Usuario Usuario = null;

		String sql = "SELECT * FROM Usuario WHERE ID = " + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String name = rs.getString("NOMBRE");
			Long id2 = rs.getLong("ID");
			String a = rs.getString("IDENTIFICACION");
			String b = rs.getString("CORREO");
			String c = rs.getString("TIPO");
			Usuario = new Usuario(id2, name, a, b, c);
		}

		return Usuario;
	}

	/**
	 * Metodo que agrega el Usuario que entra como parametro a la base de datos.
	 * @param Usuario - el Usuario a agregar. Usuario !=  null
	 * <b> post: </b> se ha agregado el Usuario a la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que el Usuario baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Usuario a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addUsuario(Usuario Usuario) throws SQLException, Exception {


		 String sql = "INSERT INTO USUARIO (id, nombre, identificacion, correo, tipo) VALUES (";
		  sql += Usuario.getId() + ", '";
		  sql += Usuario.getNombre() +"', '";
		  sql += Usuario.getIdentificacion()+"', '";
		  sql += Usuario.getCorreo()+"', '";
		  sql += Usuario.getTipoUsuario()+"')";
		


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que actualiza el Usuario que entra como parametro en la base de datos.
	 * @param Usuario - el Usuario a actualizar. Usuario !=  null
	 * <b> post: </b> se ha actualizado el Usuario en la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Usuario.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateUsuario(Usuario Usuario) throws SQLException, Exception {

		String sql = "UPDATE Usuario SET ";
		sql += "NOMBRE='" + Usuario.getNombre() + "',";
		sql += " WHERE ID = " + Usuario.getId();


		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el Usuario que entra como parametro en la base de datos.
	 * @param Usuario - el Usuario a borrar. Usuario !=  null
	 * <b> post: </b> se ha borrado el Usuario en la base de datos en la transaction actual. pendiente que el Usuario master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Usuario.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteUsuario(Usuario Usuario) throws SQLException, Exception {

		String sql = "DELETE FROM Usuario";
		sql += " WHERE ID = " + Usuario.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void makeAdmin(int idUsuario) {
		try {
			Usuario foo = buscarUsuarioPorId((long) idUsuario);
			if(foo.getTipoUsuario().contains("admin")){
				foo.setTipoUsuario("cliente");
				updateUsuario(foo);
			}else if(foo.getTipoUsuario().contains("cliente")){
				foo.setTipoUsuario("admin");
				updateUsuario(foo);
			}else{
				foo.setTipoUsuario("admin");
				updateUsuario(foo);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}