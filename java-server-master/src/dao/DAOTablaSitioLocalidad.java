package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTablaSitioLocalidad {
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
	public DAOTablaSitioLocalidad() {
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
	public void addLocalidadToSitio(int idSitio, int idLocalidad) throws SQLException, Exception
	{
		// TODO Auto-generated method stub
		String insertIntoSITIO_LOCALIDAD = "INSERT INTO ISIS2304B031710.SITIO_LOCALIDAD VALUES (?,?)";
		PreparedStatement innerPrepStmt = conn.prepareStatement(insertIntoSITIO_LOCALIDAD);
		innerPrepStmt.setInt(1, idSitio);
		innerPrepStmt.setInt(2, idLocalidad);
		recursos.add(innerPrepStmt);
		innerPrepStmt.executeQuery();
	}
}
