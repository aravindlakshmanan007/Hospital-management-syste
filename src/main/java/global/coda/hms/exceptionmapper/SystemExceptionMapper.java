package global.coda.hms.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import global.coda.hms.constant.HttpStatusConstant;
import global.coda.hms.exception.SystemException;

/**
 *
 * @author VC
 *
 */
@Provider
public class SystemExceptionMapper implements ExceptionMapper<SystemException> {

	@Override
	public final Response toResponse(SystemException exception) {

		return Response.status(HttpStatusConstant.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN)
				.entity("Something Went wrong").build();

	}

}
