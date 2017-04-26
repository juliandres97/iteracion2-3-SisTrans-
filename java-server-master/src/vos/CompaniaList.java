/**
 * 
 */
package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Julián
 *
 */
public class CompaniaList {

	@JsonProperty(value = "companias")
	private List<Compania> companias;

	/**
	 * @param companias
	 */
	public CompaniaList(@JsonProperty(value = "companias") List<Compania> companias) {
		this.companias = companias;
	}

	/**
	 * @return the clientes
	 */
	public List<Compania> getCompanias() {
		return companias;
	}

	/**
	 * @param companias
	 *            the companiass to set
	 */
	public void setCompanias(List<Compania> companias) {
		this.companias = companias;
	}
}
