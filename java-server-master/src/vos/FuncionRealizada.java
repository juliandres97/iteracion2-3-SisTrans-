/**
 * 
 */
package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author ja.bermudez10
 *
 */
public class FuncionRealizada {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "idEspectaculo")
	private int idEspectaculo;

	@JsonProperty(value = "realizado")
	private String realizado;

	/**
	 * @param id
	 * @param idEspectaculo
	 * @param realizado
	 */
	public FuncionRealizada(@JsonProperty(value = "id") int id,
			@JsonProperty(value = "idEspectaculo") int idEspectaculo,
			@JsonProperty(value = "realizado") String realizado) {
		this.id = id;
		this.idEspectaculo = idEspectaculo;
		this.realizado = realizado;
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
	 * @return the realizado
	 */
	public String getRealizado() {
		return realizado;
	}

	/**
	 * @param realizado the realizado to set
	 */
	public void setRealizado(String realizado) {
		this.realizado = realizado;
	}

}
