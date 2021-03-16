package han.oose.dea.services;

import han.oose.dea.dao.UserDAO;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.AuthenticationException;
import han.oose.dea.dto.TokenDTO;
import han.oose.dea.dto.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("auth")
public class AuthService {
    private UserDAO userDAO;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO possibleUser) {

        // Retrieve user from db
        User user = userDAO.authenticate(possibleUser.user, possibleUser.password);

        // if user does not exist
        if (user == null) throw new AuthenticationException("User bestaat niet of wachtwoord komt niet overreen!");

        // Generate token and add it to user;
        String token = UUID.randomUUID().toString();
        userDAO.updateToken(user, token);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.user = user.getUsername();
        tokenDTO.token = user.getToken();

        return Response.status(200).entity(tokenDTO).build();
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
