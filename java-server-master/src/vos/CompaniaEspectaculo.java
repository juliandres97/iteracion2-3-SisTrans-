package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class CompaniaEspectaculo {

	@JsonProperty(value = "idCompania")
	private int idCompania;

	@JsonProperty(value = "idEspectaculo")
	private int idEspectaculo;

	/**
	 * 
	 * @param idCompania
	 * @param idEspectaculo
	 */
	public CompaniaEspectaculo(@JsonProperty(value = "id") int idCompania,
			@JsonProperty(value = "idEspectaculo") int idEspectaculo) {
		this.idCompania = idCompania;
		this.idEspectaculo = idEspectaculo;
	}

	/**
	 * @return the id
	 */
	public int getIdCompania() {
		return idCompania;
	}

	/**
	 * @param idCompania
	 *            the id to set
	 */
	public void setIdCompania(int idCompania) {
		this.idCompania = idCompania;
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

}
