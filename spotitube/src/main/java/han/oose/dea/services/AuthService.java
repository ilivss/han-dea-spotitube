package han.oose.dea.services;

import han.oose.dea.dao.UserDAO;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.AuthenticationException;
import han.oose.dea.dto.TokenDTO;
import han.oose.dea.dto.UserDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Default
public class AuthService {
    private UserDAO userDAO;

    public TokenDTO login(UserDTO possibleUser) {

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

        return tokenDTO;
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
