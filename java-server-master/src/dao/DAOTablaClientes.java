package dao;

import java.sql.*;
import java.util.*;

import vos.Cliente;

public class DAOTablaClientes {
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
	public DAOTablaClientes() {
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
	public void addCliente(Cliente cliente) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoSTAFF = "INSERT INTO ISIS2304B031710.CLIENTES VALUES (?,?,?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSTAFF);
		prepStmt.setInt(1, cliente.getId());
		prepStmt.setString(2, cliente.getDocId());
		prepStmt.setString(3, cliente.getNombre());
		prepStmt.setString(4, cliente.getPassword());
		prepStmt.setString(5, cliente.getEmail());
		prepStmt.setString(6, cliente.getAddress());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que busca el/los clientes con el documento y passwor que entra
	 * como parametro.
	 * 
	 * @param name
	 *            - Nombre de el/los videos a buscar
	 * @return ArrayList con los videos encontrados
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public Cliente searchClientByDocIdPassword(String docIdUser, String password) throws SQLException, Exception {
		Cliente cliente = null;

		String query = "SELECT * FROM ISIS2304B031710.CLIENTES WHERE (DOC_ID = ?) AND (PASSWORD = ?)";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setString(1, docIdUser);
		prepStmt.setString(2, password);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int id = Integer.parseInt(rs.getString("ID"));
			String docIdQ = rs.getString("DOC_ID");
			String nombre = rs.getString("NOMBRE");
			String passwordQ = rs.getString("PASSWORD");
			String email = rs.getString("EMAIL");
			String address = rs.getString("ADDRESS");

			cliente = new Cliente(id, docIdQ, nombre, passwordQ, email, address);
		}

		return cliente;
	}
	
	public Cliente searchClienteById(int id) throws SQLException, Exception {
		Cliente cliente = null;

		String query = "SELECT * FROM ISIS2304B031710.CLIENTES WHERE ID = ?";

		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setInt(1, id);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			int idQ = Integer.parseInt(rs.getString("ID"));
			String docIdQ = rs.getString("DOC_ID");
			String nombre = rs.getString("NOMBRE");
			String passwordQ = rs.getString("PASSWORD");
			String email = rs.getString("EMAIL");
			String address = rs.getString("ADDRESS");

			cliente = new Cliente(idQ, docIdQ, nombre, passwordQ, email, address);
		}

		return cliente;
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
	public void updateCliente(Cliente cliente) throws SQLException {
		// TODO Auto-generated method stub
		String updateSTAFF = "UPDATE ISIS2304B031710.CLIENTES "
				+ "SET DOC_ID = ?, "
				+ "NOMBRE = ?, "
				+ "PASSWORD = ?, "
				+ "EMAIL = ?, "
				+ "ROL = ? "
				+ "WHERE ID = ?";
		PreparedStatement prepStmt = conn.prepareStatement(updateSTAFF);
		prepStmt.setString(1, cliente.getDocId());
		prepStmt.setString(2, cliente.getNombre());
		prepStmt.setString(3, cliente.getPassword());
		prepStmt.setString(4, cliente.getEmail());
		prepStmt.setString(5, cliente.getAddress());
		prepStmt.setInt(6, cliente.getId());
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
	public void deleteCliente(Cliente cliente) throws SQLException, Exception {
		String deleteSTAFF = "DELETE ISIS2304B031710.CLIENTES WHERE ID = ?";
		PreparedStatement presStmt = conn.prepareStatement(deleteSTAFF);
		presStmt.setInt(1, cliente.getId());
		recursos.add(presStmt);
		presStmt.executeQuery();
	}
}
