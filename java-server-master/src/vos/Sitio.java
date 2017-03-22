package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sitio {
	
	@JsonProperty(value = "id")
	private int id;
	
	@JsonProperty(value = "tipoSitio")
	private String tipo;
	
	@JsonProperty(value = "aforo")
	private int aforo;
	
	@JsonProperty(value = "localidades")
	private List<Localidad> localidades;
	
	@JsonProperty(value = "especificaciones")
	private List<Spec> especificaciones;
	
	@JsonProperty(value = "tipoSilleteria")
	private String tipoSilleteria;

	/**
	 * @param id
	 * @param tipo
	 * @param aforo
	 * @param especificaciones
	 * @param tipoSilleteria
	 */
	public Sitio(int id, String tipo, int aforo, List<Spec> especificaciones, String tipoSilleteria) {
		this.id = id;
		this.tipo = tipo;
		this.aforo = aforo;
		this.especificaciones = especificaciones;
		this.tipoSilleteria = tipoSilleteria;
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipoSitio to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the aforo
	 */
	public int getAforo() {
		return aforo;
	}

	/**
	 * @param aforo the aforo to set
	 */
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	/**
	 * @return the especificaciones
	 */
	public List<Spec> getEspecificaciones() {
		return especificaciones;
	}

	/**
	 * @param especificaciones the especificaciones to set
	 */
	public void setEspecificaciones(List<Spec> especificaciones) {
		this.especificaciones = especificaciones;
	}

	/**
	 * @return the tipoSilleteria
	 */
	public String getTipoSilleteria() {
		return tipoSilleteria;
	}

	/**
	 * @param tipoSilleteria the tipoSilleteria to set
	 */
	public void setTipoSilleteria(String tipoSilleteria) {
		this.tipoSilleteria = tipoSilleteria;
	}
	
	
}
