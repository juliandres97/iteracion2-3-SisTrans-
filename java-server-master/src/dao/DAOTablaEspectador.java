package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public Cliente darClienteMasEspectaculo() throws Exception {
		String query = "SELECT CLIENTE FROM ESPECTADORES WHERE CLIENTE = (SELECT MAX(COUNT(CLIENTE)) FROM ESPECTADORES GROUP BY CLIENTE) GROUP BY CLIENTE";
		PreparedStatement prepStmt = conn.prepareStatement(query);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		DAOTablaClientes clientes = new DAOTablaClientes();
		
		if(rs.next()) {
			return clientes.searchClienteById(rs.getInt("CLIENTE"));
		}
		else{
			return null;
		}
	}
}
