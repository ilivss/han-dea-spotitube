package han.oose.dea.exceptions;

import han.oose.dea.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    @Override
    public Response toResponse(AuthenticationException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.message = e.getMessage();

        return Response.status(401).entity(errorDTO).build();
    }
}
