package dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import vos.Funcion;

/**
 * @author ja.bermudez10
 */
public class DAOTablaFunciones {
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
	public DAOTablaFunciones() {
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
	 * Metodo que agrega el funcion que entra como parametro a la base de datos.
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
	public void addFuncion(Funcion funcion) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoSTAFF = "INSERT INTO ISIS2304B031710.FUNCIONES VALUES (?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSTAFF);
		prepStmt.setInt(1, funcion.getId());
		prepStmt.setInt(2, funcion.getIdEspectaculo());
		prepStmt.setDate(3, funcion.getFechaRealizacion());
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
	public Funcion searchAdminByDocIdPassword(String docId, String password) throws SQLException, Exception {
		Funcion funcion = null;

		String query = "SELECT * FROM ISIS2304B031710.STAFF"
				+ "WHERE (DOC_ID_MIEMBRO = ?) AND (PASSWORD_MIEMBRO = ?) AND (ROL_MIEMBRO = 'administrador')";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setString(1, docId);
		prepStmt.setString(2, password);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			int idEspectaculo = Integer.parseInt(rs.getString("ID_ESPECTACULO"));
			Date fechaRealizacion = rs.getDate("DIA_REALIZACION");
			
			funcion = new Funcion(id, idEspectaculo, null, fechaRealizacion);
		}

		return funcion;
	}

	/**
	 * Metodo que modifica el funcion que entra como parametro a la base de
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
//	public void updateFuncion(Funcion funcion) throws SQLException {
//		// TODO Auto-generated method stub
//		String updateSTAFF = "UPDATE ISIS2304B031710.STAFF " + "SET ID_ESPECTACULO = ?, " + "NOMBRE_MIEMBRO = ?,  "
//				+ "PASSWORD_MIEMBRO = ?, " + "EMAIL_MIEMBRO = ?, " + "ROL_MIEMBRO = ? " + "WHERE ID = ?";
//		PreparedStatement prepStmt = conn.prepareStatement(updateSTAFF);
//		prepStmt.setString(1, funcion.getDocId());
//		prepStmt.setString(2, funcion.getNombre());
//		prepStmt.setString(3, funcion.getPassword());
//		prepStmt.setString(4, funcion.getEmail());
//		prepStmt.setString(5, funcion.getRol());
//		prepStmt.setInt(6, funcion.getId());
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//	}

	/**
	 * Metodo que elimina el funcion que entra como parametro en la base de
	 * datos.
	 * 
	 * @param funcion
	 *            - el funcion a borrar. funcion != null <b> post: </b> se ha
	 *            borrado el funcion en la base de datos en la transaction
	 *            actual. pendiente que el funcion master haga commit para que
	 *            los cambios bajen a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             actualizar el funcion.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteFuncion(Funcion funcion) throws SQLException, Exception {
		String deleteSTAFF = "DELETE ISIS2304B031710.FUNCIONES WHERE ID_MIEMBRO = ?";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		presStmt.setInt(1, funcion.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}
}
