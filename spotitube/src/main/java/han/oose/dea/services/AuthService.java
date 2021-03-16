package han.oose.dea.services;

import han.oose.dea.dao.UserDAO;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.AuthException;
import han.oose.dea.dto.TokenDTO;
import han.oose.dea.dto.UserDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.UUID;

@Default
public class AuthService {
    private UserDAO userDAO;

    public TokenDTO login(UserDTO possibleUser) {
        // Retrieve user from db
        User user = userDAO.getUser(possibleUser.user, possibleUser.password);

        // if user does not exist
        if (user == null) throw new AuthException("User bestaat niet of wachtwoord komt niet overreen!");

        // Generate token and add to user;
        String token = UUID.randomUUID().toString();
        userDAO.updateToken(user, token);

        return new TokenDTO(user.getUsername(), user.getToken());
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
