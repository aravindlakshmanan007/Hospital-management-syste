package global.coda.hms.exception;

/**
 *
 * @author VC
 *
 */
public class UserNotFound extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public UserNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param message            msg
	 * @param cause              cause
	 * @param enableSuppression  enablesuppression
	 * @param writableStackTrace wriable
	 */

	public UserNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param message msg
	 * @param cause   cause
	 */
	public UserNotFound(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param message msg
	 */
	public UserNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param cause cause
	 */

	public UserNotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
