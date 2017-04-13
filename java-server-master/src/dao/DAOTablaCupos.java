package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Boleta;
import vos.Funcion;

public class DAOTablaCupos {
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
	public DAOTablaCupos() {
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
	public void addCupo(Funcion funcion, int cupo) throws SQLException {
		String insertIntoCUPOS = "INSERT INTO ISIS2304B031710.CUPOS VALUES (?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoCUPOS);
		prepStmt.setInt(1, funcion.getId());
		prepStmt.setInt(2, funcion.getIdEspectaculo());
		prepStmt.setInt(3, cupo);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void updateCupoVentaBoleta(Boleta boleta) throws SQLException {
		String updateCUPOS = "UPDATE ISIS2304B031710.CUPOS SET CUPO = ? WHERE (ID_ESPECTACULO = ?) AND (ID_FUNCION = ?)";
		PreparedStatement prepStmt = conn.prepareStatement(updateCUPOS);
		int nuevoCupo = getCupoActual(boleta.getIdEspectaculo(), boleta.getIdFuncion()) - 1;
		prepStmt.setInt(1, nuevoCupo);
		prepStmt.setInt(2, boleta.getIdEspectaculo());
		prepStmt.setInt(3, boleta.getIdFuncion());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public void updateCupoRegresoBoleta(Boleta boleta) throws SQLException {
		String updateCUPOS = "UPDATE ISIS2304B031710.CUPOS SET CUPO = ? WHERE (ID_ESPECTACULO = ?) AND (ID_FUNCION = ?)";
		PreparedStatement prepStmt = conn.prepareStatement(updateCUPOS);
		int nuevoCupo = getCupoActual(boleta.getIdEspectaculo(), boleta.getIdFuncion()) + 1;
		prepStmt.setInt(1, nuevoCupo);
		prepStmt.setInt(2, boleta.getIdEspectaculo());
		prepStmt.setInt(3, boleta.getIdFuncion());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public int getCupoActual(int idEspectaculo, int idFuncion) throws SQLException {
		int cupo = 0;
		
		String query = "SELECT * FROM ISIS2304B031710.CUPOS WHERE (ID_ESPECTACULO = ?) AND (ID_FUNCION = ?)";
	
		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, idEspectaculo);
		prepStmt.setInt(2, idFuncion);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
	
		if (rs.next()) {
			cupo = Integer.parseInt(rs.getString("CUPO"));
		}
	
		return cupo;
	}
}
