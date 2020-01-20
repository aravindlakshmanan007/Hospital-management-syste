package global.coda.hms.delegate;

import static global.coda.hms.constant.ApplicationConstants.HMS3012E;
import static global.coda.hms.constant.ApplicationConstants.MESSAGES_BUNDLE;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.exception.BussinessException;
import global.coda.hms.exception.DoctorNotFound;
import global.coda.hms.exception.PatientNotFound;
import global.coda.hms.exception.SystemException;
import global.coda.hms.helper.DoctorHelper;
import global.coda.hms.model.Doctor;
import global.coda.hms.model.DoctorPatientMapper;
import global.coda.hms.model.Patient;

/**
 *
 * @author VC
 *
 */
public class DoctorServiceImpl {
	private static Logger LOGGER = LogManager.getLogger(DoctorServiceImpl.class);
	private static ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	public static Doctor createDoctor(Doctor doctor) throws SQLException, SystemException {
		LOGGER.traceEntry(doctor.toString());
		Doctor createdDoctor;
		createdDoctor = DoctorHelper.createDoctor(doctor);
		LOGGER.traceExit(createdDoctor);
		return createdDoctor;
	}

	public static Doctor readDoctor(int userId)
			throws SQLException, DoctorNotFound, SystemException, BussinessException {
		LOGGER.traceEntry(String.valueOf(userId));
		Doctor doctor;
		doctor = DoctorHelper.readDoctor(userId);
		LOGGER.traceExit(doctor);
		return doctor;
	}

	public static boolean updateDoctor(int userId, Doctor updatedDoctor)
			throws SQLException, DoctorNotFound, SystemException, BussinessException {
		LOGGER.traceEntry(userId + " " + updatedDoctor);
		boolean result = false;
		result = DoctorHelper.updateDoctor(userId, updatedDoctor);
		LOGGER.traceExit(result);
		return result;
	}

	public static boolean deleteDoctor(int userId) throws SQLException, SystemException {
		LOGGER.traceEntry(String.valueOf(userId));
		boolean result = false;
		result = DoctorHelper.deleteDoctor(userId);
		LOGGER.traceExit(result);
		return result;
	}

	public static List<Doctor> readAllDoctors()
			throws SQLException, DoctorNotFound, SystemException, BussinessException {
		LOGGER.traceEntry();
		List<Doctor> doctorList;
		doctorList = DoctorHelper.readAllDoctors();
		if (doctorList == null) {
			throw new DoctorNotFound(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
		}
		LOGGER.traceExit(doctorList);
		return doctorList;
	}

	/**
	 *
	 * @param doctorId doctorId
	 * @return list_of_patient
	 * @throws SQLException       sqlexception
	 * @throws PatientNotFound    patientnotfound
	 * @throws BussinessException bussinessexception
	 * @throws SystemException    systemexception
	 */
	public static List<Patient> readAllPatients(int doctorId)
			throws SQLException, PatientNotFound, BussinessException, SystemException {
		LOGGER.traceEntry(String.valueOf(doctorId));
		List<Patient> patientList = null;
		patientList = DoctorHelper.readAllPatients(doctorId);
		LOGGER.traceExit(patientList);
		return patientList;
	}

	/**
	 *
	 * @return list_of_doctorpatient
	 * @throws SystemException    systemException
	 * @throws BussinessException BussinessException
	 */
	public static List<DoctorPatientMapper> AllPatientForAllDoctorDel() throws SystemException, BussinessException {
		LOGGER.traceEntry();
		List<DoctorPatientMapper> doctorPatientList = null;
		doctorPatientList = DoctorHelper.ReadAllPatientForAllDoctor();
		LOGGER.traceExit(doctorPatientList);
		return doctorPatientList;
	}

}
