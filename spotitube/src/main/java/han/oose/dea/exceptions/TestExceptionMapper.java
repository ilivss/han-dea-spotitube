package han.oose.dea.exceptions;

import han.oose.dea.rest.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TestExceptionMapper implements ExceptionMapper<TestException> {

    @Override
    public Response toResponse(TestException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        // FIXME: Response moet OK zijn anders logt client uit!
        return Response.status(Response.Status.OK).entity(errorDTO).build();
    }
}
