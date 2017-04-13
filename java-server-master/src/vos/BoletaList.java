package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class BoletaList {
	@JsonProperty(value = "boletas")
	private List<Boleta> boletas;

	/**
	 * @param boletas
	 */
	public BoletaList(@JsonProperty(value = "boletas") List<Boleta> boletas) {
		this.boletas = boletas;
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
