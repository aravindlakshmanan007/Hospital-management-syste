package global.coda.hms.model;

import java.sql.Date;

/**
 *
 * @author VC
 *
 */
public class User {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String phonenumber;
	private String firstname;
	private String lastname;
	private String city;
	private String state;
	private String street;
	private int isActive;
	private Date createdOn;
	private Date updatedOn;
	private int roleId;
	private int age;

	/**
	 *
	 * @return isActive
	 */
	public int getIsActive() {
		return isActive;
	}

	/**
	 *
	 * @param isActive isactive
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", username=" + username + ", password=" + password + ", email="
				+ email + ", phonenumber=" + phonenumber + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", city=" + city + ", state=" + state + ", street=" + street + ", isActive=" + isActive
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", roleId=" + roleId + ", age=" + age + "]";
	}

	/**
	 *
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 *
	 * @param userId userid
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 *
	 * @return username username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @param username username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *
	 * @return email
	 */

	public String getEmail() {
		return email;
	}

	/**
	 *
	 * @param email email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 *
	 * @return phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 *
	 * @param phonenumber phonenumer
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 *
	 * @return firstname firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 *
	 * @param firstname firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 *
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 *
	 * @param lastname lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 *
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 *
	 * @param city city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 *
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 *
	 * @param state state
	 */

	public void setState(String state) {
		this.state = state;
	}

	/**
	 *
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 *
	 * @param street street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 *
	 * @return createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 *
	 * @param createdOn createdOn
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 *
	 * @return updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 *
	 * @param updatedOn updatedon
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 *
	 * @return roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 *
	 * @param roleId roleid
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 *
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 *
	 * @param age age
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
