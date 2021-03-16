package han.oose.dea.resources;

import han.oose.dea.dto.TokenDTO;
import han.oose.dea.dto.UserDTO;
import han.oose.dea.services.AuthService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class AuthResource {
    private AuthService authService;

    // FIXME: Als ik dit weghaal werkt geen een route meer. Waarom?
    public AuthResource() {}

    @Inject
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        TokenDTO tokenDTO = authService.login(userDTO);
        return Response.status(Response.Status.OK).entity(tokenDTO).build();
    }
}
