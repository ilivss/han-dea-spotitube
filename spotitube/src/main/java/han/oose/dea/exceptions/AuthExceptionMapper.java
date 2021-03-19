package han.oose.dea.exceptions;

import han.oose.dea.rest.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthExceptionMapper implements ExceptionMapper<TestException> {

    @Override
    public Response toResponse(TestException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        return Response.status(Response.Status.UNAUTHORIZED).entity(errorDTO).build();
    }
}
