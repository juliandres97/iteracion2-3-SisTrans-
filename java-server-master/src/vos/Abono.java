package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Abono {
	
	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "boletas")
	private BoletaList boletas;

	/**
	 * @param id
	 * @param boletas
	 */
	public Abono(@JsonProperty(value = "id") int id, @JsonProperty(value = "boletas") BoletaList boletas) {
		this.id = id;
		this.boletas = boletas;
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
	 * @return the boletas
	 */
	public BoletaList getBoletas() {
		return boletas;
	}

	/**
	 * @param boletas the boletas to set
	 */
	public void setBoletas(BoletaList boletas) {
		this.boletas = boletas;
	}
	
}
