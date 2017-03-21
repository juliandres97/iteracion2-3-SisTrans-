package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Julián
 *
 */
public class Categoria {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "tipoCategoria")
	private String tipoCategoria;

	/**
	 * @param id
	 */
	public Categoria(@JsonProperty(value = "id") int id, @JsonProperty(value = "tipoCategoria") String tipoCategoria) {
		this.id = id;
		this.tipoCategoria = tipoCategoria;
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
	 * @return the tipoCategoria
	 */
	public String getTipoCategoria() {
		return tipoCategoria;
	}

	/**
	 * @param tipoCategoria the tipoCategoria to set
	 */
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

}
