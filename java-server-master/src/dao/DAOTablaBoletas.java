package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Boleta;

public class DAOTablaBoletas {
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
	public DAOTablaBoletas() {
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
	public void addBoleta(Boleta boleta) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoSTAFF = "INSERT INTO ISIS2304B031710.BOLETAS VALUES (?,?,?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSTAFF);
		prepStmt.setInt(1, boleta.getId());
		prepStmt.setInt(2, boleta.getIdEspectaculo());
		prepStmt.setInt(3, boleta.getIdFuncion());
		prepStmt.setInt(4, boleta.getIdSitio());
		prepStmt.setInt(5, boleta.getValor());
		prepStmt.setString(6, "n");
		recursos.add(prepStmt);
		prepStmt.executeQuery();
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
	public void addBoletaAbono(Boleta boleta) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoSTAFF = "INSERT INTO ISIS2304B031710.BOLETAS VALUES (?,?,?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSTAFF);
		prepStmt.setInt(1, boleta.getId());
		prepStmt.setInt(2, boleta.getIdEspectaculo());
		prepStmt.setInt(3, boleta.getIdFuncion());
		prepStmt.setInt(4, boleta.getIdSitio());
		prepStmt.setInt(5, boleta.getValorConDescuento());
		prepStmt.setString(6, "s");
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	/**
	 * Metodo que elimina la boleta que entra como parametro en la base de
	 * datos.
	 * 
	 * @param boleta
	 *            -  boleta a borrar. boleta != null <b> post: </b> se ha
	 *            borrado la boleta en la base de datos en la transaction
	 *            actual. pendiente que el master haga commit para que
	 *            los cambios bajen a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             actualizar la boleta.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void regresarBoleta(Boleta boleta) throws SQLException, Exception {
		String deleteSTAFF = "DELETE FROM ISIS2304B031710.BOLETAS WHERE ID = ?";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		presStmt.setInt(1, boleta.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}
	
	/**
	 * Metodo que elimina la boleta que entra como parametro en la base de
	 * datos.
	 * 
	 * @param boleta
	 *            -  boleta a borrar. boleta != null <b> post: </b> se ha
	 *            borrado la boleta en la base de datos en la transaction
	 *            actual. pendiente que el master haga commit para que
	 *            los cambios bajen a la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje. No pudo
	 *             actualizar la boleta.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public void regresarBoletaAbono(Boleta boleta) throws SQLException, Exception {
		String deleteSTAFF = "DELETE FROM ISIS2304B031710.BOLETAS WHERE (ID = ?) AND (ABONO = 's')";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		presStmt.setInt(1, boleta.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}
	
}
