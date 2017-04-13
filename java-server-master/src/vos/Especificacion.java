package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author ja.bermudez10
 *
 */
public class Especificacion {
	
	@JsonProperty(value = "id")
	private int id;
	
	@JsonProperty(value = "especificacion")
	private String especificacion;

	/**
	 * @param id
	 * @param spec
	 */
	public Especificacion(@JsonProperty(value = "id") int id, @JsonProperty(value = "especificacion") String especificacion) {
		this.id = id;
		this.especificacion = especificacion;
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
	 * @return the spec
	 */
	public String getEspecificacion() {
		return especificacion;
	}

	/**
	 * @param spec the spec to set
	 */
	public void setEspecificacion(String especificacion) {
		this.especificacion = especificacion;
	}
	
}
