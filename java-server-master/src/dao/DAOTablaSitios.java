package dao;

import java.sql.*;
import java.util.*;

import vos.Localidad;
import vos.Sitio;
import vos.Especificacion;

public class DAOTablaSitios {

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor <b>post: </b> Crea la instancia del DAO e inicializa
	 * el Arraylist de recursos
	 */
	public DAOTablaSitios() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * Metodo que cierra todos los recursos que estan en el arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for (Object ob : recursos) {
			if (ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la
	 * conexion que entra como parametro.
	 * 
	 * @param con
	 *            - connection a la base de datos
	 */
	public void setConn(Connection con) {
		this.conn = con;
	}

	/**
	 * Metodo que agrega el usuario que entra como parametro a la base de datos.
	 * 
	 * @param sitio
	 *            - el sitio a agregar. sitio != null <b> post: </b> se ha
	 *            agregado el sitio a la base de datos en la transaction actual.
	 *            pendiente que el sitio master haga commit para que el sitio
	 *            baje a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             agregar el sitio a la base de datos
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void addSitio(Sitio sitio) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoSITIOS = "INSERT INTO ISIS2304B031710.SITIOS VALUES (?,?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSITIOS);
		prepStmt.setInt(1, sitio.getId());
		prepStmt.setString(2, sitio.getNombre());
		prepStmt.setString(3, sitio.getTipoSitio());
		prepStmt.setInt(4, sitio.getAforo());
		prepStmt.setString(5, sitio.getSilleteria());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Método que busca el/los sitioss con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los sitioss a buscar
	 * @return ArrayList con los sitioss encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Sitio searchSitioByNombre(String nombre) throws SQLException, Exception {
		Sitio sitio = null;

		String query = "SELECT * FROM ISIS2304B031710.SITIOS WHERE NOMBRE = ?";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setString(1, nombre);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int idQ = Integer.parseInt(rs.getString("ID"));
			String nombreQ = rs.getString("NOMBRE");
			String tipoSitio = rs.getString("TIPO");
			int aforo = Integer.parseInt(rs.getString("AFORO"));
			String silleteria = rs.getString("SILLETERIA");

			sitio = new Sitio(idQ, nombreQ, tipoSitio, aforo, null, null, silleteria);
		}

		if (sitio != null && !searchSpecsSitio(sitio.getId()).isEmpty()) {
			sitio.setEspecificaciones(searchSpecsSitio(sitio.getId()));
		}

		if (sitio != null && !searchLocalidadesSitio(sitio.getId()).isEmpty()) {
			sitio.setLocalidades(searchLocalidadesSitio(sitio.getId()));
		}

		return sitio;
	}

	public int getAforoSitio(int idSitio) throws SQLException {
		int aforo = 0;
	
		String query = "SELECT S.AFORO "
				+ "FROM ISIS2304B031710.SITIOS S "
				+ "WHERE S.ID = ?";
	
		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, idSitio);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
	
		if (rs.next()) {
			aforo = Integer.parseInt(rs.getString("AFORO"));
		}
	
		return aforo;
	}

	/**
	 * Metodo que elimina el miembro que entra como parametro en la base de
	 * datos.
	 * 
	 * @param miembro
	 *            - el miembro a borrar. miembro != null <b> post: </b> se ha
	 *            borrado el miembro en la base de datos en la transaction
	 *            actual. pendiente que el miembro master haga commit para que
	 *            los cambios bajen a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             actualizar el miembro.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteSitio(Sitio sitio) throws SQLException, Exception {
		String deleteSTAFF = "DELETE ISIS2304B031710.SITIOS WHERE ID_MIEMBRO = ?";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		presStmt.setInt(1, sitio.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}

	private List<Especificacion> searchSpecsSitio(int idSitio) throws SQLException {
		List<Especificacion> specs = new ArrayList<Especificacion>();

		String query = "SELECT S.ID, S.SPEC "
				+ "FROM ISIS2304B031710.SPECS S "
				+ "WHERE S.IDSITIO = ?";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, idSitio);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String specQ = rs.getString("SPEC");

			Especificacion spec = new Especificacion(id, specQ);
			specs.add(spec);
		}

		return specs;
	}

	private List<Localidad> searchLocalidadesSitio(int idSitio) throws SQLException {
		List<Localidad> localidades = new ArrayList<Localidad>();

		String query = "SELECT L.ID, L.NOMBRE "
				+ "FROM ISIS2304B031710.LOCALIDADES L "
				+ "WHERE L.SITIO = ?";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, idSitio);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String localidadQ = rs.getString("NOMBRE");

			Localidad localidad = new Localidad(id, localidadQ);
			localidades.add(localidad);
		}

		return localidades;
	}
}
