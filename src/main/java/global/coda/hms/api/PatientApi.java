package global.coda.hms.api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import global.coda.hms.constant.ApplicationConstants;
import global.coda.hms.constant.HttpStatusConstant;
import global.coda.hms.delegate.PatientServiceImpl;
import global.coda.hms.exception.BussinessException;
import global.coda.hms.exception.PatientNotFound;
import global.coda.hms.exception.SystemException;
import global.coda.hms.model.CustomResponse;
import global.coda.hms.model.Patient;
import global.coda.hms.model.User;

/**
 *
 * @author VC
 *
 */
@Path("patient")
public class PatientApi {
	/**
	 * @param user user
	 * @return customresponse
	 * @throws SystemException systemexception
	 */
	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<String> DeletePatient(User user) throws SystemException {
		CustomResponse<String> response = new CustomResponse<String>();
		boolean result = PatientServiceImpl.deletePatient(user.getUserId());
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
	 * @throws SQLException    sqlexception
	 * @throws PatientNotFound patientnotfound
	 */
	@POST
	@Path("readall")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<List<Patient>> ReadAllPatient() throws SQLException, PatientNotFound {
		CustomResponse<List<Patient>> response = new CustomResponse<List<Patient>>();
		List<Patient> allPatients = PatientServiceImpl.readAllPatients();
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
	 * @param user user
	 * @return customresponse
	 * @throws SystemException systemexception
	 * @throws SQLException    sqlexcepion
	 * @throws PatientNotFound patientnotfound
	 */
	@POST
	@Path("read")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<Patient> ReadPatient(User user) throws SystemException, SQLException, PatientNotFound {
		CustomResponse<Patient> response = new CustomResponse<Patient>();
		Patient patient;
		patient = PatientServiceImpl.readPatient(user.getUserId());
		response.setStatus(HttpStatusConstant.OK);
		response.setData(patient);
		return response;
	}

	/**
	 *
	 * @param user user
	 * @return customresponse
	 * @throws SystemException    systemexception
	 * @throws BussinessException bussinessexception
	 * @throws SQLException       sqlexception
	 * @throws PatientNotFound    patientnotfound
	 */
	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse<String> UpdatePatient(User user)
			throws SystemException, BussinessException, SQLException, PatientNotFound {
		CustomResponse<String> response = new CustomResponse<String>();
		Patient patient;
		patient = PatientServiceImpl.readPatient(user.getUserId());
		patient.setFirstname(user.getFirstname());
		patient.setLastname(user.getLastname());
		patient.setEmail(user.getEmail());
		patient.setAge(user.getAge());
		patient.setPhonenumber(user.getPhonenumber());
		boolean result = PatientServiceImpl.updatePatient(user.getUserId(), patient);
		if (result) {
			response.setStatus(HttpStatusConstant.OK);
			response.setData(ApplicationConstants.updated);
		} else {
			response.setStatus(HttpStatusConstant.INVALID_DATA);
			response.setData(ApplicationConstants.updateFailed);
		}
		return response;
	}
}
