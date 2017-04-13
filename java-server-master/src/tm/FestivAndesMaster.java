package tm;

import java.io.*;
import java.sql.*;
import java.util.*;

import dao.*;
import vos.*;

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
	 * M茅todo que modela la transacci贸n que busca el/los videos en la base de
	 * datos con el nombre entra como par谩metro.
	 * 
	 * @param name
	 *            - Nombre del video a buscar. name != null
	 * @return ListaVideos - objeto que modela un arreglo de videos. este
	 *         arreglo contiene el resultado de la b煤squeda
	 * @throws Exception
	 *             - cualquier error que se genere durante la transacci贸n
	 */
	public Staff searchAdminByDocIdPassword(String docIdUser, String password) throws Exception {
		Staff staff = null;
		DAOTablaStaff daoStaff = new DAOTablaStaff();

		try {
			// Transaccion
			this.conn = darConexion();
			daoStaff.setConn(conn);
			staff = daoStaff.searchAdminByDocIdPassword(docIdUser, password);

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
	 * M茅todo que modela la transacci贸n que busca el/los videos en la base de
	 * datos con el nombre entra como par谩metro.
	 * 
	 * @param name
	 *            - Nombre del video a buscar. name != null
	 * @return ListaVideos - objeto que modela un arreglo de videos. este
	 *         arreglo contiene el resultado de la b煤squeda
	 * @throws Exception
	 *             - cualquier error que se genere durante la transacci贸n
	 */
	public Staff searchOperarioByDocIdPassword(String docIdUser, String password) throws Exception {
		Staff staff = null;
		DAOTablaStaff daoStaff = new DAOTablaStaff();

		try {
			// Transaccion
			this.conn = darConexion();
			daoStaff.setConn(conn);
			staff = daoStaff.searchOperarioByDocIdPassword(docIdUser, password);

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
	 * M茅todo que modela la transacci贸n que busca el/los videos en la base de
	 * datos con el nombre entra como par谩metro.
	 * 
	 * @param name
	 *            - Nombre del video a buscar. name != null
	 * @return ListaVideos - objeto que modela un arreglo de videos. este
	 *         arreglo contiene el resultado de la b煤squeda
	 * @throws Exception
	 *             - cualquier error que se genere durante la transacci贸n
	 */
	public Cliente searchClientByDocIdPassword(String docId, String password) throws Exception {
		Cliente cliente = null;
		DAOTablaClientes daoCliente = new DAOTablaClientes();

		try {
			// Transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			cliente = daoCliente.searchClientByDocIdPassword(docId, password);

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
	 * M茅todo que modela la transacci贸n que busca el/los videos en la base de
	 * datos con el nombre entra como par谩metro.
	 * 
	 * @param name
	 *            - Nombre del video a buscar. name != null
	 * @return ListaVideos - objeto que modela un arreglo de videos. este
	 *         arreglo contiene el resultado de la b煤squeda
	 * @throws Exception
	 *             - cualquier error que se genere durante la transacci贸n
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

	/**
	 * Metodo que modela la transaccion que agrega un espectaculo del Espectaculo a la
	 * base de datos. <b> post: </b> se ha agregado el miebro que entra como
	 * parametro
	 * 
	 * @param espectaculo
	 *            - el espectaculo a agregar. espectaculo != null
	 * @throws Exception
	 *             - cualquier error que se genera agregando el miebro.
	 */
	public void addEspectaculo(Espectaculo espectaculo) throws Exception {
		DAOTablaEspectaculos daoEspectaculo = new DAOTablaEspectaculos();
		DAOTablaCompaniaEspectaculo daoCompaniaEspectaculo = new DAOTablaCompaniaEspectaculo();
		DAOTablaCompanias daoTablaCompanias = new DAOTablaCompanias();

		try {
			// Transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoEspectaculo.setConn(conn);
			daoCompaniaEspectaculo.setConn(conn);
			daoTablaCompanias.setConn(conn);

			// Subtransaccion 1
			daoEspectaculo.addEspectaculo(espectaculo);

			// Chequeo de existencia del total de companias
			int totalCompaniasNoExistentes = 0;
			for (Compania compania : espectaculo.getCompanias()) {
				if (!daoTablaCompanias.existeCompania(compania.getId()))
					totalCompaniasNoExistentes++;
				else if(daoTablaCompanias.existeCompania(compania.getId()))
					daoCompaniaEspectaculo.addCompaniaToEspectaculo(compania.getId(), espectaculo.getId());
			}

			//Subtransaccion 2: asociacion companias a espectaculo
			if (espectaculo.getCompanias().isEmpty() ||
					espectaculo.getCompanias() == null ||
					espectaculo.getCompanias().size() == totalCompaniasNoExistentes)
			{
				conn.rollback();
				throw new Exception("Las compa駃as deben existir al momento de crear un espect醕ulo.");
			}
							
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
				daoEspectaculo.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void updateEspectaculo(Espectaculo espectaculo) throws Exception{
		DAOTablaEspectaculos daoCliente = new DAOTablaEspectaculos();
		try {
			// Transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.updateEspectaculo(espectaculo);

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

	public void deleteEspectaculo(Espectaculo cliente) throws Exception {
		DAOTablaEspectaculos daoCliente = new DAOTablaEspectaculos();
		try {
			// Transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.deleteEspectaculo(cliente);
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

	public void addSitio(Sitio sitio) throws Exception {
		// TODO Auto-generated method stub
		DAOTablaSitios daoSitio = new DAOTablaSitios();
		DAOTablaLocalidades daoLocalidades = new DAOTablaLocalidades();
		DAOTablaSitioLocalidad daoSitioLocalidad = new DAOTablaSitioLocalidad();
		DAOTablaEspecificaciones daoSitioEspec = new DAOTablaEspecificaciones();

		try {
			// Transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoSitio.setConn(this.conn);
			daoLocalidades.setConn(this.conn);
			daoSitioLocalidad.setConn(this.conn);
			daoSitioEspec.setConn(this.conn);

			//Subtransaccion 1
			daoSitio.addSitio(sitio);
			
			//Subtransaccion 2
			if (sitio.getEspecificaciones() != null && !sitio.getEspecificaciones().isEmpty())
				for (Especificacion especificacion : sitio.getEspecificaciones())
					daoSitioEspec.addEspecificacionToSitio(especificacion.getId(), sitio.getId(), especificacion.getEspecificacion());

			//Subtransaccion 3
			int totalLocalidadesNoExistentes = 0;
			for (Localidad localidad : sitio.getLocalidades()) {
				if (!daoLocalidades.existeLocalidad(localidad.getId()))
					totalLocalidadesNoExistentes++;
				else if(daoLocalidades.existeLocalidad(localidad.getId()))
					daoSitioLocalidad.addLocalidadToSitio(sitio.getId(), localidad.getId());
			}

			//Chequeo de existencia de localidades
			if (sitio.getLocalidades().isEmpty() ||
			    sitio.getLocalidades() == null ||
				sitio.getLocalidades().size() == totalLocalidadesNoExistentes)
			{
				conn.rollback();
				throw new Exception("Las localidades deben existir al momento de crear un sitio.");
			}

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
				daoSitio.cerrarRecursos();

				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addFuncion(Funcion funcion) throws Exception {
		// TODO Auto-generated method stub
		DAOTablaFunciones daoFunciones = new DAOTablaFunciones();
		DAOTablaSitios daoSitios = new DAOTablaSitios();
		DAOTablaCupos daoCupos = new DAOTablaCupos();

		try {
			// Transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoFunciones.setConn(conn);
			daoSitios.setConn(conn);
			daoCupos.setConn(conn);
			
			daoFunciones.addFuncion(funcion);
			
			int cupoInicial = daoSitios.getAforoSitio(funcion.getIdSitio());
			daoCupos.addCupo(funcion, cupoInicial);
			
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
				daoFunciones.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void deleteFuncion(Funcion compania) throws Exception {
		DAOTablaFunciones daoCompania = new DAOTablaFunciones();
		try {
			// Transaccion
			this.conn = darConexion();
			daoCompania.setConn(conn);
			daoCompania.deleteFuncion(compania);

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

//	public Cliente getClienteMasEspectaculos() throws SQLException, Exception {
//		// TODO Auto-generated method stub
//		DAOTablaEspectador daoCompania = new DAOTablaEspectador();
//		Cliente c = null;
//
//		try {
//			// Transaccion
//			this.conn = darConexion();
//			daoCompania.setConn(conn);
//			c = daoCompania.darClienteMasEspectaculo();
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
//				daoCompania.cerrarRecursos();
//				if (this.conn != null)
//					this.conn.close();
//			} catch (SQLException exception) {
//				System.err.println("SQLException closing resources:" + exception.getMessage());
//				exception.printStackTrace();
//				throw exception;
//			}
//		}
//
//		return c;
//	}


	public void addGeneros(Cliente cliente, GeneroList generos) throws Exception {
		// TODO Auto-generated method stub
		DAOTablaPreferencias daoPreferencias = new DAOTablaPreferencias();
		DAOTablaGeneros daoGeneros = new DAOTablaGeneros();
		
		try {
			// Transaccion
			this.conn = darConexion();
			daoPreferencias.setConn(conn);
			daoGeneros.setConn(conn);
			conn.setAutoCommit(false);
			
			int totalGenerosNoExistentes = 0;
			for (Genero genero : generos.getGeneros()) {
				if (!daoGeneros.existeGenero(genero.getId()))
					totalGenerosNoExistentes++;
				else if(daoGeneros.existeGenero(genero.getId()))
					daoPreferencias.addPreferencia(cliente, genero);
			}
			
			if (generos.getGeneros().isEmpty() ||
					generos.getGeneros() == null ||
					generos.getGeneros().size() == totalGenerosNoExistentes) {
				conn.rollback();
			}
			
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
				daoPreferencias.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void comprarBoletaReg(Cliente cliente, Boleta boleta) throws Exception {
		DAOTablaBoletas daoBoletas = new DAOTablaBoletas();
		DAOTablaVentas daoVentas = new DAOTablaVentas();
		DAOTablaCupos daoCupos = new DAOTablaCupos();
		
		try {
			// Transaccion
			this.conn = darConexion();
			daoBoletas.setConn(conn);
			daoVentas.setConn(conn);
			daoCupos.setConn(conn);
			conn.setAutoCommit(false);
			
			if (daoCupos.getCupoActual(boleta.getIdEspectaculo(), boleta.getIdFuncion()) > 0) {
				daoBoletas.addBoleta(boleta);
				daoVentas.addVenta(cliente, boleta);
				daoCupos.updateCupoVentaBoleta(boleta);
				
				conn.commit();
			}
			else {
				
				throw new Exception("No hay cupo disponible para la funci髇.");
			}
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
				daoVentas.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void comprarBoletasReg(Cliente cliente, BoletaList boletas) throws Exception {
		DAOTablaBoletas daoBoletas = new DAOTablaBoletas();
		DAOTablaVentas daoVentas = new DAOTablaVentas();
		DAOTablaCupos daoCupos = new DAOTablaCupos();
		
		try {
			// Transaccion
			this.conn = darConexion();
			daoBoletas.setConn(conn);
			daoVentas.setConn(conn);
			daoCupos.setConn(conn);
			conn.setAutoCommit(false);
			
			int cantBoletasCompradas = 0;
			
			for (Boleta boleta : boletas.getBoletas()) {
				if (daoCupos.getCupoActual(boleta.getIdEspectaculo(), boleta.getIdFuncion()) > 0) {
					daoBoletas.addBoleta(boleta);
					daoVentas.addVenta(cliente, boleta);
					daoCupos.updateCupoVentaBoleta(boleta);
					
					conn.setSavepoint();
					cantBoletasCompradas++;
				}
			}
			
			if (boletas.getBoletas().size() == cantBoletasCompradas)
				conn.commit();
			else {
				conn.rollback();
				throw new Exception("No hay cupo disponible para la funci髇.");
			}
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
				daoVentas.cerrarRecursos();
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
