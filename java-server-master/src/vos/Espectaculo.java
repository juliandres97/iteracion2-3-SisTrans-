package vos;

import java.util.List;

import org.codehaus.jackson.annotate.*;

public class Espectaculo {

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "nombre")
	private String nombre;

	@JsonProperty(value = "duracion")
	private int duracion;

	@JsonProperty(value = "idioma")
	private String idioma;

	@JsonProperty(value = "traductor")
	private String traductor;

	@JsonProperty(value = "descripcion")
	private String descripcion;

	@JsonProperty(value = "publicoObj")
	private String publicoObj;

	@JsonProperty(value = "companias")
	private List<Compania> companias;

	public Espectaculo(@JsonProperty(value = "id") int id, @JsonProperty(value = "nombre") String nombre,
			@JsonProperty(value = "duracion") int duracion, @JsonProperty(value = "idioma") String idioma,
			@JsonProperty(value = "traductor") String traductor,
			@JsonProperty(value = "descripcion") String descripcion,
			@JsonProperty(value = "publicoObj") String publicoObj,
			@JsonProperty(value = "companias") List<Compania> companias) {

		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.idioma = idioma;
		this.traductor = traductor;
		this.descripcion = descripcion;
		this.publicoObj = publicoObj;
		this.companias = companias;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getTraductor() {
		return traductor;
	}

	public void setTraductor(String traductor) {
		this.traductor = traductor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPublicoObj() {
		return publicoObj;
	}

	public void setPublicoObj(String publicoObj) {
		this.publicoObj = publicoObj;
	}

	/**
	 * @return the idsCompanias
	 */
	public List<Compania> getCompanias() {
		return companias;
	}

	/**
	 * @param idsCompanias
	 *            the idsCompanias to set
	 */
	public void setCompanias(List<Compania> companias) {
		this.companias = companias;
	}

}
