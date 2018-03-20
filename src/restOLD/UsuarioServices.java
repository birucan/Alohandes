package restOLD;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tmOLD.RotondAndesTM;
import vosOLD.Pedido;
import vosOLD.PreferenciaCliente;
import vosOLD.Usuario;
import vosOLD.Zona;

@Path("usuarios")
public class UsuarioServices {
	@Context
	private ServletContext context;
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addrotonda(Usuario rotonda) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.registrarUsuario(rotonda);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rotonda).build();
	}
	
	@PUT
	@Path("/MakeAdmin/{idUsuario}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeAdmin(@PathParam("idUsuario") int idUsuario){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.makeAdmim(idUsuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(idUsuario).build();
	}
	
	@POST
	@Path("{idUsuario}/zona/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarZona(@PathParam("idUsuario") int idUsuario, Zona zona){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.registrarZona(idUsuario, zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
	}
	
	@POST
	@Path("{idUsuario}/preferencia/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarPreferencia(@PathParam("idUsuario") int idUsuario, PreferenciaCliente preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.registrarPreferencia(idUsuario, preferencia);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}
	
	@DELETE
	@Path("{idUsuario}/preferencia/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response quitarPreferencia(@PathParam("idUsuario") int idUsuario, PreferenciaCliente preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.quitarPreferencia(idUsuario, preferencia);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}
	@POST
	@Path("{idUsuario}/pedido/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarPedido(@PathParam("idUsuario") int idUsuario, Pedido pedido){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.registrarPedido(idUsuario, pedido);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pedido).build();
	}
	
	@GET
	@Path("zona/{orderBy}/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarZonas(@PathParam("orderBy") String order){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Zona> foo;
		try {
			 foo=tm.consultarZonas(order);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(foo).build();
	}
	@GET
	@Path("{idUsuario}/info/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response consultarInfo(@PathParam("idUsuario") long idUsuario){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		String response;
		try {
			response = tm.darTodoUsuario(idUsuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("productosMas/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response consultarProductosMas(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		String response;
		try {
			response = tm.darProductoMas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("productosMasDate/{time1}/{time2}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response consultarProductosMasFecha(@PathParam("time1")long timestampI, @PathParam("time2")long timestampF){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		String response;
		try {
			response = tm.darProductoMas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(response).build();
	}
	
}
