package han.oose.dea.exceptions;

import han.oose.dea.rest.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidTokenExceptionMapper implements ExceptionMapper<InvalidTokenException> {
    @Override
    public Response toResponse(InvalidTokenException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        return Response.status(Response.Status.FORBIDDEN).entity(errorDTO).build();
    }
}
