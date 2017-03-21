package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import vos.Compania;

public class DAOTablaCompanias {

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
	public DAOTablaCompanias() {
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
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             agregar el video a la base de datos
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void addCompania(Compania compania) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoSTAFF = "INSERT INTO ISIS2304B031710.COMPANIAS VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSTAFF);
		prepStmt.setInt(1, compania.getId());
		prepStmt.setString(2, compania.getNit());
		prepStmt.setString(3, compania.getNombre());
		prepStmt.setString(4, compania.getRepresentante());
		prepStmt.setString(5, compania.getPais());
		prepStmt.setString(6, compania.getWeb());
		prepStmt.setString(7, compania.getPassword());
		prepStmt.setDate(8, compania.getLlegada());
		prepStmt.setDate(9, compania.getSalida());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	/**
	 * Método que busca el/los videos con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Compania searchCompaniaByNitPassword(String nit, String password) throws SQLException, Exception {
		Compania compania = null;

		String query = "SELECT * FROM ISIS2304B031710.COMPANIAS WHERE (NIT_COMPANIA = ?) AND (PASSWORD_COMPANIA = ?)";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setString(1, nit);
		prepStmt.setString(2, password);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID_COMPANIA"));
			String nitQ = rs.getString("NIT_COMPANIA");
			String nombre = rs.getString("NOMBRE_COMPANIA");
			String representante = rs.getString("REPRESENTANTE_COMPANIA");
			String pais = rs.getString("PAIS_COMPANIA");
			String web = rs.getString("WEB_COMPANIA");
			String passwordQ = rs.getString("PASSWORD_COMPANIA");
			Date llegada = rs.getDate("LLEGADA_COMPANIA");
			Date salida = rs.getDate("SALIDA_COMPANIA");
			
			compania = new Compania(id, nitQ, nombre, representante, pais, web, passwordQ, llegada, salida);
		}

		return compania;
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
	public void updateCompania(Compania compania) throws SQLException {
		// TODO Auto-generated method stub
		String updateSTAFF = "UPDATE ISIS2304B031710.COMPANIAS "
				+ "SET NIT_COMPANIA = ?, "
				+ "NOMBRE_COMPANIA = ?, "
				+ "REPRESENTANTE_COMPANIA = ?, "
				+ "PAIS_COMPANIA = ?, "
				+ "WEB_COMPANIA = ?, "
				+ "PASSWORD_COMPANIA = ?, "
				+ "LLEGADA_COMPANIA = ?, "
				+ "SALIDA_COMPANIA = ? "
				+ "WHERE ID_COMPANIA = ?";
		PreparedStatement prepStmt = conn.prepareStatement(updateSTAFF);
		prepStmt.setString(1, compania.getNit());
		prepStmt.setString(2, compania.getNombre());
		prepStmt.setString(3, compania.getRepresentante());
		prepStmt.setString(4, compania.getPais());
		prepStmt.setString(5, compania.getWeb());
		prepStmt.setString(6, compania.getPassword());
		prepStmt.setString(7, ""/*TODO*/);
		prepStmt.setString(8, ""/*TODO*/);
		prepStmt.setInt(9, compania.getId());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que elimina el compania que entra como parametro en la base de
	 * datos.
	 * 
	 * @param compania
	 *            - el compania a borrar. compania != null <b> post: </b> se ha
	 *            borrado el compania en la base de datos en la transaction
	 *            actual. pendiente que el compania master haga commit para que
	 *            los cambios bajen a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             actualizar el compania.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteCompania(Compania compania) throws SQLException, Exception {
		String deleteSTAFF = "DELETE ISIS2304B031710.COMPANIAS WHERE ID_COMPANIA = ?";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		presStmt.setInt(1, compania.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}

}
