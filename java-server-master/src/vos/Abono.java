package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Abono {
	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "idEspectaculo")
	private int idEspectaculo;

	@JsonProperty(value = "idFuncion")
	private int idFuncion;

	@JsonProperty(value = "idSitio")
	private int idSitio;

	@JsonProperty(value = "descuento")
	private int descuento;

	/**
	 * @param id
	 * @param idEspectaculo
	 * @param idFuncion
	 * @param idSitio
	 * @param valor
	 */
	public Abono(@JsonProperty(value = "id") int id, @JsonProperty(value = "idEspectaculo") int idEspectaculo,
			@JsonProperty(value = "idFuncion") int idFuncion, @JsonProperty(value = "idSitio") int idSitio, @JsonProperty(value = "descuento") int descuento) {
		this.id = id;
		this.idEspectaculo = idEspectaculo;
		this.idFuncion = idFuncion;
		this.idSitio = idSitio;
		this.descuento = descuento;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param idEspectaculo the idEspectaculo to set
	 */
	public void setIdEspectaculo(int idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}

	/**
	 * @return the idFuncion
	 */
	public int getIdFuncion() {
		return idFuncion;
	}

	/**
	 * @param idFuncion the idFuncion to set
	 */
	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}

	/**
	 * @return the idSitio
	 */
	public int getIdSitio() {
		return idSitio;
	}

	/**
	 * @param idSitio the idSitio to set
	 */
	public void setIdSitio(int idSitio) {
		this.idSitio = idSitio;
	}

	/**
	 * @return the valor
	 */
	public int getDescuento() {
		return descuento;
	}

	/**
	 * @param descuento the valor to set
	 */
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
}
