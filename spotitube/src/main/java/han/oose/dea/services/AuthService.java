package han.oose.dea.services;

import han.oose.dea.exceptions.AuthException;
import han.oose.dea.persistence.ITokenDAO;
import han.oose.dea.persistence.IUserDAO;
import han.oose.dea.domain.Token;
import han.oose.dea.domain.User;
import han.oose.dea.exceptions.PersistenceException;

import javax.inject.Inject;

public class AuthService {
    @Inject
    private IUserDAO userDAO;

    @Inject
    private ITokenDAO tokenDAO;

    public Token login(User potentialUser) throws PersistenceException {
        // Retrieve user from db
        User user = userDAO.authenticate(potentialUser);

        // if user does not exist
        if (user == null) return null;

        // Generate token and save to database;
        return tokenDAO.generateAndSaveToken(user.getUsername());
    }
}
