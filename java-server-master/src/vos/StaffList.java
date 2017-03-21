package vos;

import java.util.*;
import org.codehaus.jackson.annotate.*;

/**
 * @author Julián
 *
 */
public class StaffList {
	@JsonProperty(value = "staffList")
	private List<Staff> staffList;

	/**
	 * @param miembrosStaff
	 */
	public StaffList(@JsonProperty(value = "staffList") List<Staff> staffList) {
		this.staffList = staffList;
	}

	/**
	 * @return the miembrosStaff
	 */
	public List<Staff> getStaffList() {
		return staffList;
	}

	/**
	 * @param staffList
	 *            the miembrosStaff to set
	 */
	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}

}
