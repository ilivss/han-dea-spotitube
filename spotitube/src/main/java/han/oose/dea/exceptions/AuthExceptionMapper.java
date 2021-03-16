package han.oose.dea.exceptions;

import han.oose.dea.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthExceptionMapper implements ExceptionMapper<AuthException> {

    @Override
    public Response toResponse(AuthException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        return Response.status(401).entity(errorDTO).build();
    }
}
