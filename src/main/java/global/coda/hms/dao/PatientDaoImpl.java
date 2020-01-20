package global.coda.hms.dao;

import static global.coda.hms.constant.ApplicationConstants.HMS2000D;
import static global.coda.hms.constant.ApplicationConstants.HMS2003D;
import static global.coda.hms.constant.ApplicationConstants.HMS2004D;
import static global.coda.hms.constant.ApplicationConstants.HMS2005D;
import static global.coda.hms.constant.ApplicationConstants.HMS2006D;
import static global.coda.hms.constant.ApplicationConstants.HMS2007D;
import static global.coda.hms.constant.ApplicationConstants.HMS3001E;
import static global.coda.hms.constant.ApplicationConstants.HMS3003E;
import static global.coda.hms.constant.ApplicationConstants.HMS3004E;
import static global.coda.hms.constant.ApplicationConstants.HMS3009E;
import static global.coda.hms.constant.ApplicationConstants.HMS3011E;
import static global.coda.hms.constant.ApplicationConstants.HMS3012E;
import static global.coda.hms.constant.ApplicationConstants.MESSAGES_BUNDLE;
import static global.coda.hms.constant.QueryConstants.PATIENT_BLOODGROUP;
import static global.coda.hms.constant.QueryConstants.PATIENT_DELETE;
import static global.coda.hms.constant.QueryConstants.PATIENT_HEIGHT;
import static global.coda.hms.constant.QueryConstants.PATIENT_INSERT;
import static global.coda.hms.constant.QueryConstants.PATIENT_ISACTIVE;
import static global.coda.hms.constant.QueryConstants.PATIENT_READ;
import static global.coda.hms.constant.QueryConstants.PATIENT_READ_ALL;
import static global.coda.hms.constant.QueryConstants.PATIENT_UPDATE;
import static global.coda.hms.constant.QueryConstants.PATIENT_WEIGHT;
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
import global.coda.hms.exception.PatientNotFound;
import global.coda.hms.helper.UserDaoHelper;
import global.coda.hms.model.Patient;

/**
 *
 * @author VC
 *
 */
