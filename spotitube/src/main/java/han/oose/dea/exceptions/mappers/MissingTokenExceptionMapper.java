package han.oose.dea.exceptions.mappers;

import han.oose.dea.exceptions.MissingTokenException;
import han.oose.dea.rest.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MissingTokenExceptionMapper implements ExceptionMapper<MissingTokenException> {
    @Override
    public Response toResponse(MissingTokenException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorDTO).build();
    }
}
