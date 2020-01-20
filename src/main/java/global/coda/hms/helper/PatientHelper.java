package global.coda.hms.helper;

import static global.coda.hms.constant.ApplicationConstants.HMS3009E;
import static global.coda.hms.constant.ApplicationConstants.HMS3010E;
import static global.coda.hms.constant.ApplicationConstants.HMS3011E;
import static global.coda.hms.constant.ApplicationConstants.MESSAGES_BUNDLE;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.constant.ApplicationConstants;
import global.coda.hms.dao.PatientDaoImpl;
import global.coda.hms.exception.BussinessException;
import global.coda.hms.exception.PatientNotFound;
import global.coda.hms.exception.SystemException;
import global.coda.hms.model.Patient;

/**
 *
 * @author VC
 *
 */
public class PatientHelper {
	private static Logger LOGGER = LogManager.getLogger(PatientHelper.class);
	private static ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	/**
	 *
	 * @param patient patient
	 * @return patient
	 * @throws SQLException sqlexception
	 */
	public static Patient createPatientHelper(Patient patient) throws SQLException {
		return patient;
	}

	/**
	 *
	 * @param userId userId
	 * @return patient
	 * @throws SQLException    sqlexception
	 * @throws PatientNotFound patientnotfound
	 * @throws SystemException systemexception
	 */
	public static Patient readPatient(int userId) throws SQLException, PatientNotFound, SystemException {
		LOGGER.traceEntry(String.valueOf(userId));
		try {
			Patient patient = new Patient();
			patient = PatientDaoImpl.readPatient(userId);
			LOGGER.traceExit(patient);
			return patient;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new SystemException(ApplicationConstants.sqlException);
		} catch (PatientNotFound e) {
			LOGGER.error(ApplicationConstants.patientNotFound);
			throw new SystemException(ApplicationConstants.patientNotFound);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException(ApplicationConstants.genericException);
		}
	}

	/**
	 *
	 * @return list_of_patient
	 */
	public static List<Patient> readAllPatients() {
		LOGGER.traceEntry();
		List<Patient> patientList = null;
		try {
			patientList = PatientDaoImpl.readAllPatients();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} catch (PatientNotFound e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E), e);
		}
		LOGGER.traceExit(patientList);
		return patientList;
	}

	/**
	 *
	 * @param userId userId
	 * @return boolean
	 * @throws SQLException    SQLException
	 * @throws SystemException SystemException
	 */
	public static boolean deletePatient(int userId) throws SQLException, SystemException {
		LOGGER.traceEntry(String.valueOf(userId));
		int rowsReturned = 0;
		boolean result = false;
		try {
			rowsReturned = PatientDaoImpl.deletePatient(userId);
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3010E), e);
			throw new SystemException(ApplicationConstants.genericException);
		}
		if (rowsReturned > 0) {
			result = true;
		} else {
			result = false;
		}
		LOGGER.traceExit(result);
		return result;
	}

	/**
	 *
	 * @param patientId      patientID
	 * @param updatedPatient updatePatient
	 * @return boolean
	 * @throws SystemException    SystemException
	 * @throws BussinessException BussinessException
	 */
	public static boolean updatePatient(int patientId, Patient updatedPatient)
			throws SystemException, BussinessException {
		LOGGER.traceEntry(String.valueOf(patientId) + " " + updatedPatient);
		boolean result = false;

		try {
			Patient patient = PatientDaoImpl.updatePatient(patientId, updatedPatient);
			if (patient != null) {
				result = true;
			}
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3009E), e);
			throw new SystemException(ApplicationConstants.genericException);
		} catch (PatientNotFound e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E), e);
			throw new BussinessException(ApplicationConstants.patientNotFound);
		}
		LOGGER.traceExit(result);
		return result;
	}
}
