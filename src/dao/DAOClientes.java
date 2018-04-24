package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import vos.Cliente;
import vos.Contrato;
import vos.Sitio;
import vosOLD.ejemplo;



/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOClientes {


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
	public DAOClientes() {
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

	public void addCliente(Cliente cliente) throws Exception {
		 String sql = "INSERT INTO CLIENTES (NOMBRE, APELLIDO, VINCULO) VALUES ('";
		  sql += cliente.getNombre() +"', '";
		  sql += cliente.getApellido()+"', '";
		  sql += cliente.getVinculo()+"')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void addReserva(Contrato contrato, long idCliente, long idSitio, String servicios) throws Exception {
		
		 String[] serviciosplit = servicios.split(".");
		 
		 String sql = "INSERT INTO CONTRATOS (IDCONTRATO, IDCLIENTE, IDSITIO, ESTADO, FECHAIN, FECHAEN, COSTO, COSTOEXTRA, COSTOTOT, FECHAPEDIDO, IDEVENTO) VALUES (";
		  sql += contrato.getIdcontrato() + ",";
		  sql += idCliente +",";
		  sql += idSitio +", '";
		  sql += "reserva',"; 
		  sql += "TO_DATE('"+contrato.getFechain() +"', 'DD-MM-YYYY'),";
		  sql += "TO_DATE('"+contrato.getFechaen() +"', 'DD-MM-YYYY'),";
		  sql += contrato.getCosto() +", ";
		  sql += contrato.getCostoextra()+",";
		  sql += contrato.getCosto()+contrato.getCostoextra() +",";
		  
		  
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  LocalDateTime now = LocalDateTime.now();
		  
		  sql += "TO_DATE('"+dtf.format(now)+"', 'DD-MM-YYYY'), ";
		  sql += contrato.getIdevento()+")";
		  System.out.println(sql);
		  
		  	
		 
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		
		
	}

	public void addSercon(Long idcontrato, String[] serviciosplit) throws Exception {
		String sql = "";
		System.out.println(serviciosplit.length);
		for (int a = 0; a < serviciosplit.length; a++) {
			sql = "";
			sql += "INSERT INTO SERCON (IDCONTRATO, IDSERVICIO) VALUES (" +idcontrato+", "+ serviciosplit[a]+") ";
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
	}

	public void cancelReserva(long idCliente, long idReserva) throws Exception {
		String sql = "UPDATE CONTRATOS SET ESTADO = 'cancelado' WHERE IDCLIENTE ="+idCliente+" AND IDCONTRATO = "+idReserva;
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public boolean VerificarEvento(long idEvento, String[] split, Contrato contrato, String servicios) throws Exception {
		boolean response = true;
		String sql = "SELECT IDEVENTO, COUNT(*) FOO FROM CLIENTES WHERE IDEVENTO="+idEvento+" GROUP BY IDEVENTO";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		int cantidad=0;
		while (rs.next()){
		cantidad = rs.getInt("FOO");
		}
		if(verSitios(idEvento, split, contrato, servicios)){
			return response;	
		}else{return false;}
		
	}
	
	private boolean verSitios(long idEvento, String[] split, Contrato contrato, String servicios) throws Exception{
		boolean response = true;
		ArrayList<Long> sitios = new ArrayList<Long>();
		String sql = "SELECT * FROM SITIOS WHERE DISPONIBLE = 1";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next()){
			Long niu = rs.getLong("IDSITIO");
			sitios.add(niu);
		}
		sitios = verServicios(sitios, split);
		ArrayList<Long> gente = contarGente(idEvento);
		if(sitios.size()>=gente.size()){
			for (int a = 0; a < gente.size(); a++) {
				addReserva(contrato, gente.get(a), sitios.get(a), servicios);
				contrato.setIdcontrato(contrato.getIdcontrato()+1);
			}
			return true;
		}else{
			return false;
		}
	}
	




	private ArrayList<Long> verServicios(ArrayList<Long> sitios, String[] split) throws Exception {
		ArrayList<Long> sitiosSirven = new ArrayList<Long>();
		boolean agregar= true;
		for (int a = 0; a < sitios.size(); a++) {
			agregar =true;
			for (int b = 0; b < split.length; b++) {
				String sql = "SELECT * FROM SERSIT WHERE IDSITIO ="+sitios.get(a)+" AND IDSERVICIO = "+split[b];
				if(verificador(sql)){
					
				}else{
					agregar=false;
					break;
				}
			}
				if(agregar){
					sitiosSirven.add(sitios.get(a));
					System.out.println("id de sitio que cumple:\n");
					System.out.println(sitios.get(a));
				}
			
		}
		return sitiosSirven;
	}

	private boolean verificador(String sql) throws Exception {
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		long tiene = 0;
		while(rs.next()){
			tiene = 1;
		}
		if(tiene == 0){
			return false;
		}else{
			return true;
		}
	
	}
	private ArrayList<Long> contarGente(long idEvento) throws Exception {
		String sql = "SELECT * FROM CLIENTES WHERE IDEVENTO = "+idEvento;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		ArrayList<Long> gente = new ArrayList<Long>();
		while(rs.next()){
			Long niu = rs.getLong("IDCLIENTE");
			gente.add(niu);
		}
		return gente;
	}

	public void cancelEvento(long idEvento) throws Exception {
		String sql= "SELECT * FROM CONTRATOS WHERE IDEVENTO = "+idEvento;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		ArrayList<Long> idsclientes = new ArrayList<Long>();
		ArrayList<Long> idsreservas = new ArrayList<Long>();
		
		while(rs.next()){
			Long niu = rs.getLong("IDCLIENTE");
			Long niu2 = rs.getLong("IDCONTRATO");
			
			idsclientes.add(niu);
			idsreservas.add(niu2);
		}
		
		for (int a = 0; a < idsclientes.size(); a++) {
			cancelReserva(idsclientes.get(a), idsreservas.get(a));
		}
	}
	
	/*
	 * 
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("NAME");
			Long id = rs.getLong("ID");
			ejemplos.add(new ejemplo(id, name));
		}
		return ejemplos;
	 */
}
