package global.coda.hms.helper;

import static global.coda.hms.constant.ApplicationConstants.HMS3000E;
import static global.coda.hms.constant.ApplicationConstants.MESSAGES_BUNDLE;

import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.coda.hms.dao.AuthenticationDaoImpl;
import global.coda.hms.exception.SystemException;
import global.coda.hms.exception.UserNotFound;
import global.coda.hms.model.User;

/**
 *
 * @author VC
 *
 */
public class AuthenticationHelper {
	private final static Logger LOGGER = LogManager.getLogger(AuthenticationHelper.class);
	private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE);

	/**
	 *
	 * @param userName USERNAME
	 * @param passWord PASSWORD
	 * @return user
	 * @throws UserNotFound    usernotfound
	 * @throws SQLException    sqlexception
	 * @throws SystemException systemexception
	 */
	public static User Authtication(String userName, String passWord) throws SystemException, SQLException, UserNotFound {
		User user = new User();
		LOGGER.traceEntry(userName + " " + passWord);
		try {
			user = AuthenticationDaoImpl.login(userName, passWord);
			if (user != null) {
				LOGGER.traceExit(user);
				return user;

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SystemException("Something went wrong!!!");
		}
		LOGGER.error("User not found");
		throw new SystemException(LOCAL_MESSAGES_BUNDLE.getString(HMS3000E));
	}
}