public class PatientDaoImpl {
	private static Logger LOGGER = LogManager.getLogger(PatientDaoImpl.class);
	private static ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	public static Patient createPatient(Patient patient) throws SQLException {
		int rowsChangedForPatient = 0;
		int rowsChangedForUser = 0;
		Connection databaseConnection = DBConnection.establishConnection();
		try {
			databaseConnection.setAutoCommit(false);
			PreparedStatement preparedStatementForUser = databaseConnection.prepareStatement(USER_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			UserDaoHelper.setUserDetails(preparedStatementForUser, patient);
			rowsChangedForUser = preparedStatementForUser.executeUpdate();
			if (rowsChangedForUser == 1) {
				if (UserDaoHelper.setIdForUser(preparedStatementForUser, patient)) {
					LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2005D));
					PreparedStatement preparedStatementforPatient = databaseConnection.prepareStatement(PATIENT_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					setPatientDetails(preparedStatementforPatient, patient);
					rowsChangedForPatient = preparedStatementforPatient.executeUpdate();
					if (rowsChangedForPatient == 1) {
						if (setIdForPatient(preparedStatementforPatient, patient)) {
							LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2006D));
							databaseConnection.commit();
						} else {
							LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS3004E));
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
		return patient;
	}

	/**
	 *
	 * @param preparedStatement preparedstatement
	 * @param patient           patient
	 * @throws SQLException sqlexception
	 */
	public static void setPatientDetails(PreparedStatement preparedStatement, Patient patient) throws SQLException {
		try {
			preparedStatement.setInt(ApplicationConstants.no1, patient.getPatientHeight());
			preparedStatement.setString(ApplicationConstants.no2, patient.getBloodGroup());
			preparedStatement.setInt(ApplicationConstants.no3, patient.getPatientWeight());
			preparedStatement.setInt(ApplicationConstants.no4, patient.getUserId());
			LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2000D));
		} catch (SQLException e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3004E), e);
		}
	}

	/**
	 *
	 * @param preparedStatementforPatient preparedstatement
	 * @param patient                     patient
	 * @return boolean
	 */
	public static boolean setIdForPatient(PreparedStatement preparedStatementforPatient, Patient patient) {
		boolean result = false;
		try {
			ResultSet resultSetId = preparedStatementforPatient.getGeneratedKeys();
			if (resultSetId != null && resultSetId.next()) {
				int userId = resultSetId.getInt(1);
				patient.setPatientId(userId);
				LOGGER.info(MessageFormat.format(LOCAL_MESSAGES_BUNDLE.getString(HMS2007D), userId));
				result = true;
			}
		} catch (Exception e) {
			LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3001E), e);
		}
		return result;
	}

	public static Patient readPatient(int userId) throws SQLException, PatientNotFound {
		List<Patient> allpatients = null;
		Patient patient = null;
		ResultSet resultData;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement preparedStatementforPatient = databaseConnection.prepareStatement(PATIENT_READ);
		preparedStatementforPatient.setInt(ApplicationConstants.no1, userId);
		resultData = preparedStatementforPatient.executeQuery();
		allpatients = getPatientDetails(resultData);
		patient = allpatients.get(0);
		return patient;
	}

	public static Patient updatePatient(int userId, Patient updatedPatient) throws SQLException, PatientNotFound {
		int rowsChangedForPatient = 0, rowsChangedForUser = 0;
		Patient patient = null;
		patient = readPatient(userId);
		if (patient != null) {
			Connection databaseConnection = DBConnection.establishConnection();
			try {
				databaseConnection.setAutoCommit(false);
				PreparedStatement userPreparedStatement = databaseConnection.prepareStatement(USER_UPDATE);
				UserDaoHelper.setUserDetails(userPreparedStatement, updatedPatient);
				updatedPatient.setPatientId(userId);
				userPreparedStatement.setInt(ApplicationConstants.no11, userId);
				rowsChangedForUser = userPreparedStatement.executeUpdate();
				if (rowsChangedForUser > 0) {
					LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2003D));
					PreparedStatement patientPreparedStatement = databaseConnection.prepareStatement(PATIENT_UPDATE);
					setPatientDetails(patientPreparedStatement, updatedPatient);
					patientPreparedStatement.setInt(ApplicationConstants.no4, userId);
					rowsChangedForPatient = patientPreparedStatement.executeUpdate();
					if (rowsChangedForPatient > 0) {
						LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2004D));
						databaseConnection.commit();
					}
				}
			} catch (SQLException e) {
				databaseConnection.rollback();
				LOGGER.error(LOCAL_MESSAGES_BUNDLE.getString(HMS3009E), e);
				throw (e);
			}
		} else {
			throw new PatientNotFound(LOCAL_MESSAGES_BUNDLE.getString(HMS3012E));
		}
		return updatedPatient;
	}

	public static int deletePatient(int userId) throws SQLException {
		// TODO Auto-generated method stub
		int rowsChangedForPatient = 0;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement patientPreparedStatement = databaseConnection.prepareStatement(PATIENT_DELETE);
		patientPreparedStatement.setInt(ApplicationConstants.no1, userId);
		// patientPreparedStatement.setInt(2, userId);
		rowsChangedForPatient = patientPreparedStatement.executeUpdate();
		return rowsChangedForPatient;
	}

	public static List<Patient> readAllPatients() throws SQLException, PatientNotFound {
		ResultSet resultData;
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement patientPreparedStatement = databaseConnection.prepareStatement(PATIENT_READ_ALL);
		resultData = patientPreparedStatement.executeQuery();
		List<Patient> allpatients = null;
		allpatients = getPatientDetails(resultData);
		return allpatients;
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
			patient.setPatientHeight(resultData.getInt(PATIENT_HEIGHT));
			patient.setBloodGroup(resultData.getString(PATIENT_BLOODGROUP));
			patient.setPatientWeight(resultData.getInt(PATIENT_WEIGHT));
			patient.setIsActive(resultData.getInt(USER_ISACTIVE));
			patient.setIsactive(resultData.getInt(PATIENT_ISACTIVE));
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
