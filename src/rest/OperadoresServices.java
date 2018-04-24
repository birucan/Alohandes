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
import vos.Operador;
import vos.Sitio;

@Path("operadores")
public class OperadoresServices {
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
	public Response addOperador(Operador rotonda) {
		AlohandesTM tm = new AlohandesTM(getPath());
		try {
			tm.addOperador(rotonda);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rotonda).build();
	}
	
	@POST
	@Path("/addSitio/{idoperador}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSitio(Sitio rotonda, @PathParam("idoperador") long idoperador) {
		AlohandesTM tm = new AlohandesTM(getPath());
		try {
			tm.addSitio(rotonda, idoperador);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rotonda).build();
	}
	@POST
	@Path("{idoperador}/removeSitio/{idsitio}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSitio(@PathParam("idoperador") long idoperador,@PathParam("idsitio") long idsitio) {
		AlohandesTM tm = new AlohandesTM(getPath());
		try {
			tm.removeSitio(idoperador, idsitio);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(idsitio).build();
	}
	
	@GET
	@Path("{idoperador}/ganancias/{ano}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGanancias(@PathParam("idoperador") long idoperador,@PathParam("ano") int ano) {
		AlohandesTM tm = new AlohandesTM(getPath());
		double returner = 0;
		try {
			returner = tm.getGanancias(idoperador, ano);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(returner).build();
	}
	
	@GET
	@Path("frecuentes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientesFrecuentes() {
		AlohandesTM tm = new AlohandesTM(getPath());
		ArrayList<Long> returner = new ArrayList<Long>();
		try {
			returner = tm.getClientesFrecuentes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(returner).build();
	}
}
