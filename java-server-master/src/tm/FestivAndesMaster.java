package tm;

import java.io.*;
import java.sql.*;
import java.util.*;

import dao.DAOTablaClientes;
import dao.DAOTablaCompanias;
import dao.DAOTablaStaff;
import vos.Cliente;
import vos.ClienteList;
import vos.Compania;
import vos.CompaniaList;
import vos.Staff;
import vos.StaffList;

public class FestivAndesMaster {

	/**
	 * Atributo estatico que contiene el path relativo del archivo que tiene los
	 * datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los
	 * datos de la conexion
	 */
	private String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de
	 * datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base
	 * de datos.
	 */
	private String driver;

	/**
	 * Conexion a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor de la clase FestivAndesMaster, esta clase modela y
	 * contiene cada una de las transacciones y la logia de negocios que estas
	 * conllevan. <b>post:</b> Se crea el objeto FestivAndesMaster, se
	 * inicializa el path absoluto de el archivo de conexion y se inicializa los
	 * atributos que se usan par la conexion a la base de datos.
	 * 
	 * @param contextPathP
	 *            - path absoluto en el servidor del contexto del deploy actual
	 */
	public FestivAndesMaster(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/**
	 * Metodo que inicializa los atributos que se usan para la conexion a la
	 * base de datos. <b>post: </b> Se han inicializado los atributos que se
	 * usan par la conexion a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que retorna la conexion a la base de datos
	 * 
	 * @return Connection - la conexion a la base de datos
	 * @throws SQLException
	 *             - Cualquier error que se genere durante la conexion a la base
	 *             de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * Metodo que modela la transaccion que agrega un miembro del Staff a la
	 * base de datos. <b> post: </b> se ha agregado el miebro que entra como
	 * parametro
	 * 
	 * @param staff
	 *            - el miembro a agregar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera agregando el miebro.
	 */
	public void addStaff(Staff staff) throws Exception {
		DAOTablaStaff daoStaff = new DAOTablaStaff();

		try {
			// Transaccion
			this.conn = darConexion();
			daoStaff.setConn(conn);
			daoStaff.addStaff(staff);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoStaff.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addMiembrosStaff(StaffList staffList) throws Exception {
		// TODO Auto-generated method stub
		DAOTablaStaff daoStaff = new DAOTablaStaff();
		try 
		{
			// Transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoStaff.setConn(conn);
			for(Staff staff : staffList.getStaffList())
				daoStaff.addStaff(staff);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoStaff.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que modifica un miembro del Staff a la
	 * base de datos. <b> post: </b> se ha modificado el miebro que entra como
	 * parametro
	 * 
	 * @param staff
	 *            - el miembro a modificar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera modificando el miebro.
	 */
	public void updateStaff(Staff staff) throws Exception {
		DAOTablaStaff daoStaff = new DAOTablaStaff();
		try {
			// Transaccion
			this.conn = darConexion();
			daoStaff.setConn(conn);
			daoStaff.updateStaff(staff);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoStaff.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Método que modela la transacción que busca el/los videos en la base de
	 * datos con el nombre entra como parámetro.
	 * 
	 * @param name
	 *            - Nombre del video a buscar. name != null
	 * @return ListaVideos - objeto que modela un arreglo de videos. este
	 *         arreglo contiene el resultado de la búsqueda
	 * @throws Exception
	 *             - cualquier error que se genere durante la transacción
	 */
	public Staff searchAdminByDocIdPassword(String docId, String password) throws Exception {
		Staff staff = null;
		DAOTablaStaff daoStaff = new DAOTablaStaff();

		try {
			// Transaccion
			this.conn = darConexion();
			daoStaff.setConn(conn);
			staff = daoStaff.searchAdminByDocIdPassword(docId, password);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoStaff.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return staff;
	}

	/**
	 * Metodo que modela la transaccion que elimina un miembro del Staff a la
	 * base de datos. <b> post: </b> se ha eliminado el miebro que entra como
	 * parametro
	 * 
	 * @param staff
	 *            - el miembro a eliminar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera eliminando el miebro.
	 */
	public void deleteStaff(Staff staff) throws Exception {
		DAOTablaStaff daoStaff = new DAOTablaStaff();
		try {
			// Transaccion
			this.conn = darConexion();
			daoStaff.setConn(conn);
			daoStaff.deleteStaff(staff);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoStaff.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que agrega un miembro del Cliente a la
	 * base de datos. <b> post: </b> se ha agregado el miebro que entra como
	 * parametro
	 * 
	 * @param cliente
	 *            - el miembro a agregar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera agregando el miebro.
	 */
	public void addCliente(Cliente cliente) throws Exception {
		DAOTablaClientes daoCliente = new DAOTablaClientes();

		try {
			// Transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.addCliente(cliente);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addClientes(ClienteList clienteList) throws Exception {
		// TODO Auto-generated method stub
		DAOTablaClientes daoCliente = new DAOTablaClientes();
		try 
		{
			// Transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoCliente.setConn(conn);
			for(Cliente cliente : clienteList.getClientes())
				daoCliente.addCliente(cliente);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que modifica un miembro del Cliente a la
	 * base de datos. <b> post: </b> se ha modificado el miebro que entra como
	 * parametro
	 * 
	 * @param cliente
	 *            - el miembro a modificar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera modificando el miebro.
	 */
	public void updateCliente(Cliente cliente) throws Exception {
		DAOTablaClientes daoCliente = new DAOTablaClientes();
		try {
			// Transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.updateCliente(cliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Método que modela la transacción que busca el/los videos en la base de
	 * datos con el nombre entra como parámetro.
	 * 
	 * @param name
	 *            - Nombre del video a buscar. name != null
	 * @return ListaVideos - objeto que modela un arreglo de videos. este
	 *         arreglo contiene el resultado de la búsqueda
	 * @throws Exception
	 *             - cualquier error que se genere durante la transacción
	 */
	public Cliente searchClienteByDocIdPassword(String docId, String password) throws Exception {
		Cliente cliente = null;
		DAOTablaClientes daoCliente = new DAOTablaClientes();

		try {
			// Transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			cliente = daoCliente.searchClienteByDocIdPassword(docId, password);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return cliente;
	}

	/**
	 * Metodo que modela la transaccion que elimina un miembro del Cliente a la
	 * base de datos. <b> post: </b> se ha eliminado el miebro que entra como
	 * parametro
	 * 
	 * @param cliente
	 *            - el miembro a eliminar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera eliminando el miebro.
	 */
	public void deleteCliente(Cliente cliente) throws Exception {
		DAOTablaClientes daoCliente = new DAOTablaClientes();
		try {
			// Transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.deleteCliente(cliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que agrega un miembro del Compania a la
	 * base de datos. <b> post: </b> se ha agregado el miebro que entra como
	 * parametro
	 * 
	 * @param compania
	 *            - el miembro a agregar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera agregando el miebro.
	 */
	public void addCompania(Compania compania) throws Exception {
		DAOTablaCompanias daoCompania = new DAOTablaCompanias();

		try {
			// Transaccion
			this.conn = darConexion();
			daoCompania.setConn(conn);
			daoCompania.addCompania(compania);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCompania.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addCompanias(CompaniaList companiaList) throws Exception {
		// TODO Auto-generated method stub
		DAOTablaCompanias daoCompania = new DAOTablaCompanias();
		try 
		{
			// Transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoCompania.setConn(conn);
			for(Compania compania : companiaList.getCompanias())
				daoCompania.addCompania(compania);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoCompania.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que modifica un miembro del Compania a la
	 * base de datos. <b> post: </b> se ha modificado el miebro que entra como
	 * parametro
	 * 
	 * @param compania
	 *            - el miembro a modificar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera modificando el miebro.
	 */
	public void updateCompania(Compania compania) throws Exception {
		DAOTablaCompanias daoCompania = new DAOTablaCompanias();
		try {
			// Transaccion
			this.conn = darConexion();
			daoCompania.setConn(conn);
			daoCompania.updateCompania(compania);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCompania.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Método que modela la transacción que busca el/los videos en la base de
	 * datos con el nombre entra como parámetro.
	 * 
	 * @param name
	 *            - Nombre del video a buscar. name != null
	 * @return ListaVideos - objeto que modela un arreglo de videos. este
	 *         arreglo contiene el resultado de la búsqueda
	 * @throws Exception
	 *             - cualquier error que se genere durante la transacción
	 */
	public Compania searchCompaniaByDocIdPassword(String Nit, String password) throws Exception {
		Compania compania = null;
		DAOTablaCompanias daoCompania = new DAOTablaCompanias();

		try {
			// Transaccion
			this.conn = darConexion();
			daoCompania.setConn(conn);
			compania = daoCompania.searchCompaniaByNitPassword(Nit, password);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCompania.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return compania;
	}

	/**
	 * Metodo que modela la transaccion que elimina un miembro del Compania a la
	 * base de datos. <b> post: </b> se ha eliminado el miebro que entra como
	 * parametro
	 * 
	 * @param compania
	 *            - el miembro a eliminar. miembro != null
	 * @throws Exception
	 *             - cualquier error que se genera eliminando el miebro.
	 */
	public void deleteCompania(Compania compania) throws Exception {
		DAOTablaCompanias daoCompania = new DAOTablaCompanias();
		try {
			// Transaccion
			this.conn = darConexion();
			daoCompania.setConn(conn);
			daoCompania.deleteCompania(compania);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCompania.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
}
