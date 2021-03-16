package han.oose.dea.services;

import han.oose.dea.dao.UserDAO;
import han.oose.dea.domain.User;
import han.oose.dea.services.dto.UserDTO;

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
        if (user == null) return Response.status(Response.Status.UNAUTHORIZED).build();

        // Generate token and add it to user;
        String token = UUID.randomUUID().toString();
        userDAO.updateToken(user, token);

        UserDTO userDTO = new UserDTO();
        userDTO.user = user.getUsername();
        userDTO.token = user.getToken();

        return Response.status(200).entity(userDTO).build();
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
