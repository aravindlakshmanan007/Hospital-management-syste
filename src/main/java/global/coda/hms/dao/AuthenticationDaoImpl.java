package global.coda.hms.dao;

import static global.coda.hms.constant.QueryConstants.USER_SELECT_WITH_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.helper.UserDaoHelper;
import global.coda.hms.model.User;

/**
 *
 * @author VC
 *
 */

public class AuthenticationDaoImpl {
	private static Logger LOGGER = LogManager.getLogger(AuthenticationDaoImpl.class);
//	private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	/**
	 *
	 * @param username username
	 * @param password password
	 * @return
	 * @throws SQLException
	 */
	public static User login(String username, String password) throws SQLException {
		LOGGER.traceEntry("username : " + username + " Password : " + password);
		ResultSet resultData;
		User user = new User();
		Connection databaseConnection = DBConnection.establishConnection();
		PreparedStatement preparedStatementForUser = databaseConnection.prepareStatement(USER_SELECT_WITH_ID);
		preparedStatementForUser.setString(1, username);
		preparedStatementForUser.setString(2, password);
		resultData = preparedStatementForUser.executeQuery();
		LOGGER.info(resultData.toString());
		user = UserDaoHelper.setUserDetailsInLogin(resultData, user);
		LOGGER.traceExit(user);
		return user;
	}
}
