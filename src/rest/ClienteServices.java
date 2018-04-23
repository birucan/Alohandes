package rest;


import java.util.ArrayList;
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

import tm.AlohandesTM;
import vos.Cliente;
import vos.Contrato;
import vos.Sitio;

@Path("clientes")
public class ClienteServices {
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
	public Response addCliente(Cliente rotonda) {
		AlohandesTM tm = new AlohandesTM(getPath());
		try {
			tm.addCliente(rotonda);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rotonda).build();
	}
	
	@POST
	@Path("{idCliente}/addReserva/{idSitio}/{servicios}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReserva(Contrato rotonda, @PathParam("idCliente") long idCliente, @PathParam("idSitio") long idSitio, @PathParam("servicios") String Servicios) {
		AlohandesTM tm = new AlohandesTM(getPath());
		try {
			tm.addReserva(rotonda, idCliente, idSitio, Servicios);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rotonda).build();
	}
	@POST
	@Path("{idCliente}/cancelReserva/{idreserva}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelReserva(@PathParam("idCliente") long idCliente, @PathParam("idreserva") long idReserva) {
		AlohandesTM tm = new AlohandesTM(getPath());
		try {
			tm.cancelReserva(idCliente, idReserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(idCliente).build();
	}
	
	@GET
	@Path("top20")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGanancias() {
		AlohandesTM tm = new AlohandesTM(getPath());
		List<Long> returner =  new ArrayList<Long>();
		try {
			returner = tm.top20();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(returner).build();
	}
	
	@POST
	@Path("/reservaColectiva/{idEvento}/{servicios}/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReservaColectiva(@PathParam("idEvento") long idEvento, @PathParam("servicios") String Servicios) {
		AlohandesTM tm = new AlohandesTM(getPath());
		String response ="si aparece esto te jodiste papí";
		try {
			response = tm.addReservaColectiva(idEvento, Servicios);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(response).build();
	}
}
