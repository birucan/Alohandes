package restOLD;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tmOLD.RotondAndesTM;
import vosOLD.Ingrediente;
import vosOLD.Menu;
import vosOLD.Pedido;
import vosOLD.Producto;
import vosOLD.Restaurante;

@Path("restaurantes")
public class RestauranteServicio {
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
	public Response addrotonda(Restaurante rotonda) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.registrarRestaurante(rotonda);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rotonda).build();
	}
	
	@POST
	@Path("/{idRestaurante}/producto")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarProducto(@PathParam("idRestaurante") int idRestaurante, Producto producto){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.registrarProducto(idRestaurante, producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(producto).build();
	}
	
	@POST
	@Path("/{idRestaurante}/producto/{idProducto}/ingrediente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarIngrediente(@PathParam("idRestaurante") int idRestaurante,@PathParam("idProducto")int idProducto, Ingrediente ingrediente){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.registrarIngrediente(idRestaurante,idProducto, ingrediente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ingrediente).build();
	}
	@POST
	@Path("/{idRestaurante}/menu")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarMenu(@PathParam("idRestaurante")int idRestaurante, Menu menu){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.registrarMenu(idRestaurante, menu);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(menu).build();
	}
	
	@PUT
	@Path("{idRestaurante}/pedidos/{timestamp}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response aceptarPedido(@PathParam("idRestaurante")long idRestaurante,@PathParam("timestamp")long timestamp, Pedido pedido){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.aceptarPedido(idRestaurante, timestamp);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pedido).build();

	}
	
	@GET
	@Path("/{idRestaurante}/Rentabilidad/{timestampI}/{timestampF}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response darRentabilidad(@PathParam("idRestaurante")long idRestaurante,@PathParam("timestampI")long timestampI, @PathParam("timestampF")long timestampF){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		String response = "";
		try {
			tm.darRentabilidad(idRestaurante, timestampI, timestampF);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(response).build();
	}
	
}
