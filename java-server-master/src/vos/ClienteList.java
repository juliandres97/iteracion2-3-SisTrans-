package vos;

import java.util.*;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClienteList {

	@JsonProperty(value = "clientes")
	private List<Cliente> clientes;

	/**
	 * @param clientes
	 */
	public ClienteList(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes
	 *            the clientes to set
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
