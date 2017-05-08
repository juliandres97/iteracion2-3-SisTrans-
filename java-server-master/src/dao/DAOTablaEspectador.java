package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.Boleta;
import vos.Cliente;

public class DAOTablaEspectador {

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
	public DAOTablaEspectador() {
		// TODO Auto-generated constructor stub
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
	
//	public Cliente darClienteMasEspectaculo() throws Exception {
//		String query = "SELECT CLIENTE FROM ESPECTADORES WHERE CLIENTE = (SELECT MAX(COUNT(CLIENTE)) FROM ESPECTADORES GROUP BY CLIENTE) GROUP BY CLIENTE";
//		PreparedStatement prepStmt = conn.prepareStatement(query);
//		recursos.add(prepStmt);
//		ResultSet rs = prepStmt.executeQuery();
//		
//		DAOTablaClientes clientes = new DAOTablaClientes();
//		
//		if(rs.next()) {
//			return clientes.searchClienteById(rs.getInt("CLIENTE"));
//		}
//		else{
//			return null;
//		}
//	}
	
	public void addEspectador(Cliente cliente, Boleta boleta) throws SQLException {
		// TODO Auto-generated method stub
		String insertIntoSTAFF = "INSERT INTO ISIS2304B031710.ESPECTADORES VALUES (?,?,?,?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSTAFF);
		prepStmt.setInt(1, boleta.getIdFuncion());
		prepStmt.setInt(2, boleta.getIdEspectaculo());
		prepStmt.setInt(3, boleta.getIdSitio());
		prepStmt.setInt(4, cliente.getId());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public ArrayList<Integer> buscarEspectadoresByEspectaculo(int idEspectaculo) throws SQLException {
		ArrayList<Integer> idEspectadores = new ArrayList<Integer>();
		
		String insertIntoSTAFF = "SELECT ID_CLIENTE FROM ISIS2304B031710.ESPECTADORES WHERE (ID_ESPECTACULO = ?)";
		PreparedStatement prepStmt = conn.prepareStatement(insertIntoSTAFF);
		prepStmt.setInt(1, idEspectaculo);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next()) {
			int idCliente = rs.getInt("ID_CLIENTE");
			idEspectadores.add(idCliente);
		}
		
		return idEspectadores;
	}
}
