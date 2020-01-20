package global.coda.hms.helper;

import static global.coda.hms.constant.ApplicationConstants.HMS3009E;
import static global.coda.hms.constant.ApplicationConstants.HMS3011E;
import static global.coda.hms.constant.ApplicationConstants.HMS3012E;
import static global.coda.hms.constant.ApplicationConstants.HMS3014E;
import static global.coda.hms.constant.ApplicationConstants.HMS3015E;
import static global.coda.hms.constant.ApplicationConstants.HMS3017E;
import static global.coda.hms.constant.ApplicationConstants.MESSAGES_BUNDLE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.dao.DoctorDaoImpl;
import global.coda.hms.exception.BussinessException;
import global.coda.hms.exception.DoctorNotFound;
import global.coda.hms.exception.PatientNotFound;
import global.coda.hms.exception.SystemException;
import global.coda.hms.model.Doctor;
import global.coda.hms.model.DoctorPatientMapper;
import global.coda.hms.model.Patient;

/**
 *
 * @author VC
 *
 */
public class DoctorHelper {
	private static Logger LOGGER = LogManager.getLogger(DoctorHelper.class);
	private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	/**
	 *
	 * @param doctor doctor
	 * @return doctor
	 * @throws SystemException systemexception
	 */
	public static Doctor createDoctor(Doctor doctor) throws SystemException {
		LOGGER.traceEntry(doctor.toString());
		Doctor createdDoctor;
		try {
			createdDoctor = DoctorDaoImpl.createDoctor(doctor);
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3014E), e);
			throw new SystemException(LOCAL_MESSAGES_BUNDLE.getString(HMS3014E));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong!!!");
		}
		LOGGER.traceExit(createdDoctor);
		return createdDoctor;
	}

	/**
	 *
	 * @param userId userId
	 * @return doctor
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexception
	 */
	public static Doctor readDoctor(int userId) throws SystemException, BussinessException {
		LOGGER.traceEntry(String.valueOf(userId));
		try {
			Doctor doctor = DoctorDaoImpl.readDoctor(userId);
			LOGGER.traceExit(doctor);
			return doctor;
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3015E), e);
			throw new SystemException(LOCAL_MESSAGES_BUNDLE.getString(HMS3015E));
		} catch (DoctorNotFound e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E), e);
			throw new BussinessException(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong!!!");
		}
	}

	/**
	 *
	 * @param userId        userId
	 * @param updatedDoctor updateddoctor
	 * @return boolean
	 * @throws SystemException    ystemexception
	 * @throws BussinessException bussinessexception
	 */
	public static boolean updateDoctor(int userId, Doctor updatedDoctor) throws SystemException, BussinessException {
		LOGGER.traceEntry(String.valueOf(userId) + " " + updatedDoctor.toString());
		boolean result = false;
		Doctor doctor = new Doctor();
		try {
			doctor = DoctorDaoImpl.updateDoctor(userId, updatedDoctor);
			if (doctor != null) {
				result = true;
			}
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3009E), e);
			throw new SystemException(LOCAL_MESSAGES_BUNDLE.getString(HMS3009E));
		} catch (DoctorNotFound e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E), e);
			throw new BussinessException(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("something went wrong,try again!!!");
		}
		LOGGER.traceExit(result);
		return result;
	}

	/**
	 *
	 * @param userId userId
	 * @return boolean
	 * @throws SystemException systemexception
	 */
	public static boolean deleteDoctor(int userId) throws SystemException {
		LOGGER.traceEntry(String.valueOf(userId));
		boolean result = false;
		int rowsChangedForDoctor = 0;
		try {
			rowsChangedForDoctor = DoctorDaoImpl.deleteDoctor(userId);
			if (rowsChangedForDoctor > 0) {
				result = true;
			}
			LOGGER.traceExit(result);
			return result;
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3017E), e);
			throw new SystemException(LOCAL_MESSAGES_BUNDLE.getString(HMS3017E));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong!!!");
		}
	}

	/**
	 *
	 * @return list_of_doctors
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexception
	 */
	public static List<Doctor> readAllDoctors() throws SystemException, BussinessException {
		LOGGER.traceEntry();
		List<Doctor> doctorList = null;
		try {
			doctorList = DoctorDaoImpl.readAllDoctors();
			if (doctorList == null) {
				throw new DoctorNotFound(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong");
		} catch (DoctorNotFound e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E), e);
			throw new BussinessException(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong!!!");
		}
		LOGGER.traceExit(doctorList);
		return doctorList;
	}

	/**
	 *
	 * @param doctorId doctorId
	 * @return lost_of_patient
	 * @throws BussinessException bussinessexception
	 * @throws SystemException    systemexception
	 */
	public static List<Patient> readAllPatients(int doctorId) throws BussinessException, SystemException {
		LOGGER.traceEntry(String.valueOf(doctorId));
		List<Patient> patientList = null;
		try {
			patientList = DoctorDaoImpl.MapDoctor(doctorId);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong");
		} catch (PatientNotFound e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E), e);
			throw new BussinessException(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Somethinf went wrong!!!");
		}
		LOGGER.traceExit(patientList);
		return patientList;
	}

	/**
	 *
	 * @return list_of_doctor_with_patient
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexception
	 */

	public static List<DoctorPatientMapper> ReadAllPatientForAllDoctor() throws SystemException, BussinessException {
		LOGGER.traceEntry();
		List<Doctor> doctorList = null;
		List<DoctorPatientMapper> doctorPatientList = new ArrayList<DoctorPatientMapper>();
		DoctorPatientMapper doctorPatientMapper = new DoctorPatientMapper();
		int doctorId = 0;
		try {
			doctorList = DoctorDaoImpl.readAllDoctors();
			if (doctorList == null) {
				LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
				throw new DoctorNotFound(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
			}
			for (Doctor doctor : doctorList) {
				doctorId = doctor.getDoctorId();
				doctorPatientMapper.setDoctorId(doctorId);
				doctorPatientMapper.setPatient(readAllPatients(doctorId));
				doctorPatientList.add(doctorPatientMapper);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong");
		} catch (DoctorNotFound e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E), e);
			throw new BussinessException(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong!!!");
		}
		LOGGER.traceExit(doctorPatientList);
		return doctorPatientList;
	}

}
