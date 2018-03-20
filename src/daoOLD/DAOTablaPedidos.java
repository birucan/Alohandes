
	package daoOLD;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

import vosOLD.Pedido;

	public class DAOTablaPedidos {



		/**
		 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
		 */
		private ArrayList<Object> recursos;

		/**
		 * Atributo que genera la conexión a la base de datos
		 */
		private Connection conn;

		/**
		 * Metodo constructor que crea DAOPedido
		 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
		 */
		public DAOTablaPedidos() {
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
		 * Metodo que, usando la conexión a la base de datos, saca todos los Pedidos de la base de datos
		 * <b>SQL Statement:</b> SELECT * FROM PedidoS;
		 * @return Arraylist con los Pedidos de la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public ArrayList<Pedido> darPedidos() throws SQLException, Exception {
			ArrayList<Pedido> Pedidos = new ArrayList<Pedido>();
			boolean aceptado;

			String sql = "SELECT * FROM Pedido";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				Long timestamp = rs.getLong("TIMESTAMP");
				Long idCliente = rs.getLong("IDCLIENTE");
				Long idRestaurante = rs.getLong("IDRESTAURANTE");
				Long idProducto = rs.getLong("IDPRODUCTO");
				Long idMenu = rs.getLong("IDMENU");
				Long Laceptado =rs.getLong("ACEPTADO");
				if(Laceptado==1){
					aceptado = true;
				}else{
					aceptado = false;
				}
				Long precio = rs.getLong("PRECIO");		
				Pedidos.add(new Pedido(timestamp, idCliente, idRestaurante, idProducto, idMenu, aceptado, precio));
			}
			return Pedidos;
		}


//		/**
//		 * Metodo que busca el/los Pedidos con el nombre que entra como parametro.
//		 * @param name - Nombre de el/los Pedidos a buscar
//		 * @return ArrayList con los Pedidos encontrados
//		 * @throws SQLException - Cualquier error que la base de datos arroje.
//		 * @throws Exception - Cualquier error que no corresponda a la base de datos
//		 */
//		public ArrayList<Pedido> buscarPedidosPorName(String name) throws SQLException, Exception {
//			ArrayList<Pedido> Pedidos = new ArrayList<Pedido>();
//
//			String sql = "SELECT * FROM Pedido WHERE NAME ='" + name + "'";
//
//			PreparedStatement prepStmt = conn.prepareStatement(sql);
//			recursos.add(prepStmt);
//			ResultSet rs = prepStmt.executeQuery();
//
//			while (rs.next()) {
//				String name2 = rs.getString("NAME");
//				Long id = rs.getLong("ID");
//				String a = rs.getString("IDENTIFICACION");
//				String b = rs.getString("CORREO");
//				String c = rs.getString("TIPO");
//				Pedidos.add(new Pedido(id, name, a, b, c));
//			}
//
//			return Pedidos;
//		}
//		
		/**
		 * Metodo que busca el Pedido con el id que entra como parametro.
		 * @param name - Id de el Pedido a buscar
		 * @return Pedido encontrado
		 * @throws SQLException - Cualquier error que la base de datos arroje.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public Pedido buscarPedidoPorTimestamp(Long timestamp) throws SQLException, Exception 
		{
			Pedido Pedido = null;
			boolean aceptado;

			String sql = "SELECT * FROM Pedido WHERE TIMESTAMP =" + timestamp;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				Long timestamp2 = rs.getLong("TIMESTAMP");
				Long idCliente = rs.getLong("IDCLIENTE");
				Long idRestaurante = rs.getLong("IDRESTAURANTE");
				Long idProducto = rs.getLong("IDPRODUCTO");
				Long idMenu = rs.getLong("IDMENU");
				Long Laceptado =rs.getLong("ACEPTADO");
				if(Laceptado==1){
					aceptado = true;
				}else{
					aceptado = false;
				}
				Long precio = rs.getLong("PRECIO");		
				Pedido = new Pedido(timestamp2, idCliente, idRestaurante, idProducto, idMenu, aceptado, precio);
			}
				return Pedido;
			}

			
		

		/**
		 * Metodo que agrega el Pedido que entra como parametro a la base de datos.
		 * @param Pedido - el Pedido a agregar. Pedido !=  null
		 * <b> post: </b> se ha agregado el Pedido a la base de datos en la transaction actual. pendiente que el Pedido master
		 * haga commit para que el Pedido baje  a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el Pedido a la base de datos
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void addPedido(Pedido Pedido) throws SQLException, Exception {


			 String sql = "INSERT INTO Pedido VALUES (";
			  sql += Pedido.getTimestamp() + ", ";
			  sql += Pedido.getIdCliente() + ", ";
			  sql += Pedido.getIdRestaurante()+", ";
			  sql += Pedido.getIdProducto()+", ";
			  sql += Pedido.getIdMenu()+", ";
			  //oracle no tiene tipo boolean y por default es false
			  sql += "0,";
			  sql += Pedido.getPrecio()+")";
			


			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
		
		/**
		 * Metodo que actualiza el Pedido que entra como parametro en la base de datos.
		 * @param Pedido - el Pedido a actualizar. Pedido !=  null
		 * <b> post: </b> se ha actualizado el Pedido en la base de datos en la transaction actual. pendiente que el Pedido master
		 * haga commit para que los cambios bajen a la base de datos.
		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Pedido.
		 * @throws Exception - Cualquier error que no corresponda a la base de datos
		 */
		public void aceptarPedido(Pedido Pedido) throws SQLException, Exception {
			Pedido.setAceptado(true);
			String sql = "UPDATE Pedido SET ";
			sql += "ACEPTADO=" + "1,";
			sql += " WHERE TIMESTAMP = " + Pedido.getTimestamp();


			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			//TODO add registro de precio al restaurante nojoda
		}

		public String darPedidosUser(long idUsuario) throws Exception {
			Pedido foo= null;
			boolean aceptado;
			String returner="";

			String sql = "SELECT * FROM Pedido WHERE IDCLIENTE ="+idUsuario;

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			
			while (rs.next()) {
				Long timestamp = rs.getLong("TIMESTAMP");
				Long idCliente = rs.getLong("IDCLIENTE");
				Long idRestaurante = rs.getLong("IDRESTAURANTE");
				Long idProducto = rs.getLong("IDPRODUCTO");
				Long idMenu = rs.getLong("IDMENU");
				Long Laceptado =rs.getLong("ACEPTADO");
				if(Laceptado==1){
					aceptado = true;
				}else{
					aceptado = false;
				}
				Long precio = rs.getLong("PRECIO");		
				foo = new Pedido(timestamp, idCliente, idRestaurante, idProducto, idMenu, aceptado, precio);
				returner += foo.toString();
			}
			return returner;
		}

//		/**
//		 * Metodo que elimina el Pedido que entra como parametro en la base de datos.
//		 * @param Pedido - el Pedido a borrar. Pedido !=  null
//		 * <b> post: </b> se ha borrado el Pedido en la base de datos en la transaction actual. pendiente que el Pedido master
//		 * haga commit para que los cambios bajen a la base de datos.
//		 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el Pedido.
//		 * @throws Exception - Cualquier error que no corresponda a la base de datos
//		 */
//		public void deletePedido(Pedido Pedido) throws SQLException, Exception {
//
//			String sql = "DELETE FROM Pedido";
//			sql += " WHERE ID = " + Pedido.getId();
//
//			PreparedStatement prepStmt = conn.prepareStatement(sql);
//			recursos.add(prepStmt);
//			prepStmt.executeQuery();
//		}



}

