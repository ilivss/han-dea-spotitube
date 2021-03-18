package han.oose.dea.resources;

import han.oose.dea.domain.Token;
import han.oose.dea.dto.UserDTO;
import han.oose.dea.exceptions.AuthException;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.mappers.TokenMapper;
import han.oose.dea.mappers.UserMapper;
import han.oose.dea.services.AuthService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class AuthResource {
    @Inject
    private AuthService authService;

    @Inject
    private UserMapper userMapper;

    @Inject
    private TokenMapper tokenMapper;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) throws AuthException, PersistenceException {
        Token token = authService.login(userMapper.fromDTO(userDTO));

        if (token == null) {
            throw new AuthException("Account bestaat niet of wachtwoord is incorrect!");
        } else {
            return Response.status(Response.Status.OK).entity(tokenMapper.toDTO(token)).build();
        }
    }
}
