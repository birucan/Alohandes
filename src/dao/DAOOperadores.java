package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.Cliente;
import vos.Operador;
import vos.Sitio;




/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOOperadores {


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
	public DAOOperadores() {
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

	public void addOperador(Operador operador) throws Exception {
		 String sql = "INSERT INTO OPERADORES (NOMBRE, TIPO) VALUES ('";
		  sql += operador.getNombre() +"', '";
		  sql += operador.getTipo()+"')";
		  
		System.out.println(sql);  
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void addSitio(Sitio sitio, long idoperador) throws Exception {
		 String sql = "INSERT INTO SITIOS (IDOPERADOR, TIPO, DESCRIPCION) VALUES (";
		  sql += idoperador +", '";
		  sql += sitio.getTipo() + "', '";
		  sql += sitio.getDescripcion()+"')";
		  
		  System.out.println(sql);   
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}

	public void removeSitio(long idoperador, long idsitio) throws Exception {

		/*String sql = "SELECT * FROM contratos";
		boolean dependencia = false;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			if(rs.getLong("IDSITIO")==idsitio){
				dependencia = true;
			}
			
		}
		if(dependencia){
			return;
				}else{
					sql = "";
					sql = "DELETE FROM SITIOS"
				}*/
		String sql = "DELETE FROM SITIOS WHERE IDOPERADOR = "+idoperador+" AND IDSITIO = "+idsitio;
		 System.out.println(sql);   
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
	}

	public double getGanancias(long idoperador, int ano) throws Exception {
		double returner = 0;
		String sql = "with foo as(SELECT * FROM SITIOS NATURAL JOIN CONTRATOS) SELECT  SUM(ALL COSTOTOT) as returner FROM foo WHERE IDOPERADOR = "+ idoperador;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		if(rs.next()){
			returner = rs.getDouble("RETURNER");
		}
		return returner;
	}

	public List<Long> top20() throws Exception {

		String sql = "with foo as(SELECT * FROM CONTRATOS NATURAL JOIN SITIOS), fuu as(SELECT  IDSITIO, COUNT(*) as returner FROM foo GROUP BY IDSITIO ORDER BY RETURNER)SELECT * FROM fuu WHERE ROWNUM <21";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
        List<Long> returner = new ArrayList<Long>();
		while (rs.next()) {
			returner.add(rs.getLong("RETURNER"));
		}
	return returner;
}

	public ArrayList<Long> darClientesFrecuentes() throws Exception {
		String sql = "WITH FUU AS (SELECT IDCLIENTE, COUNT(*) AS FOO FROM CONTRATOS GROUP BY IDCLIENTE) SELECT * FROM FUU WHERE FOO >= 3";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
        
		ArrayList<Long> returner = new ArrayList<Long>();
		while (rs.next()) {
			returner.add(rs.getLong("IDCLIENTE"));
		}
	return returner;
	}
}
