package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author ja.bermudez10
 *
 */
public class Spec {
	
	@JsonProperty(value = "id")
	private int id;
	
	@JsonProperty(value = "spec")
	private String spec;

	/**
	 * @param id
	 * @param spec
	 */
	public Spec(int id, String spec) {
		this.id = id;
		this.spec = spec;
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
	 * @return the spec
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * @param spec the spec to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
}
