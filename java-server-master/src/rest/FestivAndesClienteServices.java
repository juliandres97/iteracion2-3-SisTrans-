package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import tm.FestivAndesMaster;
import vos.*;

/**
 * @author Julián
 *
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("client")
public class FestivAndesClienteServices {

	/**
	 * 
	 */
	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la
	 * conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Atributo que indica si un administrador ha iniciado sesion o no.
	 */
	private static boolean clientSessionStarted = false;

	/**
	 * Atributo que identifica al cliente actual.
	 */
	private static Cliente currentClient = null;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el
	 * deploy actual dentro del servidor.
	 * 
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	/**
	 * @param exc
	 *            - Excepcion que se pasa por parametro para darle el formato
	 *            especificado.
	 * @return El mensaje de error, segun la excepxion ocurrida.
	 */
	private String doErrorMessage(Exception exc) {
		return "{ \"ERROR\": \"" + exc.getMessage() + "\" }";
	}

	private String doJSONMessage(String key, String value) {
		return "{ \"" + key + "\" :\"" + value + "\" }";
	}

	/**
	 * 
	 * @param docIdUser
	 * @param userPssWrd
	 * @return
	 */
	@GET
	@Path("/auth/client={docIdUser}/psswrd={userPssWrd}")
	public Response auth(@PathParam("docIdUser") String docIdUser, @PathParam("userPssWrd") String userPssWrd)
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Cliente cliente = null;
		try {
			cliente = tm.searchClientByDocIdPassword(docIdUser, userPssWrd);

			if (cliente != null) {
				currentClient = cliente;
				clientSessionStarted = true;
				return Response.status(200).entity(doJSONMessage("Bienvenido", currentClient.getNombre())).build();
			} else {
				currentClient = null;
				clientSessionStarted = false;
				return Response.status(404).entity(doJSONMessage("NOT FOUND", "Recurso no encontrado.")).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}

	@POST
	@Path("/regpref")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarPreferencia(GeneroList generos)
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (clientSessionStarted) {			
			try {
				tm.addGeneros(currentClient, generos);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(generos).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	@POST
	@Path("/buyboletareg")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprarBoletaReg(Boleta boleta)
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (clientSessionStarted) {			
			try {
				tm.comprarBoletaReg(currentClient, boleta);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(boleta).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	
	@POST
	@Path("/buyboletasreg")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprarBoletas(BoletaList boletas)
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (clientSessionStarted) {			
			try {
				tm.comprarBoletasReg(currentClient, boletas);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(boletas).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	@DELETE
	@Path("/returnboletareg")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response regresarBoleta(Boleta boleta)
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (clientSessionStarted) {			
			try {
				tm.regresarBoleta(currentClient, boleta);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(boleta).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	@POST
	@Path("/realizarabono")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response realizarAbono(Abono abono)
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (clientSessionStarted) {			
			try {
				tm.realizarAbono(currentClient, abono);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(abono).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
}
