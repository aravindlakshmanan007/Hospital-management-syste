package global.coda.hms.delegate;

import static global.coda.hms.constant.ApplicationConstants.HMS3007E;
import static global.coda.hms.constant.ApplicationConstants.HMS3008E;
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
import global.coda.hms.helper.PatientHelper;
import global.coda.hms.model.Patient;

/**
 *
 * @author VC
 *
 */
public class PatientServiceImpl {
	private final static Logger LOGGER = LogManager.getLogger(PatientServiceImpl.class);
	private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	public static Patient createPatient(Patient patient) {
		LOGGER.traceEntry(patient.toString());
		Patient createdPatient = null;
		try {
			createdPatient = PatientDaoImpl.createPatient(patient);
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3007E), e);
		}
		LOGGER.traceExit(createdPatient);
		return createdPatient;
	}

	public static List<Patient> readAllPatients() throws SQLException, PatientNotFound {
		LOGGER.traceEntry();
		List<Patient> patientList = null;
		patientList = PatientHelper.readAllPatients();
		LOGGER.traceExit(patientList);
		return patientList;
	}

	public static Patient readPatient(int patientId) throws SystemException {
		LOGGER.traceEntry(String.valueOf(patientId));

		try {
			Patient patient = PatientHelper.readPatient(patientId);
			LOGGER.traceExit(patient);
			return patient;
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3008E), e);
			throw new SystemException(LOCAL_MESSAGES_BUNDLE.getString(HMS3008E));
		} catch (PatientNotFound e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E), e);
			throw new SystemException(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E));
		}
	}

	public static boolean updatePatient(int patientId, Patient updatedPatient)
			throws SystemException, BussinessException, SQLException, PatientNotFound {
		LOGGER.traceEntry(String.valueOf(patientId) + " " + updatedPatient.toString());
		boolean result = false;
		result = PatientHelper.updatePatient(patientId, updatedPatient);
		LOGGER.traceExit(result);
		return result;
	}

	public static boolean deletePatient(int patientId) throws SystemException {
		LOGGER.traceEntry(String.valueOf(patientId));
		boolean result = false;
		try {
			result = PatientHelper.deletePatient(patientId);
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3010E), e);
			throw new SystemException(ApplicationConstants.genericException);
		}
		LOGGER.traceExit(result);
		return result;
	}

}
