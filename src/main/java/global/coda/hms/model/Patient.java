package global.coda.hms.model;

import java.sql.Date;
/**
 *
 * @author VC
 *
 */
public class Patient extends User {
	private int patientId;
	private int patientHeight;
	private int patientWeight;
	private String bloodGroup;
	private int isactive;
	private Date createdOn;
	private Date updatedOn;
	/**
	 *
	 */
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientHeight=" + patientHeight + ", patientWeight="
				+ patientWeight + ", bloodGroup=" + bloodGroup + ", isactive=" + isactive + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + ", getUserId()=" + getUserId() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", getPhonenumber()="
				+ getPhonenumber() + ", getFirstname()=" + getFirstname() + ", getLastname()=" + getLastname()
				+ ", getCity()=" + getCity() + ", getState()=" + getState() + ", getStreet()=" + getStreet()
				+ ", getRoleId()=" + getRoleId() + ", getAge()=" + getAge() + "]";
	}
	/**
	 *
	 * @return patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 *
	 * @param patientId patientid
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	/**
	 *
	 * @return patientHeight
	 */
	public int getPatientHeight() {
		return patientHeight;
	}
	/**
	 *
	 * @param patientHeight patientheight
	 */
	public void setPatientHeight(int patientHeight) {
		this.patientHeight = patientHeight;
	}
	/**
	 *
	 * @return patientWeight
	 */
	public int getPatientWeight() {
		return patientWeight;
	}
	/**
	 *
	 * @param patientWeight patientweight
	 */
	public void setPatientWeight(int patientWeight) {
		this.patientWeight = patientWeight;
	}
	/**
	 *
	 * @return bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}
	/**
	 *
	 * @param bloodGroup bloodgroup
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	/**
	 *
	 * @return isactive
	 */
	public int getIsactive() {
		return isactive;
	}
	/**
	 *
	 * @param isactive isactive
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	/**
	 *
	 */
	@Override
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 *
	 */
	@Override
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 *
	 */
	@Override
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 *
	 */
	@Override
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
