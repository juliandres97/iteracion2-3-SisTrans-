package dao;

import java.sql.*;
import java.util.*;

import vos.Compania;
import vos.Espectaculo;

public class DAOTablaEspectaculos {

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
	public DAOTablaEspectaculos() {
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
	 * @param video
	 *            - el video a agregar. video != null <b> post: </b> se ha
	 *            agregado el video a la base de datos en la transaction actual.
	 *            pendiente que el video master haga commit para que el video
	 *            baje a la base de datos.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void addEspectaculo(Espectaculo espectaculo) throws SQLException, Exception {
		// TODO Auto-generated method stub
		String insertIntoESPECTACULO = "INSERT INTO ISIS2304B031710.ESPECTACULOS VALUES (?,?,?,?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoESPECTACULO);
		prepStmt.setInt(1, espectaculo.getId());
		prepStmt.setString(2, espectaculo.getNombre());
		prepStmt.setInt(3, espectaculo.getDuracion());
		prepStmt.setString(4, espectaculo.getIdioma());
		prepStmt.setString(5, espectaculo.getTraductor());
		prepStmt.setString(6, espectaculo.getDescripcion());
		prepStmt.setString(7, espectaculo.getPublicoObj());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que modifica el usuario que entra como parametro a la base de
	 * datos.
	 * 
	 * @param video
	 *            - el video a modificar. video != null <b> post: </b> se ha
	 *            agregado el video a la base de datos en la transaction actual.
	 *            pendiente que el video master haga commit para que el video
	 *            baje a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             agregar el video a la base de datos
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void updateEspectaculo(Espectaculo espectaculo) throws SQLException {
		// TODO Auto-generated method stub
		String updateSTAFF = "UPDATE ISIS2304B031710.ESPECTACULOS " + "SET NOMBRE = ?, " + "DURACION = ?, "
				+ "IDIOMA = ?, " + "COSTO = ?, " + "TRADUCTOR = ? " + "DESCRIPCION = ?" + "PUBLICOOBJ = ? "
				+ "WHERE ID = ?";
		PreparedStatement prepStmt = conn.prepareStatement(updateSTAFF);
		// prepStmt.setString(1, espectaculo.getNombre());
		// prepStmt.setInt(2, espectaculo.getDuracion());
		// prepStmt.setString(3, espectaculo.getIdioma());
		// prepStmt.setInt(4, espectaculo.getCosto());
		// prepStmt.setString(5, espectaculo.getTraductor());
		// prepStmt.setString(6, espectaculo.getDescripcion());
		// prepStmt.setString(7, espectaculo.getPublicoObj());
		// prepStmt.setInt(8, espectaculo.getId());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
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
	public void deleteEspectaculo(Espectaculo espectaculo) throws SQLException, Exception {
		String deleteSTAFF = "DELETE ISIS2304B031710.ESPECTACULOS WHERE ID = ?";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		// presStmt.setInt(1, espectaculo.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}

	public Espectaculo searchById(int id) throws SQLException, Exception {
		Espectaculo espectaculo = null;

		String query = "SELECT * FROM ISIS2304B031710.ESPECTACULOS WHERE ID = ?";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, id);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		DAOTablaCompanias daoCompanias = new DAOTablaCompanias();
		List<Compania> companias = daoCompanias.searchCompaniasDeEspectaculo(id);

		if (rs.next()) {
			int idQ = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			int duracion = Integer.parseInt(rs.getString("DURACION"));
			String idioma = rs.getString("IDIOMA");
			String traductor = rs.getString("TRADUCTOR");
			String descripcion = rs.getString("DESCRIPCION");
			String publicoObj = rs.getString("PUBLICOOBJ");

			espectaculo = new Espectaculo(idQ, nombre, duracion, idioma, traductor, descripcion, publicoObj, companias);
		}

		return espectaculo;
	}
	
	public List<Espectaculo> searchEspectaculosDeCompania(int idCompania) throws SQLException {
		List<Espectaculo> espectaculos = new ArrayList<Espectaculo>();

		String queryCompaniasDeEspectaculo = "SELECT E.ID, E.NOMBRE, E.DURACION,"
				+ " E.IDIOMA, E.COSTO, E.TRADUCTOR, E.DESCRIPCION, E.PUBLICOOBJ"
				+ " FROM ISIS2304B031710.COMPANIA_ESPECTACULO CE INNER JOIN ISIS2304B031710.ESPECTACULOS E"
				+ " ON CE.ID_ESPECTACULO = E.ID" + " WHERE CE.ID_COMPANIA = ?";
		PreparedStatement prepStmt = conn.prepareStatement(queryCompaniasDeEspectaculo);
		prepStmt.setInt(1, idCompania);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		DAOTablaCompanias daoCompanias = new DAOTablaCompanias();
		
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			int duracion = Integer.parseInt(rs.getString("DURACION"));
			String idioma = rs.getString("IDIOMA");
			String traductor = rs.getString("TRADUCTOR");
			String descripcion = rs.getString("DESCRIPCION");
			String publicoObj = rs.getString("PUBLICOOBJ");
			
			Espectaculo espectaculo = new Espectaculo(id, nombre, duracion, idioma, traductor, descripcion, publicoObj, daoCompanias.searchCompaniasDeEspectaculo(id));
		    espectaculos.add(espectaculo);
		}
		
		return espectaculos;
	}
}
