package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Genero;
import vos.Localidad;

public class DAOTablaGeneros {
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
	public DAOTablaGeneros() {
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
	public void addLocalidad(Localidad localidad) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoLOCALIDAD = "INSERT INTO ISIS2304B031710.LOCALIDADES VALUES (?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoLOCALIDAD);
		prepStmt.setInt(1, localidad.getId());
		prepStmt.setString(2, localidad.getLocalidad());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Genero searchByGeneroId(int id) throws SQLException, Exception {
		Genero genero = null;

		String query = "SELECT * FROM ISIS2304B031710.GENEROS WHERE ID = ?";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, id);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int idQ = Integer.parseInt(rs.getString("ID"));
			String tipo = rs.getString("TIPO");

			genero = new Genero(idQ, tipo);
		}

		return genero;
	}
	
	public boolean existeGenero(int id) throws SQLException, Exception {
		return searchByGeneroId(id) != null;
	}
}
