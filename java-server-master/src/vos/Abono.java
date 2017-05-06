package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Abono {
	
	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "boletas")
	private List<Boleta> boletas;

	/**
	 * @param id
	 * @param boletas
	 */
	public Abono(@JsonProperty(value = "id") int id, @JsonProperty(value = "boletas") List<Boleta> boletas) {
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
	public List<Boleta> getBoletas() {
		return boletas;
	}

	/**
	 * @param boletas the boletas to set
	 */
	public void setBoletas(List<Boleta> boletas) {
		this.boletas = boletas;
	}
	
}
