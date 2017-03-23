package dao;

import java.sql.*;
import java.util.*;

import vos.Sitio;

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
	 * @param sitios
	 *            - el sitios a agregar. sitios != null <b> post: </b> se ha
	 *            agregado el sitios a la base de datos en la transaction actual.
	 *            pendiente que el sitios master haga commit para que el sitios
	 *            baje a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             agregar el sitios a la base de datos
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void addSitio(Sitio sitios) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoSTAFF = "INSERT INTO ISIS2304B031710.SITIOS VALUES (?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSTAFF);
		prepStmt.setInt(1, sitios.getId());
		prepStmt.setString(2, sitios.getTipo());
		prepStmt.setString(3, sitios.getTipo());
		prepStmt.setInt(4, sitios.getAforo());
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
	public Sitio searchAdminByDocIdPassword(int id) throws SQLException, Exception {
		Sitio sitios = null;

		String query = "SELECT * FROM ISIS2304B031710.SITIOS "
				+ "WHERE ID = ?";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, id);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int idQ = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBREO");
			String tipo = rs.getString("TIPO");
			int aforo = Integer.parseInt(rs.getString("PASSWORD_MIEMBRO"));
			
			sitios = new Sitio(id, tipo, aforo, null, null);
		}
		
		

		return sitios;
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
	public void deleteSitio(Sitio sitios) throws SQLException, Exception {
		String deleteSTAFF = "DELETE ISIS2304B031710.SITIOS WHERE ID_MIEMBRO = ?";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		presStmt.setInt(1, sitios.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}
}
