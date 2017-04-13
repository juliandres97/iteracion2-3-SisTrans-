/**
 * 
 */
package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author ja.bermudez10
 *
 */
public class Localidad {
	
	@JsonProperty(value = "id")
	private int id;
	
	@JsonProperty(value = "localidad")
	private String localidad;

	/**
	 * @param id
	 * @param idSitio
	 * @param localidad
	 */
	public Localidad(@JsonProperty(value = "id") int id, @JsonProperty(value = "localidad") String localidad) {
		this.id = id;
		this.localidad = localidad;
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
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
}
