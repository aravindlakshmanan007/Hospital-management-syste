package global.coda.hms.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import global.coda.hms.constant.HttpStatusConstant;
import global.coda.hms.exception.BussinessException;
/**
 *
 * @author VC
 *
 */
@Provider
public class BussinessExceptionMapper implements ExceptionMapper<BussinessException> {
	/**
	 *
	 */
	@Override
	public Response toResponse(BussinessException exception) {
		return Response.status(HttpStatusConstant.INVALID_DATA).type(MediaType.TEXT_PLAIN)
				.entity(exception.getMessage()).build();
	}

}
