package global.coda.hms.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import global.coda.hms.constant.HttpStatusConstant;
import global.coda.hms.delegate.AuthenticationServiceImpl;
import global.coda.hms.model.CustomResponse;
import global.coda.hms.model.User;

/**
 *
 * @author VC
 *
 */
@Path("/validate")
public class AuthenticationApi {

	/**
	 * @param user user
	 * @return response
	 */
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public final CustomResponse<String> authenticate(User user) {
		CustomResponse<String> response = new CustomResponse<String>();
		User userDetails;
		try {
			userDetails = AuthenticationServiceImpl.login(user.getUsername(), user.getPassword());
			response.setStatus(HttpStatusConstant.OK);
			response.setData("welcome " + userDetails.getFirstname());
		} catch (Exception e) {
			response.setData(e.getMessage());
		}
		return response;
	}
}
