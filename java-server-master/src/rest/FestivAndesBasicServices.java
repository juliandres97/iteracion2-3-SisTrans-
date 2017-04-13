package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import tm.FestivAndesMaster;
import vos.Staff;
import vos.StaffList;

/**
 * @author Julián
 */
@Path("staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FestivAndesBasicServices {

	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la
	 * conexion actual.
	 */
	@Context
	private ServletContext context;

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
	 * 
	 * @param exc
	 *            - Excepcion que se pasa por parametro para darle el formato
	 *            especificado.
	 * @return El mensaje de error, segun la excepxion ocurrida.
	 */
	private String doErrorMessage(Exception exc) {
		return "{ \"ERROR\": \"" + exc.getMessage() + "\"}";
	}

	/**
	 * Metodo que expone servicio REST usando PUT que agrega el miembro que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/staff/crearmiembro
	 * 
	 * @param staff
	 *            - miembro a agregar
	 * @return Json con el miembro que agrego o Json con el error que se produjo
	 */
	@POST
	@Path("/crearmiembro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearMiembro(Staff staff) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try {
			tm.addStaff(staff);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(staff).build();
	}

	/**
	 * Metodo que expone servicio REST usando PUT que agrega la lista de
	 * miembros que recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/staff/crearmiembros
	 * 
	 * @param staffList
	 *            - miembros a agregar
	 * @return Json con los miembros que agrego o Json con el error que se
	 *         produjo
	 */
	@PUT
	@Path("/crearmiembros")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearMiembros(StaffList staffList) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try {
			tm.addMiembrosStaff(staffList);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(staffList).build();
	}

	/**
	 * Metodo que expone servicio REST usando POST que modifica el miembro que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/staff/actmiembro
	 * 
	 * @param staff
	 *            - miembro a actualizar
	 * @return Json con el miembro que actualizo o Json con el error que se
	 *         produjo
	 */
	@PUT
	@Path("/actmiembro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMiembro(Staff staff) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try {
			tm.updateStaff(staff);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(staff).build();
	}

	/**
	 * Metodo que expone servicio REST usando POST que modifica el miembro que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/staff/actmiembro
	 * 
	 * @param staff
	 *            - miembro a actualizar
	 * @return Json con el miembro que actualizo o Json con el error que se
	 *         produjo
	 */
	@DELETE
	@Path("/deletemiembro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMiembro(Staff staff) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try {
			tm.deleteStaff(staff);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(staff).build();
	}
}
