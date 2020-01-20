package global.coda.hms.dao;

import static global.coda.hms.constant.DBConstants.DRIVER_CLASS;
import static global.coda.hms.constant.DBConstants.PASSWORD;
import static global.coda.hms.constant.DBConstants.URL;
import static global.coda.hms.constant.DBConstants.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author VC
 *
 */
public class DBConnection {
	private static Connection dbConnection = null;
	/**
	 *
	 * @return Connection
	 */
	public static Connection establishConnection() {
		final Logger logger = LogManager.getLogger(DBConnection.class);
		if (dbConnection == null) {
			try {
				Class.forName(DRIVER_CLASS);
				dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return dbConnection;
	}
	/**
	 *
	 * @throws SQLException sqlException
	 */
	public void closeConnection() throws SQLException {
		dbConnection.close();
	}
}
