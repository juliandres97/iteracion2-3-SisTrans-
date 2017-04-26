/**
 * 
 */
package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author ja.bermudez10
 *
 */

public class Boleta {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "idEspectaculo")
	private int idEspectaculo;

	@JsonProperty(value = "idFuncion")
	private int idFuncion;

	@JsonProperty(value = "idSitio")
	private int idSitio;

	@JsonProperty(value = "valor")
	private int valor;

	@JsonProperty(value = "abono")
	private int idAbono;

	/**
	 * @param id
	 * @param idEspectaculo
	 * @param idFuncion
	 * @param idSitio
	 * @param valor
	 */
	public Boleta(@JsonProperty(value = "id") int id, @JsonProperty(value = "idEspectaculo") int idEspectaculo,
			@JsonProperty(value = "idFuncion") int idFuncion, @JsonProperty(value = "idSitio") int idSitio,
			@JsonProperty(value = "valor") int valor, @JsonProperty(value = "abono") int idAbono) {
		this.id = id;
		this.idEspectaculo = idEspectaculo;
		this.idFuncion = idFuncion;
		this.idSitio = idSitio;
		this.valor = valor;
		this.idAbono = idAbono;
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
	 * @return the idFuncion
	 */
	public int getIdFuncion() {
		return idFuncion;
	}

	/**
	 * @param idFuncion
	 *            the idFuncion to set
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
	 * @param idSitio
	 *            the idSitio to set
	 */
	public void setIdSitio(int idSitio) {
		this.idSitio = idSitio;
	}

	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getValorConDescuento() {
		return (int) (valor * 0.8);
	}

	/**
	 * @return the abono
	 */
	public int getIdAbono() {
		return idAbono;
	}

	/**
	 * @param abono
	 *            the abono to set
	 */
	public void setIdAbono(int abono) {
		this.idAbono = abono;
	}

}
