package global.coda.hms.model;

import java.util.List;

/**
 *
 * @author VC
 *
 */
public class DoctorPatientMapper {
	private int doctorId;
	private List<Patient> patient;

	/**
	 *
	 * @return doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 *
	 * @param doctorId doctorId
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 *
	 * @return list_of_patient
	 */
	public List<Patient> getPatient() {
		return patient;
	}

	/**
	 *
	 * @param patient patient
	 */
	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

}
