package global.coda.hms.dao;

import static global.coda.hms.constant.ApplicationConstants.HMS2000D;
import static global.coda.hms.constant.ApplicationConstants.HMS2003D;
import static global.coda.hms.constant.ApplicationConstants.HMS2005D;
import static global.coda.hms.constant.ApplicationConstants.HMS2008D;
import static global.coda.hms.constant.ApplicationConstants.HMS2009D;
import static global.coda.hms.constant.ApplicationConstants.HMS2010D;
import static global.coda.hms.constant.ApplicationConstants.HMS3003E;
import static global.coda.hms.constant.ApplicationConstants.HMS3011E;
import static global.coda.hms.constant.ApplicationConstants.HMS3012E;
import static global.coda.hms.constant.ApplicationConstants.HMS3013E;
import static global.coda.hms.constant.ApplicationConstants.HMS3016E;
import static global.coda.hms.constant.ApplicationConstants.MESSAGES_BUNDLE;
import static global.coda.hms.constant.QueryConstants.DOCTOR_DELETE;
import static global.coda.hms.constant.QueryConstants.DOCTOR_EXPERIENCE;
import static global.coda.hms.constant.QueryConstants.DOCTOR_INSERT;
import static global.coda.hms.constant.QueryConstants.DOCTOR_ISACTIVE;
import static global.coda.hms.constant.QueryConstants.DOCTOR_READ;
import static global.coda.hms.constant.QueryConstants.DOCTOR_READ_ALL;
import static global.coda.hms.constant.QueryConstants.DOCTOR_SPECIALITY;
import static global.coda.hms.constant.QueryConstants.DOCTOR_UPDATE;
import static global.coda.hms.constant.QueryConstants.USER_AGE;
import static global.coda.hms.constant.QueryConstants.USER_CITY;
import static global.coda.hms.constant.QueryConstants.USER_CREATEDON;
import static global.coda.hms.constant.QueryConstants.USER_EMAIL;
import static global.coda.hms.constant.QueryConstants.USER_FIRSTNAME;
import static global.coda.hms.constant.QueryConstants.USER_ID;
import static global.coda.hms.constant.QueryConstants.USER_INSERT;
import static global.coda.hms.constant.QueryConstants.USER_ISACTIVE;
import static global.coda.hms.constant.QueryConstants.USER_LASTNAME;
import static global.coda.hms.constant.QueryConstants.USER_NAME;
import static global.coda.hms.constant.QueryConstants.USER_PASSWORD;
import static global.coda.hms.constant.QueryConstants.USER_PHONENUMBER;
import static global.coda.hms.constant.QueryConstants.USER_ROLE_ID;
import static global.coda.hms.constant.QueryConstants.USER_STATE;
import static global.coda.hms.constant.QueryConstants.USER_STREET;
import static global.coda.hms.constant.QueryConstants.USER_UPDATE;
import static global.coda.hms.constant.QueryConstants.USER_UPDATEDON;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.constant.ApplicationConstants;
import global.coda.hms.constant.QueryConstants;
import global.coda.hms.exception.DoctorNotFound;
import global.coda.hms.exception.PatientNotFound;
import global.coda.hms.exception.SystemException;
import global.coda.hms.helper.UserDaoHelper;
import global.coda.hms.model.Doctor;
import global.coda.hms.model.Patient;

/**
 *
 * @author VC
 *
 */

