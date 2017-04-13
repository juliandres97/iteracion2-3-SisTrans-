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
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void addFuncion(Funcion funcion) throws Exception {
		// TODO Auto-generated method stub
		String insertIntoFUNCIONES = "INSERT INTO ISIS2304B031710.FUNCIONES VALUES (?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoFUNCIONES);
		
		prepStmt.setInt(1, funcion.getId());
		prepStmt.setInt(2, funcion.getIdEspectaculo());
		prepStmt.setInt(3, funcion.getIdSitio());
		prepStmt.setDate(4, funcion.getFechaRealizacion());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

//	private Espectaculo existeEspectaculo(int idEspectaculo) throws Exception {
//		DAOTablaEspectaculos daoEspectaculo = new DAOTablaEspectaculos();
//		Espectaculo espectaculo = null;
//
//		try {
//			espectaculo = daoEspectaculo.searchById(idEspectaculo);
//		} catch (SQLException e) {
//			System.err.println("SQLException:" + e.getMessage());
//			e.printStackTrace();
//			throw e;
//		} catch (Exception e) {
//			System.err.println("GeneralException:" + e.getMessage());
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {
//				daoEspectaculo.cerrarRecursos();
//				if (this.conn != null)
//					this.conn.close();
//			} catch (SQLException exception) {
//				System.err.println("SQLException closing resources:" + exception.getMessage());
//				exception.printStackTrace();
//				throw exception;
//			}
//		}
//
//		return espectaculo;
//	}

	/**
	 * Método que busca el/los videos con el nombre que entra como parámetro.
	 * 
	 * @param name
	 *            - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public Funcion searchFuncionByIdyIdEspectaculo(int id, int idEspectaculo) throws SQLException, Exception {
		Funcion funcion = null;

		String query = "SELECT * FROM ISIS2304B031710.FUNCIONES WHERE (ID = ? AND ID_ESPECTACUL0 = ?)";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, id);
		prepStmt.setInt(2, idEspectaculo);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int idQ = Integer.parseInt(rs.getString("ID"));
			int idEspectaculoQ = Integer.parseInt(rs.getString("ID_ESPECTACULO"));
			Date fechaRealizacion = rs.getDate("DIA_REALIZACION");
			int idSitio = Integer.parseInt("ID_SITIO");

			funcion = new Funcion(idQ, idEspectaculoQ, idSitio, fechaRealizacion);
		}

		return funcion;
	}

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
		String deleteSTAFF = "DELETE ISIS2304B031710.FUNCIONES WHERE ID = ?";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		presStmt.setInt(1, funcion.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}
}
