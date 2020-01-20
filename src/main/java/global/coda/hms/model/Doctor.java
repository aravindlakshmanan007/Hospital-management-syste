package global.coda.hms.model;

import java.sql.Date;
/**
 *
 * @author VC
 *
 */
public class Doctor extends User {
	private int doctorId;
	private int doctorexperience;
	private String doctorspeciality;
	private int isactive;
	private Date createdOn;
	private Date updatedOn;
	/**
	 *
	 */
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorexperience=" + doctorexperience + ", doctorspeciality="
				+ doctorspeciality + ", isactive=" + isactive + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ ", toString()=" + super.toString() + ", getUserId()=" + getUserId() + ", getUsername()="
				+ getUsername() + ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail()
				+ ", getPhonenumber()=" + getPhonenumber() + ", getFirstname()=" + getFirstname() + ", getLastname()="
				+ getLastname() + ", getCity()=" + getCity() + ", getState()=" + getState() + ", getStreet()="
				+ getStreet() + ", getRoleId()=" + getRoleId() + ", getAge()=" + getAge() + "]";
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
	 * @return doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}
	/**
	 *
	 * @param doctorId doctorid
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 *
	 * @return doctorexperience
	 */
	public int getDoctorexperience() {
		return doctorexperience;
	}
	/**
	 *
	 * @param doctorexperience doctorexperience
	 */
	public void setDoctorexperience(int doctorexperience) {
		this.doctorexperience = doctorexperience;
	}
	/**
	 *
	 * @return doctorspeciality
	 */
	public String getDoctorspeciality() {
		return doctorspeciality;
	}
	/**
	 *
	 * @param doctorspeciality doctorspecialty
	 */
	public void setDoctorspeciality(String doctorspeciality) {
		this.doctorspeciality = doctorspeciality;
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
