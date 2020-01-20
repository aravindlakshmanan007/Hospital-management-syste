package global.coda.hms.exception;

/**
 *
 * @author VC
 *
 */
public class DoctorNotFound extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public DoctorNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param message            message
	 * @param cause              cause
	 * @param enableSuppression  enableSuppression
	 * @param writableStackTrace writableStrackTrace
	 */
	public DoctorNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param message message
	 * @param cause   cause
	 */
	public DoctorNotFound(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param message message
	 */

	public DoctorNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param cause cause
	 */
	public DoctorNotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