public class DoctorDaoImpl {
	private static Logger LOGGER = LogManager.getLogger(DoctorDaoImpl.class);
	private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	/**
	 * @param doctor doctor
	 * @return doctor
	 * @throws SQLException sqlexception
	 * @throws SystemException
	 */
	public static Doctor createDoctor(Doctor doctor) throws SQLException, SystemException {
		int rowsChangedForDoctor = 0;
		int rowsChangedForUser = 0;
		Connection databaseConnection = DBConnection.establishConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement preparedStatementForUser = databaseConnection.prepareStatement(USER_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			UserDaoHelper.setUserDetails(preparedStatementForUser, doctor);
			rowsChangedForUser = preparedStatementForUser.executeUpdate();
			if (rowsChangedForUser == 1) {
				if (UserDaoHelper.setIdForUser(preparedStatementForUser, doctor)) {
					LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2005D));
					PreparedStatement preparedStatementForDoctor = databaseConnection.prepareStatement(DOCTOR_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					setDoctorDetails(preparedStatementForDoctor, doctor);
					rowsChangedForDoctor = preparedStatementForDoctor.executeUpdate();
					if (rowsChangedForDoctor == 1) {
						if (setIdForDoctor(preparedStatementForDoctor, doctor)) {
							LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2008D));
							databaseConnection.commit();
						} else {
							LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3013E));
						}
					}
				}
			} else {
				LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS3003E));
			}
		} catch (SQLException e) {
			databaseConnection.rollback();
			throw (e);
		}
		return doctor;
	}

	/**
	 *
	 * @param preparedStatementForDoctor preparedStatement
	 * @param doctor                     doctor
	 * @return boolean
	 * @throws SystemException
	 */
	private static boolean setIdForDoctor(PreparedStatement preparedStatementForDoctor, Doctor doctor)
			throws SystemException {
		boolean result = false;
		try {
			ResultSet resultSetId = preparedStatementForDoctor.getGeneratedKeys();
			if (resultSetId != null && resultSetId.next()) {
				int userId = resultSetId.getInt(1);
				doctor.setDoctorId(userId);
				LOGGER.info(MessageFormat.format(LOCAL_MESSAGES_BUNDLE.getString(HMS2009D), userId));
				result = true;
			}
		} catch (Exception e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3013E), e);
			throw new SystemException(ApplicationConstants.genericException);
		}
		return result;
	}

	/**
	 *
	 * @param preparedStatementForDoctor preparedStatement
	 * @param doctor                     doctor
	 */
	private static void setDoctorDetails(PreparedStatement preparedStatementForDoctor, Doctor doctor) {
		try {
			preparedStatementForDoctor.setInt(ApplicationConstants.no1, doctor.getDoctorexperience());
			preparedStatementForDoctor.setString(ApplicationConstants.no2, doctor.getDoctorspeciality());
			preparedStatementForDoctor.setInt(ApplicationConstants.no3, doctor.getUserId());
			LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2000D));
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3013E), e);
		}
	}

	/**
	 *
	 * @param userId userId
	 * @return userId
	 * @throws SQLException    sqlexception
	 * @throws DoctorNotFound  doctornotfound
	 * @throws SystemException systemexception
	 */
	public static Doctor readDoctor(int userId) throws SQLException, DoctorNotFound, SystemException {
		List<Doctor> alldoctors = null;
		Doctor doctor = null;
		ResultSet resultData;
		try {
			Connection databaseConnection = DBConnection.establishConnection();
			PreparedStatement preparedStatementforPatient = databaseConnection.prepareStatement(DOCTOR_READ);
			preparedStatementforPatient.setInt(ApplicationConstants.no1, userId);
			resultData = preparedStatementforPatient.executeQuery();
			alldoctors = getDoctorDetails(resultData);
			doctor = alldoctors.get(0);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException(ApplicationConstants.genericException);
		}
		return doctor;
	}

	/**
	 *
	 * @param resultData result_data
	 * @return list_of_doctors
	 * @throws SQLException    sqlexception
	 * @throws DoctorNotFound  doctornotfound
	 * @throws SystemException systemexception
	 */
	private static List<Doctor> getDoctorDetails(ResultSet resultData)
			throws SQLException, DoctorNotFound, SystemException {
		List<Doctor> alldoctors = new ArrayList<Doctor>();
		try {
			while (resultData.next()) {
				Doctor doctor = new Doctor();
				doctor.setDoctorId(resultData.getInt(USER_ID));
				doctor.setUsername(resultData.getString(USER_NAME));
				doctor.setPassword(resultData.getString(USER_PASSWORD));
				doctor.setEmail(resultData.getString(USER_EMAIL));
				doctor.setPhonenumber(resultData.getString(USER_PHONENUMBER));
				doctor.setFirstname(resultData.getString(USER_FIRSTNAME));
				doctor.setLastname(resultData.getString(USER_LASTNAME));
				doctor.setAge(resultData.getInt(USER_AGE));
				doctor.setStreet(resultData.getString(USER_STREET));
				doctor.setCity(resultData.getString(USER_CITY));
				doctor.setState(resultData.getString(USER_STATE));
				doctor.setRoleId(resultData.getInt(USER_ROLE_ID));
				doctor.setDoctorexperience(resultData.getInt(DOCTOR_EXPERIENCE));
				doctor.setDoctorspeciality(resultData.getString(DOCTOR_SPECIALITY));
				doctor.setIsactive(resultData.getInt(DOCTOR_ISACTIVE));
				doctor.setIsActive(resultData.getInt(USER_ISACTIVE));
				doctor.setCreatedOn(resultData.getDate(USER_CREATEDON));
				doctor.setUpdatedOn(resultData.getDate(USER_UPDATEDON));
				alldoctors.add(doctor);
			}
			if (alldoctors.size() < 1) {
				throw new DoctorNotFound(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException(ApplicationConstants.genericException);
		}
		return alldoctors;
	}

	/**
	 *
	 * @param userId userId
	 * @param doctor doctor
	 * @return doctor
	 * @throws SQLException    sqlexception
	 * @throws DoctorNotFound  doctornotfound
	 * @throws SystemException
	 */
	public final static Doctor updateDoctor(int userId, Doctor doctor)
			throws SQLException, DoctorNotFound, SystemException {
		int rowsChangedForDoctor = 0, rowsChangedForUser = 0;
		Doctor checkDoctor = null;
		checkDoctor = readDoctor(userId);
		if (checkDoctor != null) {
			Connection databaseConnection = DBConnection.establishConnection();
			try {
				databaseConnection.setAutoCommit(false);
				PreparedStatement userpreparedStatement = databaseConnection.prepareStatement(USER_UPDATE);
				UserDaoHelper.setUserDetails(userpreparedStatement, doctor);
				doctor.setDoctorId(userId);
				userpreparedStatement.setInt(ApplicationConstants.no11, userId);
				rowsChangedForUser = userpreparedStatement.executeUpdate();
				if (rowsChangedForUser > 0) {
					LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2003D));
					PreparedStatement doctorPreparedStatement = databaseConnection.prepareStatement(DOCTOR_UPDATE);
					setDoctorDetails(doctorPreparedStatement, doctor);
					doctorPreparedStatement.setInt(ApplicationConstants.no3, userId);
					rowsChangedForDoctor = doctorPreparedStatement.executeUpdate();
					if (rowsChangedForDoctor > 0) {
						LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2010D));
						databaseConnection.commit();
					}
				}
			} catch (SQLException e) {
				databaseConnection.rollback();
				LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3016E), e);
			}
		} else {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E));
			throw new DoctorNotFound(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E));
		}
		return doctor;
	}

	/**
	 *
	 * @param userId userId
	 * @return integer
	 * @throws SQLException sqlexception
	 */
	public static int deleteDoctor(int userId) throws SQLException {
		int rowsChangedForDoctor = 0;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement doctorPreparedStatement = databaseConnection.prepareStatement(DOCTOR_DELETE);
		doctorPreparedStatement.setInt(ApplicationConstants.no1, userId);
		rowsChangedForDoctor = doctorPreparedStatement.executeUpdate();
		return rowsChangedForDoctor;
	}

	/**
	 *
	 * @return list_of_doctor
	 * @throws SQLException    sqlexception
	 * @throws DoctorNotFound  doctornotfound
	 * @throws SystemException
	 */
	public static List<Doctor> readAllDoctors() throws SQLException, DoctorNotFound, SystemException {
		List<Doctor> alldoctors = null;
		ResultSet resultData;
		try {
			Connection databaseConnection = DBConnection.establishConnection();
			PreparedStatement doctorPreparedStatement = databaseConnection.prepareStatement(DOCTOR_READ_ALL);
			resultData = doctorPreparedStatement.executeQuery();
			alldoctors = getDoctorDetails(resultData);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException(ApplicationConstants.genericException);
		}
		return alldoctors;
	}

	/**
	 *
	 * @param patientID patientId
	 * @return list_of_patient
	 * @throws SQLException    sqlexception
	 * @throws PatientNotFound patientnotfound
	 * @throws SystemException
	 */
	public static List<Patient> MapDoctor(int patientID) throws SQLException, PatientNotFound, SystemException {
//		LOGGER.traceEntry(patientID);
		ResultSet resultData;
		List<Patient> allPatients = null;
		try {
			Connection databaseConnection = DBConnection.establishConnection();
			PreparedStatement preparedStatementForUser = databaseConnection
					.prepareStatement(QueryConstants.MAP_DOCTOR_FOR_PATIENT);
			preparedStatementForUser.setInt(ApplicationConstants.no1, patientID);
			resultData = preparedStatementForUser.executeQuery();
			LOGGER.info(resultData);
			allPatients = getPatientDetails(resultData);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException(ApplicationConstants.genericException);
		}
		return allPatients;
	}

	/**
	 *
	 * @param resultData resultData
	 * @return list_of_patient
	 * @throws SQLException    sqlexception
	 * @throws PatientNotFound patientNotFoundException
	 */
	public static List<Patient> getPatientDetails(ResultSet resultData) throws SQLException, PatientNotFound {
		List<Patient> allpatients = new ArrayList<Patient>();
		while (resultData.next()) {
			Patient patient = new Patient();
			patient.setPatientId(resultData.getInt(USER_ID));
			patient.setUsername(resultData.getString(USER_NAME));
			patient.setPassword(resultData.getString(USER_PASSWORD));
			patient.setEmail(resultData.getString(USER_EMAIL));
			patient.setPhonenumber(resultData.getString(USER_PHONENUMBER));
			patient.setFirstname(resultData.getString(USER_FIRSTNAME));
			patient.setLastname(resultData.getString(USER_LASTNAME));
			patient.setAge(resultData.getInt(USER_AGE));
			patient.setStreet(resultData.getString(USER_STREET));
			patient.setCity(resultData.getString(USER_CITY));
			patient.setState(resultData.getString(USER_STATE));
			patient.setRoleId(resultData.getInt(USER_ROLE_ID));
			patient.setIsActive(resultData.getInt(USER_ISACTIVE));
			patient.setCreatedOn(resultData.getDate(USER_CREATEDON));
			patient.setUpdatedOn(resultData.getDate(USER_UPDATEDON));
			allpatients.add(patient);
		}
		if (allpatients.size() < 1) {
			throw new PatientNotFound(LOCAL_MESSAGES_BUNDLE.getString(HMS3011E));
		}
		return allpatients;
	}
}
