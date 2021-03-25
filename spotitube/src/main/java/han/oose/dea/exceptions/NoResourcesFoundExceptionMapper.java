package han.oose.dea.exceptions;

import han.oose.dea.rest.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoResourcesFoundExceptionMapper implements ExceptionMapper<NoRecoursesFoundException> {

    @Override
    public Response toResponse(NoRecoursesFoundException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        return Response.ok(errorDTO).build(); // FIXME: Response moet OK zijn anders logt client uit!
    }
}
