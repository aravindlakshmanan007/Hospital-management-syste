package global.coda.hms.delegate;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.exception.SystemException;
import global.coda.hms.exception.UserNotFound;
import global.coda.hms.helper.AuthenticationHelper;
import global.coda.hms.model.User;

/**
 *
 * @author VC
 *
 */
public class AuthenticationServiceImpl {
	private static Logger LOGGER = LogManager.getLogger(AuthenticationServiceImpl.class);

	/**
	 *
	 * @param username username
	 * @param password password
	 * @return user
	 * @throws SystemException systemexception
	 */
	public static User login(String username, String password) throws SystemException {
		LOGGER.traceEntry(username);
		User userDetails = new User();
		try {
			userDetails = AuthenticationHelper.Authtication(username, password);
		} catch (SQLException error) {
			LOGGER.error("SQL exception occured at Authentication Service", error);
			throw new SystemException("SQL exception occured at Authentication Service");
		} catch (UserNotFound e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("User Not Found,Invalid credentials");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.traceExit(userDetails);
		return userDetails;
	}
}
