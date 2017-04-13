package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import tm.FestivAndesMaster;
import vos.*;

/**
 * @author Julián
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("admin")
public class FestivAndesAdminServices {

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
	@Path("/auth/staff={docIdUser}/psswrd={userPssWrd}")
	public Response auth(@PathParam("docIdUser") String docIdUser, @PathParam("userPssWrd") String userPssWrd) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Staff staff = null;
		try {
			staff = tm.searchAdminByDocIdPassword(docIdUser, userPssWrd);

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

	/**
	 * Metodo que expone servicio REST usando PUT que agrega el cliente que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/cliente/crearcliente
	 * 
	 * @param cliente
	 *            - cliente a agregar
	 * @return Json con el cliente que agrego o Json con el error que se produjo
	 */
	@POST
	@Path("/crearcliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearCliente(Cliente cliente) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.addCliente(cliente);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(cliente).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	/**
	 * Metodo que expone servicio REST usando PUT que agrega la lista de
	 * clientes que recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/staff/crearclientes
	 * 
	 * @param clientes
	 *            - clientes a agregar
	 * @return Json con los clientes que agrego o Json con el error que se
	 *         produjo
	 */
	@POST
	@Path("/crearclientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearclientes(ClienteList clientes) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		
		if (staffSessionStarted) {			
			try {
				tm.addClientes(clientes);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(clientes).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}


	/**
	 * Metodo que expone servicio REST usando POST que modifica el cliente que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/cliente/actcliente
	 * 
	 * @param cliente
	 *            - cliente a actualizar
	 * @return Json con el cliente que actualizo o Json con el error que se
	 *         produjo
	 */
	@PUT
	@Path("/actcliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCliente(Cliente cliente) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.updateCliente(cliente);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(cliente).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}

	/**
	 * Metodo que expone servicio REST usando POST que modifica el cliente que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/cliente/actcliente
	 * 
	 * @param cliente
	 *            - cliente a actualizar
	 * @return Json con el cliente que actualizo o Json con el error que se
	 *         produjo
	 */
	@DELETE
	@Path("/deletecliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(Cliente cliente) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.deleteCliente(cliente);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(cliente).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	/**
	 * Metodo que expone servicio REST usando PUT que agrega el compania que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/compania/crearcompania
	 * 
	 * @param compania
	 *            - compania a agregar
	 * @return Json con el compania que agrego o Json con el error que se produjo
	 */
	@POST
	@Path("/crearcompania")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearCompania(Compania compania) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.addCompania(compania);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(compania).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	/**
	 * Metodo que expone servicio REST usando PUT que agrega la lista de
	 * companias que recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/staff/crearcompanias
	 * 
	 * @param companias
	 *            - companias a agregar
	 * @return Json con los companias que agrego o Json con el error que se
	 *         produjo
	 */
	@POST
	@Path("/crearcompanias")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearCompanias(CompaniaList companias) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		
		if (staffSessionStarted) {			
			try {
				tm.addCompanias(companias);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(companias).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}


	/**
	 * Metodo que expone servicio REST usando POST que modifica el compania que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/compania/actcompania
	 * 
	 * @param compania
	 *            - compania a actualizar
	 * @return Json con el compania que actualizo o Json con el error que se
	 *         produjo
	 */
	@PUT
	@Path("/actcompania")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCompania(Compania compania) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.updateCompania(compania);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(compania).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	@POST
	@Path("/crearespectaculo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearEspectaculo(Espectaculo espectaculo) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
	
		if (staffSessionStarted) {
			try {
				tm.addEspectaculo(espectaculo);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(espectaculo).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}

	@PUT
	@Path("/actespectaculo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEspectaculo(Espectaculo espectaculo) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.updateEspectaculo(espectaculo);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(espectaculo).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	/**
	 * Metodo que expone servicio REST usando POST que modifica el compania que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/compania/actcompania
	 * 
	 * @param compania
	 *            - compania a actualizar
	 * @return Json con el compania que actualizo o Json con el error que se
	 *         produjo
	 */
	@DELETE
	@Path("/deletecompania")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCompania(Compania compania) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.deleteCompania(compania);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(compania).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	/**
	 * Metodo que expone servicio REST usando PUT que agrega el sitio que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/sitio/crearsitio
	 * 
	 * @param sitio
	 *            - sitio a agregar
	 * @return Json con el sitio que agrego o Json con el error que se produjo
	 */
	@POST
	@Path("/crearsitio")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearSitio(Sitio sitio) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.addSitio(sitio);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(sitio).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
	/**
	 * Metodo que expone servicio REST usando PUT que agrega el funcion que
	 * recibe en Json <b>URL: </b>
	 * http://(IP):(Puerto)/FestivAndes/rest/funcion/crearfuncion
	 * 
	 * @param funcion
	 *            - funcion a agregar
	 * @return Json con el funcion que agrego o Json con el error que se produjo
	 */
	@POST
	@Path("/crearfuncion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearFuncion(Funcion funcion) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());

		if (staffSessionStarted) {
			try {
				tm.addFuncion(funcion);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(funcion).build();
		} else {
			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
		}
	}
	
//	@GET
//	@Path("/clientemasespecs")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getClienteMasEspectaculos() {
//		FestivAndesMaster tm = new FestivAndesMaster(getPath());
//		Cliente c = null;
//
//		if (staffSessionStarted) {
//			try {
//				c = tm.getClienteMasEspectaculos();
//				return Response.status(200).entity(c).build();
//			} catch (Exception e) {
//				return Response.status(500).entity(doErrorMessage(e)).build();
//			}
//		} else {
//			return Response.status(401).entity(doJSONMessage("UNAUTHORIZED", "Sin autorizacion.")).build();
//		}
//	}
}