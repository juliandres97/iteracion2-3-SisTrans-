package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeneroList {
	@JsonProperty(value = "generos")
	private List<Genero> generos;

	/**
	 * @param generos
	 */
	public GeneroList(@JsonProperty(value = "generos") List<Genero> generos) {
		this.generos = generos;
	}
	
	/**
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	
}
