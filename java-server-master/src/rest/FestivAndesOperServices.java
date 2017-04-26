package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.FuncionRealizada;
import vos.Staff;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("oper")
public class FestivAndesOperServices {
	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la
	 * conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Atributo que indica si un administrador ha iniciado sesion o no.
	 */
	private static boolean staffSessionStarted = false;

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
	@Path("/auth/oper={docIdUser}/psswrd={userPssWrd}")
	public Response auth(@PathParam("docIdUser") String docIdUser, @PathParam("userPssWrd") String userPssWrd) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Staff staff = null;
		try {
			staff = tm.searchOperarioByDocIdPassword(docIdUser, userPssWrd);

			if (staff != null) {
				staffSessionStarted = true;
				return Response.status(200)
						.entity(doJSONMessage("Bienvenido", staff.getNombre() + " (" + staff.getRol() + ")")).build();
			} else {
				staffSessionStarted = false;
				return Response.status(404).entity(doJSONMessage("NOT FOUND", "Recurso no encontrado.")).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	@POST
	@Path("regreafuncion")
	public Response registrarRealizacionFuncion(FuncionRealizada funcion) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.registrarRealizacionFuncion(funcion);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(funcion).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
}
