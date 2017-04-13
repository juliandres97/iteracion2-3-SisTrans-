package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Julián
 *
 */
public class Compania {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "nit")
	private String nit;

	@JsonProperty(value = "nombre")
	private String nombre;

	@JsonProperty(value = "representante")
	private String representante;

	@JsonProperty(value = "pais")
	private String pais;

	@JsonProperty(value = "web")
	private String web;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "llegada")
	private Date llegada;

	@JsonProperty(value = "salida")
	private Date salida;

	/**
	 * @param id
	 * @param nombre
	 * @param representante
	 * @param pais
	 * @param email
	 * @param web
	 * @param password
	 * @param llegada
	 * @param salida
	 */
	public Compania(@JsonProperty(value = "id") int id, @JsonProperty(value = "nit") String nit,
			@JsonProperty(value = "nombre") String nombre, @JsonProperty(value = "respresentante") String representante,
			@JsonProperty(value = "pais") String pais, @JsonProperty(value = "web") String web,
			@JsonProperty(value = "password") String password, @JsonProperty(value = "llegada") Date llegada,
			@JsonProperty(value = "salida") Date salida) {
		this.id = id;
		this.nit = nit;
		this.nombre = nombre;
		this.representante = representante;
		this.pais = pais;
		this.web = web;
		this.password = password;
		this.llegada = llegada;
		this.salida = salida;
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
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit
	 *            the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
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
	 * @return the representante
	 */
	public String getRepresentante() {
		return representante;
	}

	/**
	 * @param representante
	 *            the representante to set
	 */
	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the web
	 */
	public String getWeb() {
		return web;
	}

	/**
	 * @param web
	 *            the web to set
	 */
	public void setWeb(String web) {
		this.web = web;
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
	 * @return the llegada
	 */
	public Date getLlegada() {
		return llegada;
	}

	/**
	 * @param llegada
	 *            the llegada to set
	 */
	public void setLlegada(Date llegada) {
		this.llegada = llegada;
	}

	/**
	 * @return the salida
	 */
	public Date getSalida() {
		return salida;
	}

	/**
	 * @param salida
	 *            the salida to set
	 */
	public void setSalida(Date salida) {
		this.salida = salida;
	}

}
