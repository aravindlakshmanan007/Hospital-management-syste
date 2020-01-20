package global.coda.hms.delegate.interfaces;

import global.coda.hms.exception.SystemException;
import global.coda.hms.model.User;

/**
 *
 * @author VC
 *
 */
public interface AuthenticationService {
	/**
	 *
	 * @param username username
	 * @param password password
	 * @return user
	 * @throws SystemException systemexception
	 */
	User login(String username, String password) throws SystemException;
}
