package han.oose.dea.exceptions;

import han.oose.dea.rest.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorDTO).build();
    }
}
