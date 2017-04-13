package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Funcion {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "idEspectaculo")
	private int idEspectaculo;

	@JsonProperty(value = "idSitio")
	private int idSitio;

	@JsonProperty(value = "fechaRealizacion")
	private Date fechaRealizacion;

	/**
	 * @param id
	 * @param idEspectaculo
	 * @param idSitio
	 * @param fechaRealizacion
	 */
	public Funcion(@JsonProperty(value = "id") int id, @JsonProperty(value = "idEspectaculo") int idEspectaculo,
			@JsonProperty(value = "idSitio") int idSitio,
			@JsonProperty(value = "fechaRealizacion") Date fechaRealizacion) {
		this.id = id;
		this.idEspectaculo = idEspectaculo;
		this.idSitio = idSitio;
		this.fechaRealizacion = fechaRealizacion;
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
	 * @return the idEspectaculo
	 */
	public int getIdEspectaculo() {
		return idEspectaculo;
	}

	/**
	 * @param idEspectaculo
	 *            the idEspectaculo to set
	 */
	public void setIdEspectaculo(int idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}

	/**
	 * @return the sitio
	 */
	public int getIdSitio() {
		return idSitio;
	}

	/**
	 * @param idSitio
	 *            the sitio to set
	 */
	public void setIdSitio(int idSitio) {
		this.idSitio = idSitio;
	}

	/**
	 * @return the fechaRealizacion
	 */
	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	/**
	 * @param fechaRealizacion
	 *            the fechaRealizacion to set
	 */
	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

}
