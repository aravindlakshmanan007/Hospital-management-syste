package global.coda.hms.delegate.interfaces;

import java.sql.SQLException;
import java.util.List;

import global.coda.hms.exception.BussinessException;
import global.coda.hms.exception.DoctorNotFound;
import global.coda.hms.exception.SystemException;
import global.coda.hms.model.Doctor;

/**
 *
 * @author VC
 *
 */
public interface DoctorService {
	/**
	 *
	 * @param doctor doctor
	 * @return doctor
	 * @throws SQLException       sqlexception
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexception
	 */
	Doctor createDoctor(Doctor doctor) throws SQLException, SystemException, BussinessException;

	/**
	 *
	 * @param userId userId
	 * @return doctor
	 * @throws SQLException       sqlexception
	 * @throws DoctorNotFound     doctornotfound
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexception
	 */
	Doctor readDoctor(int userId) throws SQLException, DoctorNotFound, SystemException, BussinessException;

	/**
	 *
	 * @param userId        userId
	 * @param updatedDoctor updatedoctor
	 * @return boolean
	 * @throws SQLException       sqlexception
	 * @throws DoctorNotFound     doctornotfound
	 * @throws SystemException    systemException
	 * @throws BussinessException bussinessException
	 */
	boolean updateDoctor(int userId, Doctor updatedDoctor)
			throws SQLException, DoctorNotFound, SystemException, BussinessException;

	/**
	 *
	 * @param userId userId
	 * @return boolean
	 * @throws SQLException       sqlexception
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexception
	 */
	boolean deleteDoctor(int userId) throws SQLException, SystemException, BussinessException;

	/**
	 *
	 * @return list_of_doctors
	 * @throws DoctorNotFound     doctornotfound
	 * @throws SQLException       sqlexception
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexeption
	 */
	List<Doctor> readAllDoctors() throws DoctorNotFound, SQLException, SystemException, BussinessException;
}
