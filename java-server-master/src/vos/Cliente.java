package vos;

import org.codehaus.jackson.annotate.*;

/**
 * @author Julián
 */
public class Cliente {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "docId")
	private String docId;

	@JsonProperty(value = "nombre")
	private String nombre;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "address")
	private String address;

	public Cliente(@JsonProperty(value = "id") int id, @JsonProperty(value = "docId") String docId,
			@JsonProperty(value = "nombre") String nombre, @JsonProperty(value = "password") String password,
			@JsonProperty(value = "email") String email, @JsonProperty(value = "address") String address) {
		this.id = id;
		this.docId = docId;
		this.nombre = nombre;
		this.password = password;
		this.email = email;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the docId
	 */
	public String getDocId() {
		return docId;
	}

	/**
	 * @param docId
	 *            the docId to set
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
