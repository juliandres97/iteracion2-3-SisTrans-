package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sitio {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "nombre")
	private String nombre;

	@JsonProperty(value = "tipoSitio")
	private String tipoSitio;

	@JsonProperty(value = "aforo")
	private int aforo;

	@JsonProperty(value = "localidades")
	private List<Localidad> localidades;

	@JsonProperty(value = "especificaciones")
	private List<Especificacion> especificaciones;

	@JsonProperty(value = "silleteria")
	private String silleteria;

	/**
	 * @param id
	 * @param tipoSitio
	 * @param aforo
	 * @param localidades
	 * @param especificaciones
	 * @param silleteria
	 */
	public Sitio(@JsonProperty(value = "id") int id, @JsonProperty(value = "nombre") String nombre,
			@JsonProperty(value = "tipoSitio") String tipoSitio, @JsonProperty(value = "aforo") int aforo,
			@JsonProperty(value = "localidades") List<Localidad> localidades,
			@JsonProperty(value = "especificaciones") List<Especificacion> especificaciones,
			@JsonProperty(value = "silleteria") String silleteria) {
		this.id = id;
		this.nombre = nombre;
		this.tipoSitio = tipoSitio;
		this.aforo = aforo;
		this.especificaciones = especificaciones;
		this.localidades = localidades;
		this.silleteria = silleteria;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipoSitio
	 */
	public String getTipoSitio() {
		return tipoSitio;
	}

	/**
	 * @param sitio
	 *            the tipoSitio to set
	 */
	public void setTipoSitio(String sitio) {
		this.tipoSitio = sitio;
	}

	/**
	 * @return the aforo
	 */
	public int getAforo() {
		return aforo;
	}

	/**
	 * @param aforo
	 *            the aforo to set
	 */
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	/**
	 * @return the localidades
	 */
	public List<Localidad> getLocalidades() {
		return localidades;
	}

	/**
	 * @param localidades
	 *            the localidades to set
	 */
	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}

	/**
	 * @return the especificaciones
	 */
	public List<Especificacion> getEspecificaciones() {
		return especificaciones;
	}

	/**
	 * @param especificaciones
	 *            the especificaciones to set
	 */
	public void setEspecificaciones(List<Especificacion> especificaciones) {
		this.especificaciones = especificaciones;
	}

	/**
	 * @return the silleteria
	 */
	public String getSilleteria() {
		return silleteria;
	}

	/**
	 * @param silleteria
	 *            the silleteria to set
	 */
	public void setSilleteria(String silleteria) {
		this.silleteria = silleteria;
	}

}
