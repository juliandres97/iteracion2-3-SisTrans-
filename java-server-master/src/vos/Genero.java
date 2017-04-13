package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Julián
 *
 */
public class Genero {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "genero")
	private String genero;

	/**
	 * @param id
	 */
	public Genero(@JsonProperty(value = "id") int id, @JsonProperty(value = "genero") String genero) {
		this.id = id;
		this.genero = genero;
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
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

}
