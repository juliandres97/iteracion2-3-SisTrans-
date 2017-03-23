package vos;

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

	@JsonProperty(value = "costo")
	private int costo;

	@JsonProperty(value = "traductor")
	private String traductor;

	@JsonProperty(value = "descripcion")
	private String descripcion;

	@JsonProperty(value = "publicoobj")
	private String publicoObj;

	public Espectaculo(@JsonProperty(value = "id") int id, @JsonProperty(value = "nombre") String nombre,
			@JsonProperty(value = "duracion") int duracion, @JsonProperty(value = "idioma") String idioma,
			@JsonProperty(value = "costo") int costo, @JsonProperty(value = "traductor") String traductor,
			@JsonProperty(value = "descripcion") String descripcion,
			@JsonProperty(value = "publicoobj") String publicoObj) {

		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.idioma = idioma;
		this.costo = costo;
		this.traductor = traductor;
		this.descripcion = descripcion;
		this.publicoObj = publicoObj;

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

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
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
	
}
