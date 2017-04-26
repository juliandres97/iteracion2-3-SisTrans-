package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.Compania;
import vos.Staff;

public class FestivAndesCompaniaServices {
	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la
	 * conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Atributo que indica si un administrador ha iniciado sesion o no.
	 */
	private static boolean companiaSessionStarted = false;
	
	/**
	 * 
	 */
	private static Compania currentCompania = null;

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
	@Path("/auth/compania={docIdUser}/psswrd={userPssWrd}")
	public Response auth(@PathParam("docIdUser") String docIdUser, @PathParam("userPssWrd") String userPssWrd) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Compania compania = null;
		try {
			compania = tm.searchCompaniaByNitPassword(docIdUser, userPssWrd);

			if (compania != null) {
				companiaSessionStarted = true;
				currentCompania = compania;
				return Response.status(200)
						.entity(doJSONMessage("Bienvenido", currentCompania.getNombre())).build();
			} else {
				companiaSessionStarted = false;
				currentCompania = null;
				return Response.status(404).entity(doJSONMessage("NOT FOUND", "Recurso no encontrado.")).build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
}
