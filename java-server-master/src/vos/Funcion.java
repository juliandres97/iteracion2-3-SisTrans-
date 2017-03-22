package vos;

import java.sql.Date;
import java.util.*;

import org.codehaus.jackson.annotate.JsonProperty;

import oracle.sql.*;

public class Funcion {
	
	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "idEspectaculo")
	private int idEspectaculo;

	@JsonProperty(value = "sitios")
	private List<Integer> sitios;
	
	@JsonProperty(value = "fechaRealizacion")
	private Date fechaRealizacion;
	
	/**
	 * @param id
	 * @param idEspectaculo
	 * @param sitio
	 * @param fechaRealizacion
	 */
	public Funcion(int id, int idEspectaculo, Sitio sitio, Date fechaRealizacion) {
		this.id = id;
		this.idEspectaculo = idEspectaculo;
		this.fechaRealizacion = fechaRealizacion;
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
	 * @return the idEspectaculo
	 */
	public int getIdEspectaculo() {
		return idEspectaculo;
	}

	/**
	 * @param idEspectaculo the idEspectaculo to set
	 */
	public void setIdEspectaculo(int idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}

	/**
	 * @return the sitio
	 */
	public List<Integer> getSitio() {
		return sitios;
	}

	/**
	 * @param sitio the sitio to set
	 */
	public void setSitio(List<Integer> sitios) {
		this.sitios = sitios;
	}

	/**
	 * @return the fechaRealizacion
	 */
	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	/**
	 * @param fechaRealizacion the fechaRealizacion to set
	 */
	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	
}
