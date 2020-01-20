package global.coda.hms.helper;

import static global.coda.hms.constant.ApplicationConstants.HMS2000D;
import static global.coda.hms.constant.ApplicationConstants.HMS2002D;
import static global.coda.hms.constant.ApplicationConstants.MESSAGES_BUNDLE;
import static global.coda.hms.constant.QueryConstants.USER_AGE;
import static global.coda.hms.constant.QueryConstants.USER_CITY;
import static global.coda.hms.constant.QueryConstants.USER_EMAIL;
import static global.coda.hms.constant.QueryConstants.USER_FIRSTNAME;
import static global.coda.hms.constant.QueryConstants.USER_ID;
import static global.coda.hms.constant.QueryConstants.USER_ISACTIVE;
import static global.coda.hms.constant.QueryConstants.USER_LASTNAME;
import static global.coda.hms.constant.QueryConstants.USER_NAME;
import static global.coda.hms.constant.QueryConstants.USER_PASSWORD;
import static global.coda.hms.constant.QueryConstants.USER_PHONENUMBER;
import static global.coda.hms.constant.QueryConstants.USER_ROLE_ID;
import static global.coda.hms.constant.QueryConstants.USER_STATE;
import static global.coda.hms.constant.QueryConstants.USER_STREET;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.constant.ApplicationConstants;
import global.coda.hms.model.User;

/**
 *
 * @author VC
 *
 */
public class UserDaoHelper {
	private final static Logger LOGGER = LogManager.getLogger(UserDaoHelper.class);
	private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	/**
	 *
	 * @param userPreparedStatement preparedstatement
	 * @param user                  user
	 * @return boolean
	 * @throws SQLException sqlexception
	 */
	public static boolean setIdForUser(PreparedStatement userPreparedStatement, User user) throws SQLException {
		boolean result = false;
		ResultSet resultSetId = userPreparedStatement.getGeneratedKeys();
		if (resultSetId != null && resultSetId.next()) {
			int userId = resultSetId.getInt(1);
			user.setUserId(userId);
			LOGGER.info(MessageFormat.format(LOCAL_MESSAGES_BUNDLE.getString(HMS2002D), userId));
			result = true;
		}
		return result;
	}

	/**
	 *
	 * @param userPreparedStatement userPreparedStaement
	 * @param user                  user
	 * @throws SQLException sqlException
	 */

	public static void setUserDetails(PreparedStatement userPreparedStatement, User user) throws SQLException {
		LOGGER.traceEntry();
		userPreparedStatement.setString(ApplicationConstants.no1, user.getUsername());
		userPreparedStatement.setString(ApplicationConstants.no2, user.getPassword());
		userPreparedStatement.setString(ApplicationConstants.no3, user.getEmail());
		userPreparedStatement.setString(ApplicationConstants.no4, user.getPhonenumber());
		userPreparedStatement.setString(ApplicationConstants.no5, user.getFirstname());
		userPreparedStatement.setString(ApplicationConstants.no6, user.getLastname());
		userPreparedStatement.setInt(ApplicationConstants.no7, user.getAge());
		userPreparedStatement.setString(ApplicationConstants.no8, user.getCity());
		userPreparedStatement.setString(ApplicationConstants.no9, user.getState());
		userPreparedStatement.setString(ApplicationConstants.no10, user.getStreet());
		userPreparedStatement.setInt(ApplicationConstants.no11, user.getRoleId());
		LOGGER.info(LOCAL_MESSAGES_BUNDLE.getString(HMS2000D));
		LOGGER.traceExit();
	}

	/**
	 *
	 * @param resultData resultData
	 * @param user       user
	 * @return user
	 * @throws SQLException sqlException
	 */
	public static User setUserDetailsInLogin(ResultSet resultData, User user) throws SQLException {
		// TODO Auto-generated method stub
		LOGGER.traceEntry();
		if (resultData.next()) {
			user.setUserId(resultData.getInt(USER_ID));
			user.setUsername(resultData.getString(USER_NAME));
			user.setPassword(resultData.getString(USER_PASSWORD));
			user.setEmail(resultData.getString(USER_EMAIL));
			user.setPhonenumber(resultData.getString(USER_PHONENUMBER));
			user.setFirstname(resultData.getString(USER_FIRSTNAME));
			user.setLastname(resultData.getString(USER_LASTNAME));
			user.setAge(resultData.getInt(USER_AGE));
			user.setCity(resultData.getString(USER_CITY));
			user.setState(resultData.getString(USER_STATE));
			user.setStreet(resultData.getString(USER_STREET));
			user.setRoleId(resultData.getInt(USER_ROLE_ID));
			user.setIsActive(resultData.getInt(USER_ISACTIVE));
			LOGGER.traceExit(LOCAL_MESSAGES_BUNDLE.getString(HMS2000D));
			return user;
		}
		LOGGER.traceExit();
		return null;
	}
}
