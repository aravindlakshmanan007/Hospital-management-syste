package global.coda.hms.delegate.interfaces;

import java.sql.SQLException;
import java.util.List;

import global.coda.hms.exception.BussinessException;
import global.coda.hms.exception.PatientNotFound;
import global.coda.hms.exception.SystemException;
import global.coda.hms.exception.UserNotFound;
import global.coda.hms.model.Patient;

/**
 *
 * @author VC
 *
 */
public interface PatientService {
	/**
	 *
	 * @param patient patient
	 * @return patient
	 * @throws SQLException    sqlexception
	 * @throws UserNotFound    userNotFound
	 * @throws PatientNotFound patientNotFound
	 * @throws SQLException    sqlexception
	 */
	Patient createPatient(Patient patient) throws SQLException, UserNotFound, PatientNotFound;

	/**
	 *
	 * @return list_of_patient
	 * @throws SQLException    sqlexception
	 * @throws PatientNotFound patientNotFound
	 */
	List<Patient> readAllPatients() throws SQLException, PatientNotFound;

	/**
	 *
	 * @param patientId patientId
	 * @return patient
	 * @throws SQLException    sqlException
	 * @throws UserNotFound    userNotException
	 * @throws SystemException systemException
	 */
	Patient readPatient(int patientId) throws SQLException, UserNotFound, SystemException;

	/**
	 *
	 * @param patientId      patientId
	 * @param updatedPatient updatedPatient
	 * @return boolean
	 * @throws SQLException       sqlexception
	 * @throws PatientNotFound    patientNotFound
	 * @throws SystemException    bussinessexception
	 * @throws BussinessException Bussinessexception
	 */
	boolean updatePatient(int patientId, Patient updatedPatient)
			throws SystemException, BussinessException, SQLException, PatientNotFound;

	/**
	 *
	 * @param patientId patientId
	 * @return boolean
	 * @throws SystemException systemexception
	 */
	boolean deletePatient(int patientId) throws SystemException;
}
