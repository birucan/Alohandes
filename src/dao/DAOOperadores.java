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

	/**
	 * Valida si existe el sitio con la informacion suministrada
	 */
	public boolean existeSitio(long idOperador, long idSitio) throws Exception {
		String sql ="SELECT * FROM SITIOS WHERE SITIOS.IDSITIO = "+idSitio+" AND SITIOS.IDOPERADOR = "+idOperador+";";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		if (rs.next()){
			return true;
		}
		return false ;
	}

	public boolean estaHabilitada(long idoperador, long idsitio)throws Exception {

		String sql ="SELECT SITIOS.DISPONIBLE FROM SITIOS WHERE SITIOS.IDSITIO = "+idSitio+" AND SITIOS.IDOPERADOR = "+idOperador+";";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		if (rs.next()){
			int hab = rs.getInt("DISPONIBLE");
			if (hab == 1){
				return true ;
			}
			else{
				return false;
			}
		}

		return false;

	}

	public ArrayList<Contrato> getContratosSitio(long idsitio)throws Exception{
		String sql ="SELECT * FROM CONTRATOS WHERE IDSITIO = "+ idsitio +";";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		ArrayList<Contrato> contras = new ArrayList<Contrato>()
		while(rs.next()){
			Long idcont = rs.getLong("IDCONTRATO");
			Long idcli = rs.getLong("IDCLIENTE");
			Long idsitio = rs.getLong("IDSITIO");
			String estado = rs.getString("ESTADO");
			String fechain = rs.getString ("FECHAIN");
			String fechaen = rs.getString ("FECHAEN");
			String fechaped = rs.getString ("FECHAPED");
			Long idev = rs.getString("IDEVENTO");
			double costo = rs.getDouble("COSTO");
			double costoextra = rs.getDouble("COSTOEXTRA");
			double costotot = rs.getDouble("COSTOTOT");
			if (!estado.contains("cancelado")){
				Contrato con = new Contrato (idcon, idcli, idsitio,estado, fechain, fechaen, costo, costoextra, contotot, fechaped, idev);
				contras.add(con);
			}

		}

	}
	
	public void organizarReserva(long idsitio){
							
	}


	public void deshabilitarSitio(long idoperador, long idsitio)throws Exception{
		if (existeSitio(idOperador, idSitio)){
			if (esHabilitada(idoperador, idsitio)){
				String sql ="UPDATE SITIOS SET SITIOS.DISPONIBLE =0 WHERE SITIOS.IDSITIO = "+idSitio+" AND SITIOS.IDOPERADOR = "+idOperador+";";
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();
				organizarReserva(idsitio);
			}
			else
			{
				throw new Exception ("El alojamiento ya se encuentra desabilitado");
			}
		}
		else{
			throw new Exception ("No existe el sitio especificado");
		}

	}

	public void habilidarSitio(long idoperador, long idsitio){
		if (existeSitio(idOperador, idSitio)){
			if (!esHabilitada(idoperador, idsitio)){
				String sql ="UPDATE SITIOS SET SITIOS.DISPONIBLE =1 WHERE SITIOS.IDSITIO = "+idSitio+" AND SITIOS.IDOPERADOR = "+idOperador+";";
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs = prepStmt.executeQuery();
			}
			else {
				throw new Exception ("El alojamiento ya se encuentra habilitado")
			}

		}
		else{
			throw new Exception ("No existe el sitio especificado");
		}
	}
}
