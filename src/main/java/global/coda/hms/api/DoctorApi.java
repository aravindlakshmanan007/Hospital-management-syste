package global.coda.hms.api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import global.coda.hms.constant.ApplicationConstants;
import global.coda.hms.constant.HttpStatusConstant;
import global.coda.hms.delegate.DoctorServiceImpl;
import global.coda.hms.exception.BussinessException;
import global.coda.hms.exception.DoctorNotFound;
import global.coda.hms.exception.PatientNotFound;
import global.coda.hms.exception.SystemException;
import global.coda.hms.helper.DoctorHelper;
import global.coda.hms.model.CustomResponse;
import global.coda.hms.model.Doctor;
import global.coda.hms.model.DoctorPatientMapper;
import global.coda.hms.model.Patient;
import global.coda.hms.model.User;

/**
 *
 * @author VC
 *
 */
@Path("/doctor")
public class DoctorApi {
	/**
	 *
	 * @param user user
	 * @return customresponse
	 * @throws SystemException systemException
	 * @throws SQLException    sqlexception
	 */
	@PUT
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<String> deleteDoctor(User user) throws SystemException, SQLException {
		CustomResponse<String> response = new CustomResponse<String>();
		boolean result = DoctorServiceImpl.deleteDoctor(user.getUserId());
		if (result) {
			response.setStatus(HttpStatusConstant.OK);
			response.setData(ApplicationConstants.updated);
		} else {
			response.setStatus(HttpStatusConstant.INVALID_DATA);
			response.setData(ApplicationConstants.updateFailed);
		}
		return response;
	}

	/**
	 *
	 * @param user user
	 * @return customresponse
	 * @throws SystemException    systemexception
	 * @throws SQLException       sqlexcepion
	 * @throws PatientNotFound    patientnotfound
	 * @throws BussinessException bussinessException
	 */
	@POST
	@Path("patientmap")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<List<Patient>> patientMap(User user)
			throws SystemException, SQLException, PatientNotFound, BussinessException {
		CustomResponse<List<Patient>> response = new CustomResponse<List<Patient>>();
		List<Patient> allPatients = DoctorServiceImpl.readAllPatients(user.getUserId());
		if (allPatients != null) {
			response.setStatus(HttpStatusConstant.OK);
			response.setData(allPatients);
		} else {
			response.setStatus(HttpStatusConstant.INVALID_DATA);
		}
		return response;
	}

	/**
	 *
	 * @return customresponse
	 * @throws SQLException       sqlexception
	 * @throws BussinessException bussinessexception
	 * @throws SystemException    systemexception
	 * @throws DoctorNotFound     doctornotfound
	 */
	@POST
	@Path("readall")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<List<Doctor>> ReadAllDoctor()
			throws SQLException, DoctorNotFound, SystemException, BussinessException {
		CustomResponse<List<Doctor>> response = new CustomResponse<List<Doctor>>();
		List<Doctor> allDoctor = DoctorServiceImpl.readAllDoctors();
		if (allDoctor != null) {
			response.setStatus(HttpStatusConstant.OK);
			response.setData(allDoctor);
		} else {
			response.setStatus(HttpStatusConstant.INVALID_DATA);
		}
		return response;
	}

	/**
	 *
	 * @param user user
	 * @return customresponse
	 * @throws SystemException    systemexception
	 * @throws SQLException       sqlexception
	 * @throws PatientNotFound    patientnotfound
	 * @throws BussinessException bussinessnotfound
	 */
	@POST
	@Path("read")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<Doctor> ReadDoctor(User user)
			throws SystemException, SQLException, PatientNotFound, BussinessException {
		CustomResponse<Doctor> response = new CustomResponse<Doctor>();
		Doctor doctor = DoctorHelper.readDoctor(user.getUserId());
		response.setStatus(HttpStatusConstant.OK);
		response.setData(doctor);
		return response;
	}

	/**
	 *
	 * @param user user
	 * @return customresponse
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinesexception
	 * @throws SQLException       sqlexception
	 * @throws DoctorNotFound     doctornotfound
	 */
	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<String> UpdateDoctor(User user)
			throws SystemException, BussinessException, SQLException, DoctorNotFound {
		CustomResponse<String> response = new CustomResponse<String>();
		Doctor doctor;
		doctor = DoctorServiceImpl.readDoctor(user.getUserId());
		doctor.setFirstname(user.getFirstname());
		doctor.setLastname(user.getLastname());
		doctor.setEmail(user.getEmail());
		doctor.setAge(user.getAge());
		doctor.setPhonenumber(user.getPhonenumber());
//		System.out.println(user.getFirstname() + user.getLastname() + user.getAge() + user.getEmail()
//				+ user.getPhonenumber() + user.getUserId());
		boolean result = DoctorServiceImpl.updateDoctor(user.getUserId(), doctor);
		if (result) {
			response.setStatus(HttpStatusConstant.OK);
			response.setData(ApplicationConstants.updated);
		} else {
			response.setStatus(HttpStatusConstant.INVALID_DATA);
			response.setData(ApplicationConstants.updateFailed);
		}
		return response;
	}

	/**
	 *
	 * @return customresponse
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexception
	 */
	@POST
	@Path("getpatients")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<List<DoctorPatientMapper>> getPatientForAllDoctor()
			throws SystemException, BussinessException {
		CustomResponse<List<DoctorPatientMapper>> response = new CustomResponse<List<DoctorPatientMapper>>();
		List<DoctorPatientMapper> doctorPatient = DoctorServiceImpl.AllPatientForAllDoctorDel();
		if (doctorPatient != null) {
			response.setStatus(HttpStatusConstant.OK);
			response.setData(doctorPatient);
		} else {
			response.setStatus(HttpStatusConstant.INVALID_DATA);
		}
		return response;

	}

}
