package han.oose.dea.rest;

import han.oose.dea.domain.Token;
import han.oose.dea.exceptions.AuthException;
import han.oose.dea.rest.dto.UserDTO;
import han.oose.dea.exceptions.NoRecoursesFoundException;
import han.oose.dea.exceptions.PersistenceException;
import han.oose.dea.rest.mappers.TokenRestMapper;
import han.oose.dea.rest.mappers.UserRestMapper;
import han.oose.dea.services.AuthService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class AuthEndpoint {
    @Inject
    private AuthService authService;

    @Inject
    private UserRestMapper userRestMapper;

    @Inject
    private TokenRestMapper tokenMapper;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) throws PersistenceException, AuthException {
        Token token = authService.login(userRestMapper.toDomainObject(userDTO));

        if (token == null) {
            throw new AuthException("Account bestaat niet of wachtwoord is incorrect!");
        } else {
            return Response.status(Response.Status.OK).entity(tokenMapper.toDTO(token)).build();
        }
    }
}
